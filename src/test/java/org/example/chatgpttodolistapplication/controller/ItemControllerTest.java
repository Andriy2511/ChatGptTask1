package org.example.chatgpttodolistapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.chatgpttodolistapplication.model.Item;
import org.example.chatgpttodolistapplication.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Autowired
    private ObjectMapper objectMapper;

    private Item item;

    @BeforeEach
    void setUp() {
        item = new Item(1L, "Controller Title", "Controller Description");
    }

    @Test
    void createItemTest() throws Exception {
        doNothing().when(itemService).saveItem(any(Item.class));

        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Controller Title"));

        verify(itemService, times(1)).saveItem(any(Item.class));
    }

    @Test
    void updateItemTest() throws Exception {
        when(itemService.getItemById(1L)).thenReturn(item);
        doNothing().when(itemService).updateItem(any(Item.class));

        mockMvc.perform(put("/items/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Controller Title"));

        verify(itemService, times(1)).updateItem(any(Item.class));
    }

    @Test
    void deleteItemTest() throws Exception {
        when(itemService.getItemById(1L)).thenReturn(item);
        doNothing().when(itemService).deleteItem(1L);

        mockMvc.perform(delete("/items/1"))
                .andExpect(status().isNoContent());

        verify(itemService, times(1)).deleteItem(1L);
    }

    @Test
    void getItemByIdTest() throws Exception {
        when(itemService.getItemById(1L)).thenReturn(item);

        mockMvc.perform(get("/items/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Controller Title"));

        verify(itemService, times(1)).getItemById(1L);
    }
}
