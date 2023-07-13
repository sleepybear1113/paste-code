package cn.sleepybear.pastecode.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * there is introduction
 *
 * @author xjx
 * @date 2022/3/25 14:12
 */
@Data
public class CodeDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -6207018401536538694L;

    private Long id;
    private Long createTime;
    private Long modifyTime;
    private Long userId;
    private String language;
    private String style;
    private String code;
    private String ip;
}
