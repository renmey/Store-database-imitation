package com.example.project.services;

import com.example.project.models.Item;
import com.example.project.models.Person;
import com.example.project.repositories.ItemsRepository;
import com.example.project.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ItemsService {

    private final ItemsRepository itemsRepository;

    @Autowired
    public ItemsService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public List<Item> findAll() {

            return itemsRepository.findAll();
    }



    public Item findOne(int id) {
        Optional<Item> foundItem = itemsRepository.findById(id);
        return foundItem.orElse(null);
    }



    @Transactional
    public void save(Item item) {

        itemsRepository.save(item);
    }

    @Transactional
    public void update(int id, Item updatedItem) {



        updatedItem.setId(id);


        itemsRepository.save(updatedItem);
    }

    @Transactional
    public void delete(int id) {
        itemsRepository.deleteById(id);
    }


    public Person getItemOwner(int id) {

        return itemsRepository.findById(id).map(Item::getOwner).orElse(null);
    }


    public List<Item> getItemsByOwnerId(int id) {

       return itemsRepository.findByOwnerId(id);
    }
}
