package com.example.fullstackproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
public class ItemController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/items")
    public String viewItems(Model model) {
        String sql = "SELECT * FROM WishlistItem";
        List<Map<String, Object>> users = jdbcTemplate.queryForList(sql);
        model.addAttribute("items");//,items);
        return "items";
    }

    @PostMapping("/items")
    public String addItem(@RequestParam String item, @RequestParam String itemURL, @RequestParam BigDecimal price, @RequestParam String category, @RequestParam int wishlistID) {
        String sql = "INSERT INTO WishlistITem(item, itemURL, price, category, wishlistID) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, item, itemURL, price, category, wishlistID);
        return "redirect:/users";
    }
}
