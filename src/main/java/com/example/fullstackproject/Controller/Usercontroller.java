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
public class Usercontroller {

    @Autowired
    Service service;
    @GetMapping("/")
    public String index(Model model, HttpSession session){
        if (session.getAttribute("currentUser") != null){
            return "redirect:/";
        }
        List<User> userList = service.fetchAll();
        model.addAttribute("userList", userList);
        return "/index";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user, WebRequest wr){
        user.setUsername(wr.getParameter("username"));
        user.setPassword(wr.getParameter("password"));
        service.addUser(user);
        return ("redirect:/");
    }

    @PostMapping("/wishlist")
    public String login(Model model, @ModelAttribute User user, WebRequest wr, HttpSession session){
        user.setUsername(wr.getParameter("username"));
        user.setPassword(wr.getParameter("password"));
        List<User> list = service.fetchAll();
        if (service.validation(user)){
            session.setAttribute("currentUser", list.get(list.size()-1)); //Gets the most recent user(Last user in list)
            return "redirect:/wishlist";
        }

        return ("/index");
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
