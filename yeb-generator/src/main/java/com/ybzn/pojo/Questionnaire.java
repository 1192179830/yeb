package com.ybzn.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="Questionnaire对象", description="")
public class Questionnaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "问卷表ID")
    @TableId(value = "questionnaire_id", type = IdType.AUTO)
    private Integer questionnaireId;

    @ApiModelProperty(value = "问卷标题")
    @TableField("questionnaire_title")
    private String questionnaireTitle;

    @ApiModelProperty(value = "问卷副标题")
    @TableField("questionnaire_subtitle")
    private String questionnaireSubtitle;

    @ApiModelProperty(value = "问卷描述")
    @TableField("questionnaire_description")
    private String questionnaireDescription;

    @ApiModelProperty(value = "问卷类型（0:信息日检,1:返程登记..）")
    @TableField("questionnaire_style")
    private Integer questionnaireStyle;


}
