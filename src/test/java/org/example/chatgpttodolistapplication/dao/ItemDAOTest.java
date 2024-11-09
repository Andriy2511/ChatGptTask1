package org.example.chatgpttodolistapplication.dao;

import org.example.chatgpttodolistapplication.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemDAOTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private ItemDAO itemDAO;

    private Item item;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        item = new Item(1L, "Test Title", "Test Description");
    }

    @Test
    void saveItemTest() {
        doNothing().when(entityManager).persist(any(Item.class));
        itemDAO.save(item);
        verify(entityManager, times(1)).persist(item);
    }

    @Test
    void updateItemTest() {
        doReturn(item).when(entityManager).merge(any(Item.class));
        itemDAO.update(item);
        verify(entityManager, times(1)).merge(item);
    }

    @Test
    void deleteItemTest() {
        doNothing().when(entityManager).remove(any(Item.class));
        itemDAO.delete(item);
        verify(entityManager, times(1)).remove(item);
    }

    @Test
    void getItemByIdTest() {
        when(entityManager.find(Item.class, 1L)).thenReturn(item);
        Item foundItem = itemDAO.get(1L);
        assertNotNull(foundItem);
        assertEquals("Test Title", foundItem.getTitle());
    }
}
