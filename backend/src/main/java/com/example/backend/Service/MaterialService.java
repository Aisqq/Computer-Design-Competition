package com.example.backend.Service;

import com.example.backend.Controller.request.MaterialPageRequest;
import com.example.backend.Entity.Material;

import java.util.List;
import java.util.Map;

public interface MaterialService {

    List<Material> SelectMaterials();
    List<String> listMaterialtag();
    Object page(MaterialPageRequest materialpagerequest);

    void addMaterial(Material material);
    void updateMaterial(Material material);
    void deleteMaterial(String id);

    Map<String,Integer> addlabel();
    List<Map<String, Integer>> listcountMaterial();
}
