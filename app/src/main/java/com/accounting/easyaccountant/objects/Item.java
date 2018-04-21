package com.accounting.easyaccountant.objects;

/**
 * Created by ahmed on 4/19/2018.
 */

public class Item {
    int id ;
    String name ;
    String disc ;
    String unit ;
    float price ;
    float quantity ;
    float discount ;
    float total ;

    public Item(int id, String name, String disc, String unit, float price, float quantity, float discount, float total) {
        this.id = id;
        this.name = name;
        this.disc = disc;
        this.unit = unit;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
        this.total = total;
    }

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
