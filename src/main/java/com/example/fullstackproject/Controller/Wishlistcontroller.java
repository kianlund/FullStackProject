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

import java.util.List;

@Controller
public class Wishlistcontroller {

    @Autowired
    Service service;

    @GetMapping ("/wishlist")
    public String wishlist(Model model, @ModelAttribute User user, HttpSession session){
            User tempUser = (User) session.getAttribute("currentUser");
            model.addAttribute(tempUser.getUsername());

                List<Wishlist> wishlists = service.fetchAllWishLists(tempUser);
                System.out.println(tempUser);
                model.addAttribute("wishlists", wishlists);
                return "wishlist.html";


    }

}
