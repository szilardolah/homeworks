package com.szilardolah.amusementpark.service;

import com.szilardolah.amusementpark.entity.Guest;
import com.szilardolah.amusementpark.repository.GuestRepository;
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
public class GuestService implements Serializable {

    @Inject
    private GuestRepository guestRepo;
    
    
    public Guest createGuest(Guest entity) {
        return guestRepo.create(entity);
    }
    
    public Guest deleteGuest(Long id) {
        Util.checkExistence(Guest.class, guestRepo, id);
        return guestRepo.delete(Guest.class, id);
    }
    
    public Guest modifyGuest(Guest entity) {
        Util.checkExistence(Guest.class, guestRepo, entity.getId());
        return guestRepo.update(entity);
    }

    public Guest get(Long id) {
       return Util.checkExistence(Guest.class, guestRepo, id);
    }

    public Collection<Guest> values() {
        return guestRepo.values();
    } 
        
    public Collection<Guest> getGuestsOfMachine(Long parkId, Long guestId) {
        return guestRepo.listGuestsOfMachine(parkId, guestId);
    }
    
    public Collection<Guest> getGuestsByState(Long parkId) {
        return guestRepo.getGuestsByState(parkId);
    }
}
