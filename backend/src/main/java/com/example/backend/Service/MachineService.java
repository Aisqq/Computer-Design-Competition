package com.example.backend.Service;

import com.example.backend.Controller.request.MachinePageRequest;
import com.example.backend.Entity.Machine;

import java.util.List;
import java.util.Map;

public interface MachineService {
    void deleteMachine(String id);

    void updateMachine(Machine machine);

    void addMachine(Machine machine);

    List<Machine> SelectMachines();

    Object page(MachinePageRequest machinePageRequest);

    List<Map<String, Object>> getMachineTypeCounts();

    List<Map<String, Object>> getMachineNameCounts();
}
