package com.ybzn.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author Hugo
 * @since 2020-1-13
 */
@Data
@EqualsAndHashCode (callSuper = false)
@Accessors (chain = true)
@TableName ("t_joblevel")
@ApiModel (value = "Joblevel对象", description = "")
public class Joblevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty (value = "id")
    @TableId (value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty (value = "职称名称")
    private String name;

    @ApiModelProperty (value = "职称等级")
    private String titleLevel;

    @ApiModelProperty (value = "创建时间")
    @JsonFormat(pattern = "yyyy-mm-dd",timezone = "Asia/Shanghai")
    private LocalDateTime createDate;

    @ApiModelProperty (value = "是否启用")
    private Boolean enabled;


}
