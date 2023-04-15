package com.example.fullstackproject.Controller;

import com.example.fullstackproject.Model.User;
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
public class DELETEMECONTROLLER {

    @Autowired
    Service service;

    @PostMapping("/test")
    public String test(Model model, @ModelAttribute User user, HttpSession session){
        User tempUser = (User) session.getAttribute("currentUser");
        model.addAttribute(tempUser.getUsername());
        if (tempUser.getUserID() == 1){
            System.out.println("YAAAAY!");
        }

        return ("/index");
    }
}
