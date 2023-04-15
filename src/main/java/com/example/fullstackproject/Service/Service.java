package com.example.fullstackproject.Service;

import com.example.fullstackproject.Model.User;
import com.example.fullstackproject.Model.Wishlist;
import com.example.fullstackproject.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
@org.springframework.stereotype.Service
public class Service {

    @Autowired
    Repository repo;

        //fetches all the wishlists from the database
    public List<Wishlist> fetchAllWishLists(String username){
        int id = repo.getUserIDByName(username);
        int userID =0;
        for (int i: repo.getUserIDList()
             ) { if(i == id){
             userID = id;
        }

        };

        return repo.getUserWishlists(userID);
    }


        //Måske overflødig, da det lidt er den samme som addItem første gang
    public void addWishList(String userName){
        int userID = repo.getUserIDByName(userName);
        repo.addWishlist(userID);
    }
    public void addItem(int wishlistID, String item,String itemURL, double itemPrice){

        repo.addItem(wishlistID, item, itemURL, itemPrice);
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
    public ArrayList findUserById(String userName){
        int userID = repo.getUserIDByName(userName);

        return repo.getUserByID(userID);
    }
        //finder specific ønskeliste i liste af ønskelister
    public ArrayList findWishListById(int userid){

        return repo.getUserWishlists(userid);
    }

    public void deleteWishList(String userName){
        int userID = repo.getUserIDByName(userName);
        repo.deleteWishlistByID(userID);
    }

    public void deleteItem(int itemID){

        repo.deleteItemByID(itemID);

    }


    public void updateWishList(int wishlistID, String item,String itemURL, double itemPrice){

        repo.editItemByID(wishlistID, item, itemURL, itemPrice);
    }
}
