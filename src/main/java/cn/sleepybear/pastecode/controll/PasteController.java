package cn.sleepybear.pastecode.controll;

import cn.sleepybear.pastecode.constants.Constant;
import cn.sleepybear.pastecode.logic.PasteCodeLogic;
import cn.sleepybear.pastecode.param.PasteCodeParam;
import cn.sleepybear.pastecode.vo.PasteCodeVo;
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
@RequestMapping(value = Constant.PREFIX)
public class PasteController {
    @Value("${const.admin.key:}")
    private String adminKey;

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
        if (StringUtils.isNotBlank(adminKey) && adminKey.equals(key)) {
            return pasteCodeLogic.getById(id);
        }
        return pasteCodeLogic.getByEid(eid);
    }

}
