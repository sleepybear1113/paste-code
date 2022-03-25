package com.xjx.pastecode.vo;

import com.xjx.pastecode.entity.Code;
import com.xjx.pastecode.utils.Encryption;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * there is introduction
 *
 * @author xjx
 * @date 2022/3/24 14:43
 */
@Data
public class PasteCodeVo implements Serializable {
    @Serial
    private static final long serialVersionUID = -6463101907539242537L;

    private static final Encryption ENCRYPTION = new Encryption(4507L, 1933L, 34);

    private Long id;

    /**
     * encrypted id，对 id 加密后的 eid
     */
    private String eid;
    private Long userId;
    private String code;
    private String style;
    private String language;

    public void encrypt() {
        this.eid = encryptId(this.id);
        this.id = null;
    }

    public void decrypt() {
        this.id = decryptEid(this.eid);
    }

    public static String encryptId(Long id) {
        return ENCRYPTION.encryptId(id);
    }

    public static Long decryptEid(String eid) {
        return ENCRYPTION.decryptEid(eid);
    }

    public static PasteCodeVo fillByCode(Code code) {
        PasteCodeVo pasteCodeVo = new PasteCodeVo();
        if (code == null) {
            return pasteCodeVo;
        }
        pasteCodeVo.setCode(code.getCode());
        pasteCodeVo.setLanguage(code.getLanguage());
        pasteCodeVo.setStyle(code.getStyle());
        pasteCodeVo.setUserId(code.getUserId());
        pasteCodeVo.setId(code.getId());
        return pasteCodeVo;
    }
}
