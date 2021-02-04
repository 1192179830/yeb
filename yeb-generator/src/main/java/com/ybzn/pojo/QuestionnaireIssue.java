package com.ybzn.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("questionnaire_issue")
@ApiModel(value="QuestionnaireIssue对象", description="")
public class QuestionnaireIssue implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "问卷表与问题表中间表(1对多)")
    @TableId(value = "questionnaire_issue_id", type = IdType.AUTO)
    private Integer questionnaireIssueId;

    @ApiModelProperty(value = "问卷表id")
    @TableField("questionnaire_id")
    private Integer questionnaireId;

    @ApiModelProperty(value = "问题id")
    @TableField("issue_id")
    private Integer issueId;

    @ApiModelProperty(value = "问题答案")
    @TableField("user_answer")
    private String userAnswer;


}
