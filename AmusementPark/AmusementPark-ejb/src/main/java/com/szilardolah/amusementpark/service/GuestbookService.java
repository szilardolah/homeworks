package com.szilardolah.amusementpark.service;

import com.szilardolah.amusementpark.entity.Guestbook;
import com.szilardolah.amusementpark.repository.AmusementParkRepository;
import com.szilardolah.amusementpark.repository.GuestRepository;
import com.szilardolah.amusementpark.repository.GuestbookRepository;
import com.szilardolah.amusementpark.repository.NoteRepository;
import com.szilardolah.amusementpark.util.Util;
import java.io.Serializable;
import java.util.Collection;
import javax.inject.Inject;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class GuestbookService implements Serializable {

    @Inject
    private AmusementParkRepository parkRepo;
    
    @Inject
    private GuestbookRepository bookRepo;
   
    @Inject
    private GuestRepository guestRepo;
    
    @Inject
    private NoteRepository noteRepo;
    
    public Guestbook get(Long id) {
       return Util.checkExistence(Guestbook.class, bookRepo, id);
    }

    public Collection<Guestbook> values() {
        return bookRepo.values();
    }

    
    
}
