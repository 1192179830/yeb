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
@ApiModel(value="Issue对象", description="")
public class Issue implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "问题ID")
    @TableId(value = "issue_id", type = IdType.AUTO)
    private Integer issueId;

    @ApiModelProperty(value = "问题内容")
    @TableField("issue_conten")
    private String issueConten;

    @ApiModelProperty(value = "问题是否必填 0:必填,1选填")
    @TableField("is_fill")
    private Integer isFill;

    @ApiModelProperty(value = "问题题型 0选择 1填空")
    @TableField("issue_type")
    private Integer issueType;

    @ApiModelProperty(value = "问题选项，用户json存储")
    @TableField("issue_options")
    private String issueOptions;


}
