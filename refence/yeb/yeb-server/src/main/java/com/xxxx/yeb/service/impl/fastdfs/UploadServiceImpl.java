package com.xxxx.yeb.service.impl.fastdfs;

import com.alibaba.fastjson.JSON;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.xxxx.yeb.config.fastdfs.UploadProperties;
import com.xxxx.yeb.mapper.AdminMapper;
import com.xxxx.yeb.pojo.Admin;
import com.xxxx.yeb.service.fastdfs.IUploadService;
import com.xxxx.yeb.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @description： 文件上传
 * @author: Hxxiapgy
 * @date: 2020/7/9 22:01
 */
@Service
@EnableConfigurationProperties (UploadProperties.class)
public class UploadServiceImpl implements IUploadService {

    private Log log = LogFactory.getLog(UploadServiceImpl.class);

    @Resource
    private FastFileStorageClient storageClient;

    @Resource
    private UploadProperties uploadProperties;

    @Resource
    private AdminMapper adminMapper;

    /**
     * 功能描述:  文件上传
     *
     * @param multipartFile 要上传的文件
     * @param adminStr
     * @return {@link String}    返回文件路径
     * @author hxxiapgy
     * @data 2020/7/9 22:05
     */
    @Override
    public String upload (MultipartFile multipartFile, String adminStr) throws IOException {


        // 校验用户是否存在
        AssertUtil.isTrue(StringUtils.isBlank(adminStr), "登录状态异常！");

        // 获取用户对象
        Admin admin = JSON.parseObject(adminStr, Admin.class);

        AssertUtil.isTrue(null == admin, "非法数据");

        // 1.校验上传文件是否为空
        AssertUtil.fileException(null == multipartFile, "该文件不存在！");

        // 1.校验文件类型
        //获取上传文件的类型
        String contentType = multipartFile.getContentType();
        AssertUtil.fileException(!uploadProperties.getAllowTypes().contains(contentType),
                "该文件类型不支持！");

        // 2.校验文件内容
        // 读取文件
        BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
        AssertUtil.fileException(bufferedImage == null || bufferedImage.getWidth() == 0
                || bufferedImage.getHeight() == 0, "上传文件有问题");

        // 3.获取扩展名
        String extension = StringUtils.substringAfterLast(multipartFile.getOriginalFilename(), ".");
        // 4.上传文件
        StorePath storePath = storageClient.uploadFile(multipartFile.getInputStream(), multipartFile.getSize(), extension, null);
        // 5.获取图片全路径
        String imgPath = uploadProperties.getBaseUrl() + storePath.getFullPath();
        // 6.修改用户头像
        admin.setUserFace(imgPath);

        int row = adminMapper.updateUserFaceById(admin);

        AssertUtil.isTrue(row < 0, "用户头像修改失败！");
        // 5.返回路径
        return imgPath;

    }

}
