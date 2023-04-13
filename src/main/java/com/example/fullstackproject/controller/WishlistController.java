package com.example.fullstackproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class WishlistController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/wishlist")
    public String viewWishlist(Model model) {
        String sql = "SELECT * FROM Wishlist";
        List<Map<String, Object>> users = jdbcTemplate.queryForList(sql);
        model.addAttribute("wishlists");
        return "wishlist";
    }

    @PostMapping("/wishlist")
    public String addWishlist(@RequestParam int userId, @RequestParam String date) {
        String sql = "INSERT INTO Wishlist(userID,date) VALUES(?,?)";
        jdbcTemplate.update(sql, userId, date);
        return "redirect:/wishlist";
    }

}
