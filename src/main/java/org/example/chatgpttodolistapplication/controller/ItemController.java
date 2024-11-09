package org.example.chatgpttodolistapplication.controller;

import org.example.chatgpttodolistapplication.model.Item;
import org.example.chatgpttodolistapplication.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        itemService.saveItem(item);
        return item;
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item item) {
        Item existingItem = itemService.getItemById(id);
        if (existingItem != null) {
            item.setId(id);
            itemService.updateItem(item);
            return item;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }
}
