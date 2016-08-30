package com.szilardolah.amusementpark.repository;

import com.szilardolah.amusementpark.entity.Guest;
import java.util.Collection;
import javax.persistence.TypedQuery;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class GuestRepository extends EntityRepository{

    public Collection<Guest> values() {
        TypedQuery<Guest> query = entityManager
                .createQuery("SELECT g From Guest g", Guest.class);       
        return query.getResultList();
    }
        
    public Collection<Guest> listGuestsOfMachine(Long parkId, Long machineId) {
        TypedQuery<Guest> query = entityManager
                .createNamedQuery("Guest.listGuestsOfMachine", Guest.class)
                .setParameter("park_id", parkId)
                .setParameter("machine_id", machineId);
        return query.getResultList();
    }
    
    public Collection<Guest> getGuestsByState(Long parkId) {
        TypedQuery<Guest> query = entityManager
                .createNamedQuery("Guest.guestListWithState", Guest.class)
                .setParameter("park_id", parkId);
        return query.getResultList();
    }
    
}
