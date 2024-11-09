package org.example.chatgpttodolistapplication.service;

import org.example.chatgpttodolistapplication.dao.ItemDAO;
import org.example.chatgpttodolistapplication.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTest {

    @Mock
    private ItemDAO itemDAO;

    @InjectMocks
    private ItemService itemService;

    private Item item;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        item = new Item(1L, "Service Title", "Service Description");
    }

    @Test
    void saveItemTest() {
        itemService.saveItem(item);
        verify(itemDAO, times(1)).save(item);
    }

    @Test
    void updateItemTest() {
        itemService.updateItem(item);
        verify(itemDAO, times(1)).update(item);
    }

    @Test
    void deleteItemTest() {
        when(itemDAO.get(1L)).thenReturn(item);
        itemService.deleteItem(1L);
        verify(itemDAO, times(1)).delete(item);
    }

    @Test
    void getItemByIdTest() {
        when(itemDAO.get(1L)).thenReturn(item);
        Item foundItem = itemService.getItemById(1L);
        assertNotNull(foundItem);
        assertEquals("Service Title", foundItem.getTitle());
        verify(itemDAO, times(1)).get(1L);
    }
}
