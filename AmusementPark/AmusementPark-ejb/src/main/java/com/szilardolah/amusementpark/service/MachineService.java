package com.szilardolah.amusementpark.service;

import com.szilardolah.amusementpark.entity.Machine;
import com.szilardolah.amusementpark.repository.MachineRepository;
import com.szilardolah.amusementpark.util.Util;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Stateless
public class MachineService implements Serializable {

    @Inject
    private MachineRepository machineRepo;
    
    public Machine createMachine(Machine entity) {
        return machineRepo.create(entity);
    }
    
    public Machine deleteMachine(Long id) {
        Util.checkExistence(Machine.class, machineRepo, id);
        return machineRepo.delete(Machine.class, id);
    }
    
    public Machine modifyMachine(Machine entity) {
        Util.checkExistence(Machine.class, machineRepo, entity.getId());
        return machineRepo.update(entity);
    }

    public Machine get(Long id) {
        return Util.checkExistence(Machine.class, machineRepo, id);
    }

    public Collection<Machine> values() {
        return machineRepo.values();
    }

}
