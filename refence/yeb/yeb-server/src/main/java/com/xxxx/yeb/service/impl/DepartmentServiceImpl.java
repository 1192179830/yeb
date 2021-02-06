package com.ybzn.yeb.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybzn.yeb.mapper.DepartmentMapper;
import com.ybzn.yeb.pojo.Department;
import com.ybzn.yeb.pojo.RespBean;
import com.ybzn.yeb.service.IDepartmentService;
import com.ybzn.yeb.utils.AssertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl <DepartmentMapper, Department> implements IDepartmentService {

    @Resource
    public DepartmentMapper departmentMapper;

    @Override
    public List <Department> queryAllModules () {

        // 查询所有部门
        List <Department> otlDeptMenus = departmentMapper.queryAllModules();

        // 所有的顶级部门
        List <Department> resultList = new ArrayList <>();

        Map <Integer, Object> tree = new HashMap <>();

        Object itemTree;

        // 将部门存储在map中,key为部门id
        for (int i = 0; i < otlDeptMenus.size() && !otlDeptMenus.isEmpty(); i++) {
            itemTree = otlDeptMenus.get(i);
            // 把所有的数据都放到map中
            tree.put(otlDeptMenus.get(i).getId(), otlDeptMenus.get(i));
        }

        // 遍历map得到顶层节点（游离节点也算作顶层节点）
        for (int i = 0; i < otlDeptMenus.size(); i++) {
            // 优点1：整个方法，只查询了一次数据库
            // 优点2：不用知道顶层节点的id
            if (!tree.containsKey(otlDeptMenus.get(i).getParentId())) {
                // 我们在存储的时候就是将元素的id为键，元素本身为值存入的
                // 以元素的父id为键，在map里取值，若取不到则，对应的元素不存在，即没有父节点，为顶层节点或游离节点
                // 将顶层节点放入list集合
                resultList.add(otlDeptMenus.get(i));
            }
        }

        // 循环数据，将数据放到该节点的父节点的children属性中
        for (int i = 0; i < otlDeptMenus.size() && !otlDeptMenus.isEmpty(); i++) {
            // 数据库中，若一个元素有子节点，那么，该元素的id为子节点的父id
            //treeMap.get(tbCategories.get(i).getParentId());
            // 从map集合中找到父节点
            Department department = (Department) tree.get(otlDeptMenus.get(i).getParentId());
            // 不等于null，也就意味着有父节点
            if (department != null) {
                // 有了父节点，要判断父节点下存贮字节点的集合是否存在，然后将子节点放入
                if (department.getChildren() == null) {
                    // 判断一个集合是否被创建用null：表示结合还没有被分配内存空间(即还没有被创建)，内存大小自然为null
                    // 用集合的size判断集合中是否有元素，为0，没有元素（集合已经被创建），
                    department.setChildren(new ArrayList <Department>());
                }
                // 添加到父节点的ChildList集合下
                department.getChildren().add(otlDeptMenus.get(i));
            }

        }

        return resultList;
    }

    /**
     * 添加子部门
     *
     * @param department
     */
    @Override
    public RespBean addDepartment (Department department) {

        RespBean respBean = new RespBean();
        //1.参数效验
        checkParam(department);
        //2.设置参数默认值
        department.setEnabled(Boolean.TRUE);
        // 设置孩子为空集合，避免前台null不可读
        department.setChildren(new ArrayList <>());


        //3.执行添加判断结果
        AssertUtil.isTrue(departmentMapper.insert(department) < 1, "添加失败!!!");
        respBean.setObj(department);
        respBean.setMessage("添加部门成功!");
        return respBean;
    }

    @Override
    public void deleteDepartment (Integer id) {
        Department department = departmentMapper.selectById(id);
        AssertUtil.isTrue(department == null || null == id, "待删除的部门不存在!!");
        department.setEnabled(Boolean.FALSE);
        AssertUtil.isTrue(departmentMapper.deleteById(id) < 1, "部门删除失败!");

    }


    /**
     * 参数效验
     *
     * @param department
     */
    private void checkParam (Department department) {

        //判断参数值是否为空
        AssertUtil.isTrue(null == department.getName() || department.getName().equals(""), "部门名不能为空!!");
        //判断部门名是否重复
        AssertUtil.isTrue(departmentMapper.queryDepartMent(department.getName()) != null, "部门名已存在!!!");
    }

}
