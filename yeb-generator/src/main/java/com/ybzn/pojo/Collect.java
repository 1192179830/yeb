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
@ApiModel(value="Collect对象", description="")
public class Collect implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "问卷ID")
    @TableId(value = "collect_id", type = IdType.AUTO)
    private Integer collectId;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "问卷表编号")
    @TableField("questionaire_id")
    private Integer questionaireId;

    @ApiModelProperty(value = "填写时间")
    @TableField("collect_full_date")
    private LocalDateTime collectFullDate;

    @ApiModelProperty(value = "截止时间")
    @TableField("collect_full_deadline")
    private LocalDateTime collectFullDeadline;


}
