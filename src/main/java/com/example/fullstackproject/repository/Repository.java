package com.example.fullstackproject.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Repository {

    @Autowired
    JdbcTemplate dbaccess;

    public Repository(){
        //Empty constructor
    }

    public void addUser(String username, String password){
        String sql = "INSERT INTO accounts VALUES (?, ?)";
        dbaccess.update(sql,username,password);
    }

    public void addWishlist(int userID){
        String sql = "INSERT INTO wishlist VALUES (?)";
        dbaccess.update(sql,userID);
    }

    public void addItem(int wishlistID, String item, String itemURL, double itemPrice){
        //TODO: Add category? OR remove category column from db?
        String sql = "INSERT INTO wishlistitem (item, itemURL, price, wishlistID) VALUES (?, ?, ?, ?)";
        dbaccess.update(sql, item, itemURL, itemPrice, wishlistID);
    }
    public void deleteItem(int itemID){ //removes one row from item database
        String sql = "DELETE FROM wishlistitem WHERE itemID = ?";
        dbaccess.update(sql,itemID);
    }

    public void deleteWishlist(int wishlistID){ //Delete wishlist and all items belonging to it
        //First remove all items belonging to wishlist
        String sql = "DELETE FROM wishlistitem WHERE wishlistID = ?";
        dbaccess.update(sql,wishlistID);

        //Remove wishlist
        sql = "DELETE FROM wishlist WHERE wishlistID = ?";
        dbaccess.update(sql,wishlistID);
    }

    public ArrayList getUser(int userID){
        return;
    }

    public ArrayList<Integer> getUserList(){ //returns all userIDs in an arraylist
        //TODO: Consider other methods? RowMapper?
        //ArrayList wont accept 'int' as datatype, using Integer instead, worth looking into?
        ArrayList<Integer> list = new ArrayList<Integer>();
        String sql = "SELECT userID FROM accounts";
        ResultSetExtractor rse; //??
        ResultSet rs = dbaccess.query(sql); //

        try {
            while (rs.next()){

            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList getWishlistList(int userID){ //returns all wishlist IDs in an arraylist
        return;
    }

    public ArrayList getItemList(int wishlistID){ //returns all itemIDs in an arraylist
        return;
    }
}
