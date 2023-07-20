package cn.sleepybear.pastecode.param;

import cn.sleepybear.pastecode.exception.FrontException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serial;
import java.io.Serializable;

/**
 * there is introduction
 *
 * @author xjx
 * @date 2022/3/24 14:29
 */
@Data
public class PasteCodeParam implements Serializable {
    public static final int MAX_CODE_LENGTH = 16777215;
    public static final int MAX_STYLE_LENGTH = 31;
    public static final int MAX_LANGUAGE_LENGTH = 31;

    @Serial
    private static final long serialVersionUID = 3614981738958659678L;

    private String code;
    private String style;
    private String language;
    private String adminKey;

    public static void valid(PasteCodeParam pasteCodeParam) {
        if (pasteCodeParam == null) {
            throw new FrontException("参数错误");
        }

        String code = pasteCodeParam.getCode();
        if (StringUtils.isBlank(code)) {
            throw new FrontException("内容为空");
        }
        if (code.length() >= MAX_CODE_LENGTH) {
            throw new FrontException("内容过长");
        }

        String style = pasteCodeParam.getStyle();
        if (StringUtils.isBlank(style)) {
            throw new FrontException("格式为空");
        }
        if (style.length() >= MAX_STYLE_LENGTH) {
            throw new FrontException("格式过长");
        }

        String language = pasteCodeParam.getLanguage();
        if (StringUtils.isBlank(language)) {
            throw new FrontException("语言为空");
        }
        if (language.length() > MAX_LANGUAGE_LENGTH) {
            throw new FrontException("语言过长");
        }
    }
}
