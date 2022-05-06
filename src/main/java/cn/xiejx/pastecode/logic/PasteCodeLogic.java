package cn.xiejx.pastecode.logic;

import cn.xiejx.pastecode.dto.CodeDto;
import cn.xiejx.pastecode.param.PasteCodeParam;
import cn.xiejx.pastecode.service.CodeService;
import cn.xiejx.pastecode.vo.PasteCodeVo;
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

        CodeDto codeDto = new CodeDto();
        codeDto.setCode(pasteCodeParam.getCode());
        codeDto.setLanguage(pasteCodeParam.getLanguage());
        codeDto.setStyle(pasteCodeParam.getStyle());
        codeDto.setUserId(userId);
        return PasteCodeVo.encryptId(codeService.insert(codeDto));
    }

    public PasteCodeVo getByEid(String eid) {
        Long id = PasteCodeVo.decryptEid(eid);
        if (id == null) {
            return null;
        }

        CodeDto codeDto = codeService.get(id);
        PasteCodeVo pasteCodeVo = PasteCodeVo.fillByCode(codeDto);
        pasteCodeVo.encrypt();
        return pasteCodeVo;
    }

    public PasteCodeVo getById(Long id) {
        if (id == null) {
            return null;
        }
        CodeDto codeDto = codeService.get(id);
        PasteCodeVo pasteCodeVo = PasteCodeVo.fillByCode(codeDto);
        pasteCodeVo.encrypt();
        pasteCodeVo.decrypt();
        return pasteCodeVo;
    }
}
