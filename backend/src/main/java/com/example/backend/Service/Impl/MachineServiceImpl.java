package com.example.backend.Service.Impl;

import com.example.backend.Controller.request.MachinePageRequest;
import com.example.backend.Entity.Employee;
import com.example.backend.Entity.Machine;
import com.example.backend.Mapper.EmployeeMapper;
import com.example.backend.Mapper.MachineMapper;
import com.example.backend.Service.MachineService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class MachineServiceImpl implements MachineService {
    @Autowired
    MachineMapper machineMapper;

    @Override
    public void deleteMachine(String id) {
        machineMapper.deleteMachine(id);
    }

    @Override
    public void updateMachine(Machine machine) {
        machineMapper.updateMachine(machine);
    }

    @Override
    public void addMachine(Machine machine) {
        machineMapper.addMachine(machine);
    }

    @Override
    public List<Machine> SelectMachines() {
        return machineMapper.SelectMachines();
    }

    @Override
    public Object page(MachinePageRequest machinePageRequest) {
        PageHelper.startPage(machinePageRequest.getPageNum(), machinePageRequest.getPageSize());
        List<Machine> machines = machineMapper.listByCondition(machinePageRequest);
        return new PageInfo<>(machines);
    }

    @Override
    public List<Map<String, Object>> getMachineTypeCounts() {
        return machineMapper.getMachineTypeCounts();
    }

    @Override
    public List<Map<String, Object>> getMachineNameCounts() {
        return machineMapper.getMachineNameCounts();
    }
}
