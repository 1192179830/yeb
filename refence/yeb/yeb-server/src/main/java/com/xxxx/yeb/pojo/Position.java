package com.ybzn.yeb.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
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
 * @author zhoubin
 * @since 2020-07-17
 */
@Data
@EqualsAndHashCode (callSuper = false)
@Accessors (chain = true)
@TableName ("t_position")
@ApiModel (value = "Position对象", description = "")
public class Position implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty (value = "id")
    @TableId (value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty (value = "职位")
    private String name;

    @ApiModelProperty (value = "创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty (value = "是否启用")
    private Boolean enabled;


}
