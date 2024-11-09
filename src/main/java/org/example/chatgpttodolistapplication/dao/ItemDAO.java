package org.example.chatgpttodolistapplication.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.chatgpttodolistapplication.model.Item;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Item item) {
        entityManager.persist(item);
    }

    public void update(Item item) {
        entityManager.merge(item);
    }

    public void delete(Item item) {
        entityManager.remove(item);
    }

    public Item get(Long id) {
        return entityManager.find(Item.class, id);
    }
}
