package com.szilardolah.amusementpark.service;

import com.szilardolah.amusementpark.entity.Note;
import com.szilardolah.amusementpark.repository.NoteRepository;
import java.io.Serializable;
import java.util.Collection;
import javax.inject.Inject;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class NoteService implements Serializable{

    @Inject
    private NoteRepository noteRepo;
    
    public Collection<Note> get(Long parkId, Long guestId) {
       return noteRepo.getNoteByGuest(parkId, guestId);
    }
    
    
}
