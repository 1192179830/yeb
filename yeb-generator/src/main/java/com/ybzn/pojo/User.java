package com.ybzn.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Hugo
 * @since 2021-01-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID 主键")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "账号")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "密码")
    @TableField("user_password")
    private String userPassword;

    @ApiModelProperty(value = "身份证号")
    @TableField("user_idcard")
    private String userIdcard;

    @ApiModelProperty(value = "电话")
    @TableField("user_phone")
    private String userPhone;

    @ApiModelProperty(value = "用户头像")
    @TableField("user_icon")
    private String userIcon;

    @ApiModelProperty(value = "用户个性签名")
    @TableField("user_description")
    private String userDescription;

    @ApiModelProperty(value = "所在社区ID")
    @TableField("user_address")
    private Integer userAddress;

    @ApiModelProperty(value = "0:健康(默认),1:患病，2:疑似,")
    @TableField("user_status")
    private Integer userStatus;

    @ApiModelProperty(value = "0:普通用户(默认),其他:社区管理员Id")
    @TableField("user_roles")
    private Integer userRoles;

    @ApiModelProperty(value = "字段创建时间")
    @TableField("gmt_create")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "字段修改时间")
    @TableField("gmt_modified")
    private LocalDateTime gmtModified;


}
