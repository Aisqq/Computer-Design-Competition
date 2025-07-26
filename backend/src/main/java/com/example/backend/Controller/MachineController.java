package com.example.backend.Controller;

import com.example.backend.Controller.request.MachinePageRequest;
import com.example.backend.Entity.Machine;
import com.example.backend.Service.MachineService;
import com.example.backend.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin            //解决跨域问题
@RestController
@RequestMapping("/Device")
public class MachineController {

    @Autowired
    MachineService machineService;

    @DeleteMapping("/delete/{id}")
    public Result deleteMachine(@PathVariable String id) {
        machineService.deleteMachine(id);
        return Result.success();
    }

    @PostMapping("/update")
    public Result updateMachine(@RequestBody Machine  machine){
        machineService.updateMachine(machine);
        return Result.success();
    }

    @PostMapping("/add")//增加用户的前端接口
    public Result addMachine(@RequestBody Machine machine) {//将json转为实体对象
        machineService.addMachine(machine);
        return Result.success();    //告诉前端接受到了请求，请求成功
    }

    @GetMapping("/List")
    public Result SelectMachines() {
        List<Machine> machines = machineService.SelectMachines();
        return Result.success(machines);
        // return Result.success(employeeMapper.SelectEmployees());
    }

    @GetMapping("/Page")
    public Result Page(MachinePageRequest machinePageRequest) {
        return Result.success(machineService.page(machinePageRequest));
        // return Result.success(employeeMapper.SelectEmployees());
    }

    @GetMapping("/Chartdataout")
    public Result getChartdatainside() {
        List<Map<String, Object>> list = machineService.getMachineTypeCounts();
        return Result.success(list);
    }
    @GetMapping("/Chartdatainside")
    public Result getChartdataout() {
        List<Map<String, Object>> list = machineService.getMachineNameCounts();
        return Result.success(list);
    }

}
