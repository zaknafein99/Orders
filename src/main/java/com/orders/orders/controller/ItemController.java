package com.orders.orders.controller;

import com.orders.orders.domain.Item;
import com.orders.orders.repository.ItemRepository;
import com.orders.orders.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemRepository itemRepository, ItemService itemService) {
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
}
