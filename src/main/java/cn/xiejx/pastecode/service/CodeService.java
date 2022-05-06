package cn.xiejx.pastecode.service;

import cn.xiejx.pastecode.dto.CodeDto;
import cn.xiejx.pastecode.entity.Code;
import cn.xiejx.pastecode.mapper.CodeMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * there is introduction
 *
 * @author xjx
 * @date 2022/3/24 14:41
 */
@Service
public class CodeService {
    @Resource
    private CodeMapper codeMapper;

    public Long insert(CodeDto codeDto) {
        if (codeDto == null) {
            return null;
        }
        Code code = new Code();
        BeanUtils.copyProperties(codeDto, code);
        code.fillAllTime();
        codeMapper.insert(code);
        return code.getId();
    }

    public CodeDto get(Long id) {
        if (id == null) {
            return null;
        }
        Code code = codeMapper.selectById(id);
        if (code == null) {
            return null;
        }
        CodeDto codeDto = new CodeDto();
        BeanUtils.copyProperties(code, codeDto);
        return codeDto;
    }
}
