package com.example.backend.Controller;


import com.example.backend.Controller.request.warningPageRequest;
import com.example.backend.Entity.warning;
import com.example.backend.Service.warningService;
import com.example.backend.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//解决跨域问题
@CrossOrigin
@RestController
@RequestMapping("/security")
public class warningController {

    @Autowired
    warningService warningService;

    @GetMapping("/List")
    public Result Selectwarnings() {
        List<warning> Warnings = warningService.Selectwarnings();
        return Result.success(Warnings);
    }

    @GetMapping("/Page")
    public Result Page(warningPageRequest warningPageRequest) {
        return Result.success(warningService.page(warningPageRequest));
    }

    @GetMapping("/warningtype")
    public Result Selectwarningtype() {
        List<Map<String,Integer>> warningtype = warningService.Selectwarningtype();
        return Result.success(warningtype);
    }
    @GetMapping("/warninglevel")
    public Result Selectwarninglevel() {
        List<Map<Integer,String>> warninglevel = warningService.Selectwarninglevel();
        return Result.success(warninglevel);
    }
}
