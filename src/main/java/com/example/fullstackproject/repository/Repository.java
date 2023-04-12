package com.example.fullstackproject.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;

public class Repository {

    @Autowired
    JdbcTemplate dbaccess;

    public Repository(){
        //Empty constructor
    }

    public void addUser(String username, String password){

    }

    public void addWishlist(int userID){

    }

    public void addItem(int wishlistID, String item, String itemURL, double itemPrice){

    }

    public void deleteWishlist(int wishlistID){ //Delete wishlist and all items belonging to it

    }

    public void deleteItem(int itemID){ //removes one row from item database

    }

    public ArrayList getUser(int userID){
        return;
    }

    public ArrayList getUserList(){ //returns all userIDs in an arraylist
        return;
    }

    public ArrayList getWishlistList(int userID){ //returns all wishlist IDs in an arraylist
        return;
    }

    public ArrayList getItemList(int wishlistID){ //returns all itemIDs in an arraylist
        return;
    }
}
