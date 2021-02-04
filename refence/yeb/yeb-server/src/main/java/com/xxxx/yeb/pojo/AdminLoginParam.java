package com.xxxx.yeb.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 登录实体类
 *
 * @author zhoubin
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode (callSuper = false)
@Accessors (chain = true)
@ApiModel (value = "登录实体类", description = "")
public class AdminLoginParam {
    @ApiModelProperty (value = "用户名", required = true)
    private String username;
    @ApiModelProperty (value = "密码", required = true)
    private String password;
    @ApiModelProperty (value = "验证码", required = true)
    private String code;

}
