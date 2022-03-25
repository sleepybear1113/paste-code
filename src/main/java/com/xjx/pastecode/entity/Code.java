package com.xjx.pastecode.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * there is introduction
 *
 * @author xjx
 * @date 2022/3/24 14:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("code")
public class Code extends BaseDomain {
    @Serial
    private static final long serialVersionUID = 424718537367192746L;

    @TableField("user_id")
    private Long userId;
    @TableField("language")
    private String language;
    @TableField("style")
    private String style;
    @TableField("code")
    private String code;
}
