package com.example.backend.Mapper;

import com.example.backend.Entity.Geom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GeomMapper {
    List<Geom> SelectGeoms();
}
