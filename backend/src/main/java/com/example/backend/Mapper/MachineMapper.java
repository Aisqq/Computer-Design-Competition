package com.example.backend.Mapper;

import com.example.backend.Controller.request.MachinePageRequest;
import com.example.backend.Entity.Machine;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MachineMapper {

    void deleteMachine(String id);

    void updateMachine(Machine machine);

    void addMachine(Machine machine);

    List<Machine> SelectMachines();

    List<Machine> listByCondition(MachinePageRequest machinePageRequest);

    List<Map<String, Object>> getMachineTypeCounts();

    List<Map<String, Object>> getMachineNameCounts();
}
