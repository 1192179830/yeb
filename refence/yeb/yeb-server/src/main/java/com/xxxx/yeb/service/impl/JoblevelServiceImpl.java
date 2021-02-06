package com.ybzn.yeb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybzn.yeb.enums.TitleLevelEnum;
import com.ybzn.yeb.mapper.JoblevelMapper;
import com.ybzn.yeb.pojo.Joblevel;
import com.ybzn.yeb.service.IJoblevelService;
import com.ybzn.yeb.utils.AssertUtil;
import com.ybzn.yeb.utils.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
public class JoblevelServiceImpl extends ServiceImpl <JoblevelMapper, Joblevel> implements IJoblevelService {

    @Resource
    private JoblevelMapper joblevelMapper;

    /**
     * @description: 查询职称列表
     * @param:
     * @return: {@link List< Joblevel>}
     * @throws:
     * @author: hxxiapgy
     * @date: 2020/7/18 9:11
     */
    @Override
    public List <Joblevel> getJobLevelList () {
        return joblevelMapper.selectList(new QueryWrapper <Joblevel>());
    }

    /**
     * 新增职称
     *
     * @param: joblevel
     * @return:
     * @author: hxxiapgy
     */
    @Override
    public void addJobLevel (Joblevel joblevel) {

        // 1.参数校验
        checkParam(joblevel);

        // 2.判断职称名称是否可用
        // 根据职称名称查询查询
        Joblevel temp = selectJobLevelByName(joblevel.getName());

        AssertUtil.isTrue(null != temp, "该职称已存在，不能重复添加！");

        // 3.判断职称等级是否合法
        checkTitleLevel(joblevel.getTitleLevel());

        // 设置当前时间为创建时间
        joblevel.setCreateDate(LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai"))));
        // 默认设置为可用
        joblevel.setEnabled(true);

        // 3.新增职称
        int insert = joblevelMapper.insert(joblevel);

        AssertUtil.isTrue(insert < 1, "职称新增失败！");
    }

    /**
     * @description: 判断职称等级是否合法
     * @param: titleLevel
     * @return:
     * @throws:
     * @author: hxxiapgy
     * @date: 2020/7/19 14:42
     */
    private void checkTitleLevel (String titleLevel) {
        // 判断当前值是否在枚举中
        AssertUtil.isTrue(!EnumUtils.isExist(TitleLevelEnum.values(), titleLevel),
                "请输入合法的职称等级！");
    }

    /**
     * 根据职称名查询职称
     *
     * @param: name
     * @return:
     * @author: hxxiapgy
     */
    private Joblevel selectJobLevelByName (String name) {

        // 查询职称
        QueryWrapper <Joblevel> queryWrapper = new QueryWrapper <>();
        queryWrapper.eq("name", name);
        return joblevelMapper.selectOne(queryWrapper);

    }

    /**
     * 更新职称
     *
     * @param: joblevel
     * @return:
     * @author: hxxiapgy
     */
    @Override
    public void updateJobLevel (Joblevel joblevel) {

        // 1.参数校验
        checkParam(joblevel);

        // 1.判断职称名称是否可用
        // 查询职称信息
        Joblevel temp = selectJobLevelByName(joblevel.getName());
        // 判断是否可用
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(joblevel.getId())),
                "该职称已存在");


        // 2.判断职称等级是否合法
        checkTitleLevel(joblevel.getTitleLevel());

        // 3.更新职称信息
        int row = joblevelMapper.updateById(joblevel);
        AssertUtil.isTrue(row < 0, "职称修改失败");
    }

    /**
     * @description: 批量删除职称
     * @param: ids
     * @return:
     * @throws:
     * @author: hxxiapgy
     * @date: 2020/7/18 22:15
     */
    @Override
    public void deleteJobLevel (String ids) {

        // 校验参数是否存在
        AssertUtil.isTrue(null == ids, "请选择要删除的数据！S");

        // 删除操作
        String[] idStrs = ids.split(",");
        int count = 0;
        for (String id : idStrs) {
            joblevelMapper.deleteById(Integer.parseInt(id));
            count++;
        }
        AssertUtil.isTrue(count < idStrs.length, "删除操作失败!");
    }

    /***
     *  校验参数
     * @param: joblevel
     * @return:
     * @author: hxxiapgy
     */
    private void checkParam (Joblevel joblevel) {
        // 判断是否存在增加记录
        AssertUtil.isTrue(null == joblevel, "操作无效，请选择要添加或修改的职称！！");
        // 判断职称名称使用存在
        AssertUtil.isTrue(StringUtils.isBlank(joblevel.getName()), "请输入职称名称！");
        // 判断职称等级是否存在
        AssertUtil.isTrue(null == joblevel.getTitleLevel(), "请输入职称等级！");
    }
}
