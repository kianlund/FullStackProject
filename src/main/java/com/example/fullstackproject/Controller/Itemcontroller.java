package com.example.fullstackproject.Controller;

import com.example.fullstackproject.Model.Item;
import com.example.fullstackproject.Model.User;
import com.example.fullstackproject.Model.Wishlist;
import com.example.fullstackproject.Service.Service;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Controller
public class Itemcontroller {

    @Autowired
    Service service;

    @GetMapping("/item/")
    public String itemHome(HttpSession session, Model model){
        Wishlist tempWishlist = (Wishlist) session.getAttribute("currentWishlist");
        List<Item> list = service.fetchAllWishlistItems(tempWishlist);
        model.addAttribute("itemList", list);
        return "/item";
    }

    @PostMapping("/addItem")
    public String addUser(Model model, @ModelAttribute Item item, WebRequest wr, HttpSession session){
        Wishlist tempWishlist = (Wishlist) session.getAttribute("currentWishlist");
        item.setItem(wr.getParameter("item"));
        item.setItemURL(wr.getParameter("itemURL"));
        item.setPrice(Double.parseDouble(wr.getParameter("price")));
        item.setWishlistID(tempWishlist.getWishlistID());

        service.addItem(item);

        List<Item> list = service.fetchAllWishlistItems(tempWishlist);
        model.addAttribute("itemList",list);

        return ("/items");
    }

    @PostMapping("/deleteItem")
    public String deleteItem(Model model, WebRequest wr, HttpSession session){
        Wishlist tempWishlist = (Wishlist) session.getAttribute("currentWishlist");
        int tempID = Integer.parseInt(wr.getParameter("itemID"));

        service.deleteItem(tempID);

        List<Item> list = service.fetchAllWishlistItems(tempWishlist);
        model.addAttribute("itemList",list);

        return ("/items");
    }

    @PostMapping("/editItem")
    public String editItem(Model model, WebRequest wr, HttpSession session){
        // How do dis ???
        return ("/items");
    }

    @PostMapping("/reserveItem")
    public String reserveItem(Model model, WebRequest wr, HttpSession session){
        Wishlist tempUser = (Wishlist) session.getAttribute("currentUser");
        Wishlist tempWishlist = (Wishlist) session.getAttribute("currentWishlist");
        int tempItemID = Integer.parseInt(wr.getParameter("itemID"));
        Item tempItem = service.findItemById(tempItemID);
        tempItem.setReservedBy(tempUser.getUserID());

        service.updateItemReservation(tempItem);

        List<Item> list = service.fetchAllWishlistItems(tempWishlist);
        model.addAttribute("itemList",list);

        return ("/items");
    }

    @PostMapping("/unreserveItem")
    public String unreserveItem(Model model, WebRequest wr, HttpSession session){
        Wishlist tempWishlist = (Wishlist) session.getAttribute("currentWishlist");
        int tempItemID = Integer.parseInt(wr.getParameter("itemID"));

        Item tempItem = service.findItemById(tempItemID);
        tempItem.setReservedBy(null);

        service.updateItemReservation(tempItem);

        List<Item> list = service.fetchAllWishlistItems(tempWishlist);
        model.addAttribute("itemList",list);

        return ("/items");
    }
}
