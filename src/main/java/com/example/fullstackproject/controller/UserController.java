package com.example.fullstackproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/users")
    public String viewUsers(Model model) {
        String sql = "SELECT * FROM Accounts";
        List<Map<String, Object>> users = jdbcTemplate.queryForList(sql);
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping("/users")
    public String addUser(@RequestParam String username, @RequestParam String password) {
        String sql = "INSERT INTO Accounts(username, password) VALUES (?,?)";
        jdbcTemplate.update(sql, username, password);
        return "redirect:/users";

    }


}
