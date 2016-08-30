package com.szilardolah.amusementpark.repository;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Stateless
public class EntityRepository implements Serializable{

    @PersistenceContext(unitName = "amusement_park_PU")
    protected transient EntityManager entityManager;

    public <T> T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public <T> T update(T entity) {
        return entityManager.merge(entity);
    }

    public <T> T find(Class<T> clazz, Long id) {
        return entityManager.find(clazz, id);
    }

    public <T> T delete(Class<T> clazz, Long id) {
        T entity = find(clazz, id);
        entityManager.remove(entity);
        return entity;
    }  
}
