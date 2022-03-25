package com.xjx.pastecode.logic;

import com.xjx.pastecode.entity.Code;
import com.xjx.pastecode.param.PasteCodeParam;
import com.xjx.pastecode.service.CodeService;
import com.xjx.pastecode.vo.PasteCodeVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * there is introduction
 *
 * @author xjx
 * @date 2022/3/24 14:45
 */
@Component
public class PasteCodeLogic {
    @Resource
    private CodeService codeService;

    public String insert(PasteCodeParam pasteCodeParam, Long userId) {
        PasteCodeParam.valid(pasteCodeParam);

        Code code = new Code();
        code.setCode(pasteCodeParam.getCode());
        code.setLanguage(pasteCodeParam.getLanguage());
        code.setStyle(pasteCodeParam.getStyle());
        code.setUserId(userId);
        return PasteCodeVo.encryptId(codeService.insert(code));
    }

    public PasteCodeVo getByEid(String eid) {
        Long id = PasteCodeVo.decryptEid(eid);
        if (id == null) {
            return null;
        }

        Code code = codeService.get(id);
        PasteCodeVo pasteCodeVo = PasteCodeVo.fillByCode(code);
        pasteCodeVo.encrypt();
        return pasteCodeVo;
    }

    public PasteCodeVo getById(Long id) {
        if (id == null) {
            return null;
        }
        Code code = codeService.get(id);
        PasteCodeVo pasteCodeVo = PasteCodeVo.fillByCode(code);
        pasteCodeVo.encrypt();
        pasteCodeVo.decrypt();
        return pasteCodeVo;
    }
}
