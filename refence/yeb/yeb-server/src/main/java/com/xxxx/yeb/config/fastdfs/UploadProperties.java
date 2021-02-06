package com.ybzn.yeb.config.fastdfs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @description： 配置fastdfs的属性类
 * @author: Hxxiapgy
 * @date: 2020/7/9 21:58
 */
@ConfigurationProperties (prefix = "upload")
@Data
public class UploadProperties {

    private String baseUrl;

    private List <String> allowTypes;

    public String getBaseUrl () {
        return baseUrl;
    }

    public void setBaseUrl (String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List <String> getAllowTypes () {
        return allowTypes;
    }

    public void setAllowTypes (List <String> allowTypes) {
        this.allowTypes = allowTypes;
    }
}
