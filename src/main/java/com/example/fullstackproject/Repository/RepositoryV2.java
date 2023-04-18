package com.example.fullstackproject.Repository;

import com.example.fullstackproject.Model.Item;
import com.example.fullstackproject.Model.User;
import com.example.fullstackproject.Model.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

@org.springframework.stereotype.Repository
public class RepositoryV2 {

    @Autowired
    JdbcTemplate db;

    public RepositoryV2(){
        //Empty constructor
    }

    public void addUser(User user){
        String sql = "INSERT INTO accounts (username, password) VALUES (?, ?)";
        db.update(sql, user.getUsername(), user.getPassword());
    }

    public void addWishlist(Wishlist wishlist){
        String sql = "INSERT INTO wishlist (userID) VALUES (?)";
        db.update(sql, wishlist.getUserID());
    }

    public void addItem(Item item){
        String sql = "INSERT INTO wishlistitem (item, itemURL, price, wishlistID) VALUES (?, ?, ?, ?)";
        db.update(sql, item.getItem(), item.getItemURL(), item.getPrice(), item.getWishlistID());
    }

    public User getUserByID(int userID){
        String sql = "SELECT * FROM accounts WHERE userID = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        User u = db.queryForObject(sql, rowMapper, userID);
        return u;
    }

    public Wishlist getWishlistByID(int wishlistID){
        String sql = "SELECT * FROM wishlist WHERE wishlistID = ?";
        RowMapper<Wishlist> rowMapper = new BeanPropertyRowMapper<>(Wishlist.class);
        Wishlist w = db.queryForObject(sql, rowMapper, wishlistID);
        return w;
    }

    public Item getItemByID(int itemID){
        String sql = "SELECT * FROM wishlistitem WHERE itemID = ?";
        RowMapper<Item> rowMapper = new BeanPropertyRowMapper<>(Item.class);
        Item i = db.queryForObject(sql, rowMapper, itemID);
        return i;
    }

    public void deleteItemByID(int itemID){ //removes one row from item database
        String sql = "DELETE FROM wishlistitem WHERE itemID = ?";
        db.update(sql,itemID);
    }

    public void deleteWishlistByID(int wishlistID){ //Delete wishlist and all items belonging to it
        //First remove all items belonging to wishlist
        String sql = "DELETE FROM wishlistitem WHERE wishlistID = ?";
        db.update(sql,wishlistID);

        //Remove wishlist
        sql = "DELETE FROM wishlist WHERE wishlistID = ?";
        db.update(sql,wishlistID);
    }

    public List<User> fetchAllUsers(){
        String sql = "SELECT * FROM accounts";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return db.query(sql, rowMapper);
    }

    public List<Wishlist> fetchAllUserWishlists(int userID){
        String sql = "SELECT * FROM wishlist WHERE userID = ?";
        RowMapper<Wishlist> rowMapper = new BeanPropertyRowMapper<>(Wishlist.class);
        return db.query(sql, rowMapper, userID);
    }

    public List<Item> fetchAllWishlistItems(int wishlistID){
        String sql = "SELECT * FROM wishlistitem WHERE wishlistID = ?";
        RowMapper<Item> rowMapper = new BeanPropertyRowMapper<>(Item.class);
        return db.query(sql, rowMapper, wishlistID);
    }

    public void updateItem(Item item){
        String sql = "UPDATE wishlistitem SET item = ?, itemURL = ?, price = ? WHERE itemID = ?";
        db.update(sql, item.getItem(), item.getItemURL(), item.getPrice(), item.getItemID());
    }

    public void updateItemReservation(Item item){
        String sql = "UPDATE wishlistitem SET reservedBy = ? WHERE itemID = ?";
        db.update(sql, item.getReservedBy(), item.getItemID());
    }

    public void removeItemReservation(Item item){
        String sql = "UPDATE wishlistitem SET reservedBy = ? WHERE itemID = ?";
        db.update(sql, null, item.getItemID());
    }
}
