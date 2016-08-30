package com.szilardolah.amusementpark.repository;

import com.szilardolah.amusementpark.entity.Machine;
import java.util.Collection;
import javax.persistence.TypedQuery;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class MachineRepository extends EntityRepository{

    public Collection<Machine> values() {
        TypedQuery<Machine> query = entityManager
                .createQuery("SELECT m From Machine m", Machine.class);       
        return query.getResultList();
    }
        
}
