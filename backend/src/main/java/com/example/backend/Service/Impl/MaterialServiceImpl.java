package com.example.backend.Service.Impl;

import com.example.backend.Controller.request.MaterialPageRequest;
import com.example.backend.Entity.Material;
import com.example.backend.Mapper.MaterialMapper;
import com.example.backend.Service.MaterialService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//必须引用Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    MaterialMapper materialMapper;

    @Override
    public List<Material> SelectMaterials() {
        return materialMapper.SelectMaterials();
    }
    @Override
    public List<String> listMaterialtag(){
        return materialMapper.listMaterialtag();
    }
    @Override
    public List<Map<String, Integer>> listcountMaterial(){return materialMapper.listcountMaterial();}

    public Map<String, Integer> addlabel() {
        Map<String, Integer> map = new HashMap<>();
        List<String> mtag = materialMapper.listMaterialtag();
        int a = 0, b = 0, c = 0, d= 0,e= 0,f= 0; // 初始化计数器为0
        for (int i = 0; i < mtag.size(); i++) {
            String value = mtag.get(i);
            if (value.equals("A")) {
                a++; // 增加计数器
            } else if (value.equals("B")) {
                b++; // 增加计数器
            } else if (value.equals("C")) {
                c++; // 增加计数器
            } else if (value.equals("D")) {
                d++; // 增加计数器
            } else if (value.equals("E")) {
                e++;
            } else if (value.equals("F")) {
                f++;
            }
        }
        map.put("A", a);
        map.put("B", b);
        map.put("C", c);
        map.put("D", d);
        map.put("E", e);
        map.put("F", f);
        return map;
    }

    @Override
    public Object page(MaterialPageRequest materialPageRequest) {
        String type=materialPageRequest.getMaterialType();
        PageHelper.startPage(materialPageRequest.getPageNum(), materialPageRequest.getPageSize());
        List<Material> materials = materialMapper.listByCondition(materialPageRequest);
        return new PageInfo<>(materials);
    }

    @Override
    public void addMaterial(Material material) {
        materialMapper.addMaterial(material);
    }
    public void updateMaterial(Material material) {
        materialMapper.updateMaterial(material);
    }

    @Override
    public void deleteMaterial(String id) {
        materialMapper.deleteMaterial(id);
    }


}