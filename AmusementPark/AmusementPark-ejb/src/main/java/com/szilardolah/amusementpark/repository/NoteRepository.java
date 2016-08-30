package com.szilardolah.amusementpark.repository;

import com.szilardolah.amusementpark.entity.Note;
import java.util.Collection;
import javax.persistence.TypedQuery;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class NoteRepository extends EntityRepository{

    public Collection<Note> getNoteByGuest(Long parkId, Long guestId) {
        TypedQuery<Note> query = entityManager
                .createNamedQuery("Note.getNoteByGuest", Note.class)
                .setParameter("park_id", parkId)
                .setParameter("guest_id", guestId);
        return query.getResultList();
    }
}
