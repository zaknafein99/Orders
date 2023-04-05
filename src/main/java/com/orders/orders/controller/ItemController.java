package com.orders.orders.controller;

import com.orders.orders.domain.Item;
import com.orders.orders.service.ItemService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @PostMapping("")
    public Item createItem(@RequestBody Item item) {
        return itemService.CreateItem(item);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long itemId){
        try {
            itemService.deleteItem(itemId);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
