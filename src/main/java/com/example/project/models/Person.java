package com.example.project.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "The name should not be empty")

    @Column(name = "full_name")
    private String fullName;


    @Column(name = "phone_number")
    private String  phoneNumber;

    @OneToMany(mappedBy = "owner")
    private List<Item> items;


    public Person() {

    }

    public Person(String fullName, String phoneNumber) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {


        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Item> getItems() {
        return items;
    }


    public List<String> showItems(){
       List<Item> list = this.getItems();
       List<String> stringList = new ArrayList<>();
       for (Item item: list){
          stringList.add( item.getItemName());
       }
       return stringList;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
        item.setOwner(this);
    }

    public void removeItem(Item item) {
        items.remove(item);
        item.setOwner(null);
    }

    @Override
    public String toString() {
        return getFullName();
    }
}