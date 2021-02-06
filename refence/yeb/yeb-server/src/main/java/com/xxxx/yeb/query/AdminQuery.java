package com.ybzn.yeb.query;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * auth:Administrator
 * time:2020/7/22 0022 16:49
 */
@Data
@EqualsAndHashCode (callSuper = false)
@Accessors (chain = true)
@ApiModel (value = "当前用户", description = "")
public class AdminQuery {
    private String username;
}
