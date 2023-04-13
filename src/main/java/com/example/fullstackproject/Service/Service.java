package com.example.fullstackproject.Service;

import com.example.fullstackproject.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
@org.springframework.stereotype.Service
public class Service {

    @Autowired
    Repository repo;

        //fetches all the wishlists from the database
    public List<wishList> fetchAllWishLists(String username){
        int id = repo.getUserIDByName(username);
        int userID =0;
        for (int i: repo.getUserIDList()
             ) { if(i == id){
             userID = id;
        }

        };

        return repo.getUserWishlists(userID);
    }

        //fetches all the users from the database
    public List<User> fetchAll(){
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
    public boolean validation(String userName, String password){
        int id = repo.getUserIDByName(userName);
        ArrayList user = repo.getUserByID(id);
        if(userName.equals(user.get(0)) && password.equals(user.get(1)))
            return true;
        else
            return false;

    }
            //finder specific bruger i en liste af brugere
    public Wishlist findUserById(int id){

        return null;
    }
        //finder specific ønskeliste i liste af ønskelister
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
