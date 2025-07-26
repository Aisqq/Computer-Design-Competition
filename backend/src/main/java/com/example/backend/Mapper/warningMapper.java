package com.example.backend.Mapper;

import com.example.backend.Controller.request.warningPageRequest;
import com.example.backend.Entity.warning;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface warningMapper {
//    @Select("select * from warning")

    List<warning> Selectwarnings();

    List<warning> listByCondition(warningPageRequest warningPageRequest);

    List<Map<String,Integer>> Selectwarningtype();

    List<Map<Integer,String>> Selectwarninglevel();
}
