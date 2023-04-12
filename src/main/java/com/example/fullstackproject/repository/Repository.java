package com.example.fullstackproject.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository
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
    public void deleteItemByID(int itemID){ //removes one row from item database
        String sql = "DELETE FROM wishlistitem WHERE itemID = ?";
        dbaccess.update(sql,itemID);
    }

    public void deleteWishlistByID(int wishlistID){ //Delete wishlist and all items belonging to it
        //First remove all items belonging to wishlist
        String sql = "DELETE FROM wishlistitem WHERE wishlistID = ?";
        dbaccess.update(sql,wishlistID);

        //Remove wishlist
        sql = "DELETE FROM wishlist WHERE wishlistID = ?";
        dbaccess.update(sql,wishlistID);
    }

    //Is getUser needed at all?????
//    public ArrayList getUserByID(int userID){
//        String sql = "SELECT * FROM accounts WHERE userID = ?";
//        SqlRowSet rowSet;
//        ArrayList<String> list = new ArrayList<>();
//
//        rowSet = dbaccess.queryForRowSet(sql);
//
//        while (rowSet.next()){
//            list.add(rowSet.getString("username"));
//            list.add(rowSet.getString("password"));
//        }
//
//        return list;
//    }

    public ArrayList<Integer> getUserIDList(){ //returns all userIDs in an arraylist
        //TODO: Consider other methods? RowMapper?
        //ArrayList wont accept 'int' as datatype, using Integer instead, worth looking into?
        String sql = "SELECT userID FROM accounts";
        List<Integer> tempList = dbaccess.queryForList(sql, Integer.class);
        ArrayList<Integer> list = new ArrayList<>(tempList);

        return list;
    }

    public ArrayList<String> getUsernameList(){ //returns all userrnames in an arraylist
        //TODO: Consider other methods? RowMapper?
        //ArrayList wont accept 'int' as datatype, using Integer instead, worth looking into?
        String sql = "SELECT username FROM accounts";
        List<String> tempList = dbaccess.queryForList(sql, String.class);
        ArrayList<String> list = new ArrayList<>(tempList);

        return list;
    }

    public ArrayList getUserWishlists(int userID){ //returns all wishlist IDs in an arraylist
        String sql = "SELECT wishlistID FROM wishlist";
        List<Integer> tempList = dbaccess.queryForList(sql, Integer.class);
        ArrayList<Integer> list = new ArrayList<>(tempList);

        return list;
    }

    public ArrayList getItemList(int wishlistID){ //returns all itemIDs in an arraylist
        String sql = "SELECT itemID FROM wishlistitem";
        List<Integer> tempList = dbaccess.queryForList(sql, Integer.class);
        ArrayList<Integer> list = new ArrayList<>(tempList);

        return list;
    }

    public int getUserIDByName(String username){
        int id;
        String sql = "SELECT userID FROM accounts WHERE username = ?";
        id = dbaccess.queryForObject(sql, Integer.class, username);

        return id;
    }
}
