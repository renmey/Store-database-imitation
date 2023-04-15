package com.example.project.repositories;

import com.example.project.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Integer> {

    List<Item> findByOwnerId(int ownerId);

}