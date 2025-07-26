package com.example.backend.Controller;


import com.example.backend.Entity.Geom;
import com.example.backend.Service.GeomService;
import com.example.backend.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Geom")
public class GeomController {
    @Autowired
    GeomService geomService;

    @GetMapping("/List")
    public List<Geom> getGeom(){
        List<Geom> geoms = geomService.SelectGeoms();
        return geoms;
    }
}
