package com.example.backend.Mapper;

import com.example.backend.Controller.request.MaterialPageRequest;
import com.example.backend.Entity.Material;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MaterialMapper {
    
    List<Material> SelectMaterials();

    void addMaterial(Material material);
    void deleteMaterial(String id);
    void updateMaterial(Material material);
    List<String> listMaterialtag();
    List<Material> listByCondition(MaterialPageRequest materialPageRequest);
    List<Map<String,Integer>> listcountMaterial();
}

