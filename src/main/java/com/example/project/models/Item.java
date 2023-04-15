package com.example.project.models;

import jakarta.persistence.*;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


import java.util.Date;




@Entity
@Table(name = "item")
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "The name should not be empty")
    @Column(name = "item_name")
    private String itemName;


    @Min(value = 1)
    @Column(name = "price")
    private int price;



    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;







    public Item() {


    }

    public Item(String itemName, int price) {
        this.itemName = itemName;
        this.price = price;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Person getOwner() {


        return owner;
    }

    public String showOwner(){
        return this.owner.getFullName();

    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}