package com.xxxx.yeb.controller.fastdfs;

import com.xxxx.yeb.service.fastdfs.IUploadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description： 文件上传
 * @author: Hxxiapgy
 * @date: 2020/7/9 22:25
 */
@Controller
@RequestMapping ("/system/admin/info")
class UploadController {

    @Resource
    private IUploadService uploadService;

    /**
     * 功能描述:  文件上传
     *
     * @param articleImage 要上传的文件
     * @return {@link Map< String, Object>}
     * @author hxxiapgy
     * @data 2020/7/9 22:27
     */
    @PostMapping ("/upload/")
    @ResponseBody
    public Map <String, Object> upload (@RequestParam ("articleImage") MultipartFile articleImage,
                                        @RequestParam ("admin") String adminStr) throws IOException {

        String filePath = uploadService.upload(articleImage, adminStr);

        Map <String, Object> map = new HashMap <>();
        map.put("code", "200");
        map.put("message", "文件上传成功");
        map.put("filePath", filePath);

        return map;
    }

}
