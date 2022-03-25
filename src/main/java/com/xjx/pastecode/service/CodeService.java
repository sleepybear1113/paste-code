package com.xjx.pastecode.service;

import com.xjx.pastecode.entity.Code;
import com.xjx.pastecode.mapper.CodeMapper;
import jakarta.annotation.Resource;
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

    public Long insert(Code code) {
        code.fillAllTime();
        codeMapper.insert(code);
        return code.getId();
    }

    public Code get(Long id) {
        if (id == null) {
            return null;
        }
        return codeMapper.selectById(id);
    }
}
