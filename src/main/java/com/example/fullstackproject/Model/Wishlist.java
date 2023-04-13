package com.example.fullstackproject.Model;

public class Wishlist {
    private int wishlistID;
    private int userID;

    public Wishlist(int wishlistID, int userID){
        this.userID = userID;
        this.wishlistID = wishlistID;
    }

    public int getWishlistID() {
        return wishlistID;
    }

    public void setWishlistID(int wishlistID) {
        this.wishlistID = wishlistID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
