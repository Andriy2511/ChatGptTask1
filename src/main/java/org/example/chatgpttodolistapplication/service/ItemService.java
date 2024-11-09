package org.example.chatgpttodolistapplication.service;

import org.example.chatgpttodolistapplication.dao.ItemDAO;
import org.example.chatgpttodolistapplication.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemDAO itemDAO;

    @Autowired
    public ItemService(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    public void saveItem(Item item) {
        itemDAO.save(item);
    }

    public void updateItem(Item item) {
        itemDAO.update(item);
    }

    public void deleteItem(Long id) {
        Item item = itemDAO.get(id);
        if (item != null) {
            itemDAO.delete(item);
        }
    }

    public Item getItemById(Long id) {
        return itemDAO.get(id);
    }
}
