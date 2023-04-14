package com.example.fullstackproject.Model;

public class Item {
    private int itemID;
    private String item;
    private String itemURL;
    private double price;
    private int wishlistID;
    private Integer reservedBy;

    public Item(){
        //Empty constructor
    }
    public Item(int itemID, String item, String itemURL, double price,int wishlistID){
        this.itemID = itemID;
        this.item = item;
        this.itemURL = itemURL;
        this.price = price;
        this.wishlistID = wishlistID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItemURL() {
        return itemURL;
    }

    public void setItemURL(String itemURL) {
        this.itemURL = itemURL;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(Integer reservedBy) {
        this.reservedBy = reservedBy;
    }

    public int getWishlistID() {
        return wishlistID;
    }

    public void setWishlistID(int wishlistID) {
        this.wishlistID = wishlistID;
    }
}
