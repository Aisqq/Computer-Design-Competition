package com.example.backend.Service.Impl;

import com.example.backend.Controller.request.warningPageRequest;
import com.example.backend.Entity.warning;
import com.example.backend.Mapper.warningMapper;
import com.example.backend.Service.warningService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
//必须引用Service
public class warningServiceImpl implements warningService {
    @Autowired
    warningMapper warningMapper;
    @Override
    public List<warning> Selectwarnings() {
        return warningMapper.Selectwarnings();
    }
    @Override
    public Object page(warningPageRequest warningPageRequest) {
        PageHelper.startPage(warningPageRequest.getPageNum(), warningPageRequest.getPageSize());
        List<warning> warnings = warningMapper.listByCondition(warningPageRequest);
        return new PageInfo<>(warnings);
    }
    @Override
    public List<Map<String,Integer>> Selectwarningtype(){
        warningMapper.Selectwarningtype();
        return warningMapper.Selectwarningtype();
    }
    @Override
    public List<Map<Integer,String>> Selectwarninglevel(){
        warningMapper.Selectwarninglevel();
        return warningMapper.Selectwarninglevel();
    }
}
