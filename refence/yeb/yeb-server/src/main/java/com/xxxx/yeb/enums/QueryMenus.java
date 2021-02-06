package com.ybzn.yeb.enums;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ybzn.yeb.pojo.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: yeb
 * @BelongsPackage: com.xxxx.yeb.enums
 * @Author: lhr
 * @Description:
 * @Vsersion v1.0.0
 * @CreateTime: 2020-07-19 19:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryMenus extends Menu {

    @TableField (exist = false)
    private Boolean checked = true;
}
