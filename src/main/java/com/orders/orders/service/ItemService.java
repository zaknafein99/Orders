package com.orders.orders.service;

import com.orders.orders.domain.Item;
import com.orders.orders.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item CreateItem(Item item){
        return itemRepository.save(item);
    }
}
