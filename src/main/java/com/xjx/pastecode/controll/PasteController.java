package com.xjx.pastecode.controll;

import com.xjx.pastecode.logic.PasteCodeLogic;
import com.xjx.pastecode.param.PasteCodeParam;
import com.xjx.pastecode.vo.PasteCodeVo;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * there is introduction
 *
 * @author xjx
 * @date 2022/3/24 13:32
 */
@RestController
public class PasteController {
    @Value("${const.admin.key:}")
    private String adminKey;
    @Value("${spring.datasource.password}")
    private String a;
    @Resource
    private PasteCodeLogic pasteCodeLogic;

    @RequestMapping("/paste")
    public PasteCodeVo paste(@RequestBody PasteCodeParam pasteCodeParam) {
        PasteCodeVo pasteCodeVo = new PasteCodeVo();
        pasteCodeVo.setEid(pasteCodeLogic.insert(pasteCodeParam, null));
        return pasteCodeVo;
    }

    @RequestMapping("/get")
    public PasteCodeVo get(String eid, Long id, String key) {
        if (!StringUtils.isBlank(adminKey) && adminKey.equals(key)) {
            return pasteCodeLogic.getById(id);
        }
        return pasteCodeLogic.getByEid(eid);
    }

}
