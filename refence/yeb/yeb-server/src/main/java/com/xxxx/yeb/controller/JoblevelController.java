package com.ybzn.yeb.controller;


import com.ybzn.yeb.pojo.Joblevel;
import com.ybzn.yeb.pojo.RespBean;
import com.ybzn.yeb.service.IJoblevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
@RestController
@RequestMapping ("/system/basic/joblevel")
public class JoblevelController {

    @Resource
    private IJoblevelService joblevelService;

    @GetMapping ("/")
    @ApiOperation (value = "查询职称列表")
    public List <Joblevel> getJobLevelList () {
        return joblevelService.getJobLevelList();
    }

    @RequestMapping (method = RequestMethod.POST, path = "/")
    @ApiOperation (value = "新增职称")
    public RespBean addOrUpdateJobLevel (@RequestBody Joblevel joblevel) {
        joblevelService.addJobLevel(joblevel);
        return RespBean.success("新增职称成功！");
    }

    @PutMapping ("/")
    @ApiOperation (value = "修改职称")
    public RespBean updateJobLevel (@RequestBody Joblevel joblevel) {
        joblevelService.updateJobLevel(joblevel);
        return RespBean.success("新增职称成功！");
    }

    @RequestMapping (method = RequestMethod.DELETE, path = "/")
    @ApiOperation (value = "批量删除职称")
    public RespBean deleteBatchJobLevel (String ids) {
        joblevelService.deleteJobLevel(ids);
        return RespBean.success("批量删除操作成功！");
    }

    @DeleteMapping ("/{id}")
    @ApiOperation (value = "删除职称")
    public RespBean deleteJobLevel (@PathVariable Integer id) {
        joblevelService.deleteJobLevel(String.valueOf(id));
        return RespBean.success("删除成功！");
    }

}
