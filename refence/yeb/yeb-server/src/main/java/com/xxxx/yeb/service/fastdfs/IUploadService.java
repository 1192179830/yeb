package com.ybzn.yeb.service.fastdfs;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @description: 文件上传
 * @author: hxxiapgy
 * @date: 2020/7/19 12:00
 * @version: v1.0
 */

public interface IUploadService {

    /**
     * @description: 文件上传
     * @param: multipartFile 要上传的文件
     * @return: {@link String} 返回文件路径
     * @throws:
     * @author: hxxiapgy
     * @date: 2020/7/19 12:01
     */
    String upload (MultipartFile multipartFile, String adminStr) throws IOException;
}
