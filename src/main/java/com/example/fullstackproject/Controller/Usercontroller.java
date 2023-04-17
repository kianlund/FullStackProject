package com.example.fullstackproject.Controller;

import com.example.fullstackproject.Model.User;
import com.example.fullstackproject.Service.Service;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.util.List;

@Controller
public class Usercontroller {

    @Autowired
    Service service;
    @GetMapping("/")
    public String index(Model model, HttpSession session){
        if (session.getAttribute("currentUser") != null){
            return "redirect:/wishlist";
        }
        List<User> userList = service.fetchAll();
        model.addAttribute("userList", userList);
        return "/index";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user, WebRequest wr, Model model){
        user.setUsername(wr.getParameter("username"));
        user.setPassword(wr.getParameter("password"));

        try {
            service.addUser(user);
        } catch (Exception e){
            model.addAttribute("errorMsg", "Username already exists.");
            return "/index";
        }
        return ("redirect:/");
    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute User loginInfo, WebRequest wr, HttpSession session){
        loginInfo.setUsername(wr.getParameter("username"));
        loginInfo.setPassword(wr.getParameter("password"));
        Integer tempUserID = service.validation(loginInfo);
        if (tempUserID != null){
            User tempUser = service.findUserById(tempUserID);
            session.setAttribute("currentUser", tempUser); //Gets the most recent user(Last user in list)
            return "redirect:/wishlist";
        }

        return ("redirect:/");
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
