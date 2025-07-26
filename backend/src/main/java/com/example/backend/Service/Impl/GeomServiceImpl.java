package com.example.backend.Service.Impl;


import com.example.backend.Entity.Geom;
import com.example.backend.Mapper.GeomMapper;
import com.example.backend.Service.GeomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GeomServiceImpl implements GeomService {
    @Autowired
    GeomMapper geomMapper;

    @Override
    public List<Geom> SelectGeoms() {
        return geomMapper.SelectGeoms();
    }
}
