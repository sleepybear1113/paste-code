package com.xjx.pastecode.controll;

import com.xjx.pastecode.logic.PasteCodeLogic;
import com.xjx.pastecode.param.PasteCodeParam;
import com.xjx.pastecode.vo.PasteCodeVo;
import jakarta.annotation.Resource;
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
        String k = "1113";
        if (k.equals(key)) {
            return pasteCodeLogic.getById(id);
        }
        return pasteCodeLogic.getByEid(eid);
    }

}
