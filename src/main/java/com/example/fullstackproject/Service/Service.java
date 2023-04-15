package com.example.fullstackproject.Service;

import com.example.fullstackproject.Model.Item;
import com.example.fullstackproject.Model.User;
import com.example.fullstackproject.Model.Wishlist;
import com.example.fullstackproject.Repository.RepositoryV2;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@org.springframework.stereotype.Service
public class Service {

    @Autowired
    RepositoryV2 repo;

        //fetches all the wishlists from the database
    public List<Wishlist> fetchAllWishLists(User user){
//        List<Wishlist> list = repo.fetchAllUserWishlists(user.getUserID());
        return repo.fetchAllUserWishlists(user.getUserID());
    }

    //fetches all the users from the database
    public List<User> fetchAll(){
        return repo.fetchAllUsers();
    }

    public List<Item> fetchAllWishlistItems(Wishlist wishlist){
        return repo.fetchAllWishlistItems(wishlist.getWishlistID());
    }
    //Måske overflødig, da det lidt er den samme som addItem første gang
    public void addWishList(Wishlist w){
        repo.addWishlist(w);
    }

    public void addUser(User u){
        repo.addUser(u);
    }
    public void addItem(Item i){
        repo.addItem(i);
    }
        /*Det er lidt tid siden jeg har lavet validering, så kan ikke lige huske det, men ideen er at den tjekker
         inputtet mod det den har modtaget fra databasen.
         */
        public boolean validation(User user){
//            int id = repo.getUserIDByName(userName);
            List<User> tempUsers = repo.fetchAllUsers();
            for (User listUser: tempUsers) {
                if(user.getUsername().equals(listUser.getUsername()) && user.getPassword().equals(listUser.getPassword())){
                    return true;
                }
            }
            return false;
        }
            //finder specific bruger i en liste af brugere
    public User findUserById(int id){
        return repo.getUserByID(id);
    }
        //finder specific ønskeliste i liste af ønskelister
    public Wishlist findWishListById(int id){

        return repo.getWishlistByID(id);
    }

    public Item findItemById(int id){
        return repo.getItemByID(id);
    }

    public void deleteWishList(int id){
        repo.deleteWishlistByID(id);
    }

    public void deleteItem(int id){
        repo.deleteItemByID(id);
    }

    public void updateItem(Item item){
        repo.updateItem(item);
    }

    public void updateItemReservation(Item item){
        repo.updateItemReservation(item);
    }

    public void removeItemReservation(Item item){
        repo.removeItemReservation(item);
    }
}
