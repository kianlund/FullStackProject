package com.example.fullstackproject.Controller;


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
public class Wishlistcontroller {

    @Autowired
    Service service;

    @GetMapping ("/wishlist")
    public String wishlist(Model model, @ModelAttribute User user, HttpSession session){
            User tempUser = (User) session.getAttribute("currentUser");
            model.addAttribute(tempUser);
            List<Wishlist> wishlists = service.fetchAllWishLists(tempUser);
            model.addAttribute("wishlists", wishlists);
            return "wishlist";
    }

    @PostMapping("/addWishlist")
    public String addWishlist(Model model, @ModelAttribute Wishlist wishlist, HttpSession session){
        User tempUser = (User) session.getAttribute("currentUser");
        wishlist.setUserID(tempUser.getUserID());
        service.addWishList(wishlist);

        session.setAttribute("currentWishlist", wishlist);
        List<Wishlist> wishlists = service.fetchAllWishLists(tempUser);
        model.addAttribute("wishlists", wishlists);
        return ("redirect:/wishlist");
    }

    @PostMapping("/editWishlist")
    public String editWishlist(Model model, HttpSession session, WebRequest wr){
        int id = Integer.parseInt(wr.getParameter("id"));
        Wishlist wishlist = service.findWishListById(id);
        session.setAttribute("currentWishlist", wishlist);
        return ("redirect:/item");
    }

}
