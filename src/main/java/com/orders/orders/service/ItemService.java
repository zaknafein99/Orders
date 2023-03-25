package com.orders.orders.service;

import com.orders.orders.domain.Item;
import com.orders.orders.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final Logger logger = LoggerFactory.getLogger(ItemService.class);

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item CreateItem(Item item){
        return itemRepository.save(item);
    }

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    public void deleteItem(Long itemId){
        Optional<Item> item = itemRepository.findById(itemId);
        if (item.isPresent()) {
            itemRepository.delete(item.get());

        } else {
            logger.warn("Item with id {} not found", itemId);
        }
    }
}
