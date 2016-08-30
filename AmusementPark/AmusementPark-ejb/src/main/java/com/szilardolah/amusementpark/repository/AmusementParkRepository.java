package com.szilardolah.amusementpark.repository;

import com.szilardolah.amusementpark.entity.AmusementPark;
import com.szilardolah.amusementpark.entity.BuyedMachine;
import java.util.Collection;
import javax.persistence.TypedQuery;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class AmusementParkRepository extends EntityRepository{

    public Collection<AmusementPark> values() {
        TypedQuery<AmusementPark> query = entityManager
                .createQuery("SELECT a From AmusementPark a", AmusementPark.class);       
        return query.getResultList();
    }
    
    public Collection<BuyedMachine> machineValues(Long parkId) {
        TypedQuery<BuyedMachine> query = entityManager
                .createNamedQuery("AmusementPark.machineList", BuyedMachine.class)
                .setParameter("park_id", parkId);
        return query.getResultList();
    }

    
    public Collection<AmusementPark> getParksWithNonZeroFund() {
        TypedQuery<AmusementPark> query = entityManager
                .createNamedQuery("AmusementPark.listParksWithFund", AmusementPark.class);
        return query.getResultList();
    }
    
    
}
