package com.ybzn.yeb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybzn.yeb.mapper.EmployeeMapper;
import com.ybzn.yeb.mapper.PositionMapper;
import com.ybzn.yeb.pojo.Position;
import com.ybzn.yeb.pojo.RespBean;
import com.ybzn.yeb.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
@Service
public class PositionServiceImpl extends ServiceImpl <PositionMapper, Position> implements IPositionService {

    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 查询职位所有信息
     *
     * @return
     */
    @Override
    public RespBean selectPosition () {
        //创建HashMap
        HashMap <String, Object> positionMap = new HashMap <>();
        //把要查询的数据字段和值放入Map中
        positionMap.put("enabled", true);
        //调用BaseMapper的查询所有的方法，把要传入的参数放入。
        List <Position> positionList = positionMapper.selectByMap(positionMap);
        //判断是否查询查出来数据
        if (positionList.size() < 0) {
            //返回RespBean错误信息
            return RespBean.error("暂无数据，请添加数据！");
        }
        //返回RespBean
        return RespBean.success("查询成功", positionList);
    }

    /**
     * 删除职位信息，
     *
     * @return
     */
    @Transactional
    @Override
    public RespBean deletePosition (Integer id) {
        //判断传递过的id是否为空
        if (null == id) {
            return RespBean.error("删除失败！");
        }
        //查询所有使用该职位的人数
        int count = employeeMapper.selectcount(id);
        //判断总人数
        if (count > 0) {
            return RespBean.error("有员工信息中引用该职位，请清除所有引用！");
        }
        //创建Position对象
        Position position = new Position();
        position.setId(id);
        //把表中Enabled字段设置为false--关闭 true--开启
        position.setEnabled(false);
        //调用更新操作
        int result = positionMapper.updateById(position);
        //判断影响数
        if (result < 1) {
            return RespBean.error("删除失败！");
        }
        return RespBean.success("职位删除成功！");
    }

    /**
     * 删除多条数据
     *
     * @param ids 职位ID
     * @return
     */
    @Transactional
    @Override
    public RespBean deleteAllPosition (String ids) {
        //职位中有人数的个数
        int error = 0;
        //职位中没有人的个数
        int success = 0;
        //查看ID是否为空
        if (null == ids) {
            return RespBean.error("请选择要删除的职位");
        }

        String[] idStr = ids.split(",");

        //创建Position对象
        Position position = new Position();
        for (int i = 0; i < idStr.length; i++) {

            int id = Integer.parseInt(idStr[i]);

            //查询所有使用该职位的人数
            int count = employeeMapper.selectcount(id);
            //判断总人数
            if (count == 0) {
                ++success;
                position.setId(id);
                //把表中Enabled字段设置为false--关闭 true--开启
                position.setEnabled(false);
                //调用更新操作
                int result = positionMapper.updateById(position);
                //判断影响数
                if (result < 1) {
                    return RespBean.error("删除失败！");
                }
            }
        }
        error = idStr.length - success;
        return RespBean.success("成功删除" + success + "条数据，失败" + error + "条数据");
    }

    /**
     * 添加职位
     *
     * @param pos
     * @return
     */
    @Transactional
    @Override
    public Boolean addPosition (Position pos) {
        //判断职位名称是否为空
        if ("".equals(pos.getName()) || pos.getName().isEmpty()) {
            return false;
        }
        //查询职位名字，和添加的职位名称是否重复
        List <Position> positionList = this.selectName(pos);
        //判断是否存在
        if (positionList.size() > 0) {
            return false;
        }
        //创建职位对象
        Position position = new Position();
        //插入数据返回受影响的行数
        int insert = positionMapper.addPosition(pos.getName());
        //判断受影响行数
        if (insert < 1) {
            return false;
        }
        return true;
    }

    /**
     * 修改职位信息
     *
     * @param updatePos
     * @return
     */
    @Transactional
    @Override
    public Boolean updatePosition (Position updatePos) {
        //判断传递过来的id和name是否为空
        if (updatePos.getId() == null) {
            return false;
        }
        if ("".equals(updatePos.getName()) || updatePos.getName().isEmpty()) {
            return false;
        }
        //查询这个用的名字是否有人使用
        List <Position> positionList = this.selectName(updatePos);
        //遍历循环获取用户ID
        for (Position positions : positionList) {
            Integer id = positions.getId();
            //把用户ID和传递过来的ID进行比较，如果不同，修改失败
            if (!id.equals(updatePos.getId())) {
                return false;
            }
        }
        //创建一个Position对象
        Position position = new Position();
        //把用户穿过的ID和name进行赋值
        position.setId(updatePos.getId());
        position.setName(updatePos.getName());
        // 注意：updateById 参数是一个对象
        int i = positionMapper.updateById(position);
        if (i < 0) {
            return false;
        }
        return true;
    }

    /**
     * 查询是否存在要创建的用户
     *
     * @return
     */
    private List <Position> selectName (Position pos) {
        //创建HashMap
        HashMap <String, Object> posMap = new HashMap <>();
        //将要查询的数据存放到map中
        posMap.put("name", pos.getName());
        posMap.put("enabled", true);
        //查询
        List <Position> positionList = positionMapper.selectByMap(posMap);
        return positionList;
    }
}
