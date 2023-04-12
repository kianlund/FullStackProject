package com.example.fullstackproject.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@org.springframework.stereotype.Service
public class Service {

    @Autowired
    Repo repo;
        //fetches all the wishlists from the database
    public List<wishList> fetchAll(){

        return repo.fetchAll();
    }
        //fetches all the users from the database
    public List<User> fetcAll(){
        return repo.fetchAll();
    }
        //Måske overflødig, da det lidt er den samme som addItem første gang
    public void addWishList(wishList w){
        repo.addWishList(w);
    }
    public void addItem(Item i){
        repo.addItem(i);
    }
        /*Det er lidt tid siden jeg har lavet validering, så kan ikke lige huske det, men ideen er at den tjekker
         inputtet mod det den har modtaget fra databasen.
         */
    public boolean validation(String userName, String password, String inputName, String inputPassword){
        if(userName.equals(inputName) && password.equals(inputPassword))
            return true;
        else
            return false;

    }


    public Wishlist findWishListById(int id){

        return null;
    }

    public boolean deleteWishList(int id){

        return true;
    }

    public boolean deleteItem(int id){

        return true;
    }


    public void updateWishList(int id, Wishlist w){

    }
}
