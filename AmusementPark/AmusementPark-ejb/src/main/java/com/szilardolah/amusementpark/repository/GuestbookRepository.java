package com.szilardolah.amusementpark.repository;

import com.szilardolah.amusementpark.entity.Guestbook;
import java.util.Collection;
import javax.persistence.TypedQuery;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class GuestbookRepository extends EntityRepository{

    public Collection<Guestbook> values() {
        TypedQuery<Guestbook> query = entityManager
                .createQuery("SELECT g From Guestbook g", Guestbook.class);       
        return query.getResultList();
    }
}
