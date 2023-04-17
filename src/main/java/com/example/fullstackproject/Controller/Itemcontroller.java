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

    @GetMapping("/item")
    public String item(HttpSession session, Model model){
        Wishlist tempWishlist = (Wishlist) session.getAttribute("currentWishlist");
        User tempUser = (User) session.getAttribute("currentUser");
        List<Item> list = service.fetchAllWishlistItems(tempWishlist);
        model.addAttribute("itemList", list);
        model.addAttribute("currentWishlist", tempWishlist);
        model.addAttribute("currentUser", tempUser);
        return "/item";
    }

    @PostMapping("/addItem")
    public String addItem(@ModelAttribute Item item, WebRequest wr, HttpSession session){
        Wishlist tempWishlist = (Wishlist) session.getAttribute("currentWishlist");

        item.setItem(wr.getParameter("item"));
        item.setItemURL(wr.getParameter("itemURL"));
        item.setPrice(Double.parseDouble(wr.getParameter("itemPrice")));
        item.setWishlistID(tempWishlist.getWishlistID());

        service.addItem(item);

        return ("redirect:/item");
    }

    @PostMapping("/deleteItem")
    public String deleteItem(WebRequest wr, HttpSession session){
        int tempID = Integer.parseInt(wr.getParameter("itemID"));
        service.deleteItem(tempID);

        return ("redirect:/item");
    }

    @PostMapping("/editItem")
    public String editItem(Model model, WebRequest wr, HttpSession session){
        // How do dis ???
        return ("/items");
    }

    @PostMapping("/reserveItem")
    public String reserveItem(WebRequest wr, HttpSession session){
        Wishlist tempUser = (Wishlist) session.getAttribute("currentUser");
        int tempItemID = Integer.parseInt(wr.getParameter("itemID"));
        Item tempItem = service.findItemById(tempItemID);
        tempItem.setReservedBy(tempUser.getUserID());

        service.updateItemReservation(tempItem);

        return ("redirect:/item");
    }

    @PostMapping("/unreserveItem")
    public String unreserveItem(WebRequest wr){
        int tempItemID = Integer.parseInt(wr.getParameter("itemID"));

        Item tempItem = service.findItemById(tempItemID);
        tempItem.setReservedBy(null);

        service.updateItemReservation(tempItem);
        return ("redirect:/item");
    }
}
