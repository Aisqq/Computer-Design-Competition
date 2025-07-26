package com.example.backend.Controller;

import com.example.backend.Controller.request.MaterialPageRequest;
import com.example.backend.Entity.Material;
import com.example.backend.Service.MaterialService;
import com.example.backend.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin            //解决跨域问题
@RestController
@RequestMapping("/Material")
public class MaterialController {

    @Autowired
    MaterialService materialService;

    @DeleteMapping("/delete/{id}")
    public Result deleteMaterial(@PathVariable String id) {
        materialService.deleteMaterial(id);
        return Result.success();
    }

    @PostMapping("/update")
    public Result updateMaterial(@RequestBody Material material){
        materialService.updateMaterial(material);
        return Result.success();
    }

    @PostMapping("/add")//增加用户的前端接口
    public Result addEmployee(@RequestBody Material material) {//将json转为实体对象
        materialService.addMaterial(material);
        return Result.success();    //告诉前端接受到了请求，请求成功
    }

    @GetMapping("/List")
    public Result SelectMaterials() {
        List<Material> materials = materialService.SelectMaterials();
        return Result.success(materials);
        // return Result.success(employeeMapper.SelectEmployees());
    }

    @GetMapping("/Page")
    public Result Page(MaterialPageRequest materialPageRequest) {
        return Result.success(materialService.page(materialPageRequest));
    }

    @GetMapping("/Listmaterialtag")
    public Result listMaterialtag() {
            return Result.success(materialService.listMaterialtag());
    }

    @GetMapping("/addlabel")
    public Result addlabel() {
        return Result.success(materialService.addlabel());
    }

    @GetMapping("/countMaterial")
    public Result listcountMaterial(){
        return Result.success( materialService.listcountMaterial());
    }
}
