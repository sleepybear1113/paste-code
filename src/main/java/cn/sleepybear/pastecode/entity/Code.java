package cn.sleepybear.pastecode.entity;

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

    private Long userId;
    private String language;
    private String style;
    private String code;
    private String ip;
}
