package com.dev.web.blogging.controller;

import com.dev.web.blogging.dao.UserDao;
import com.dev.web.blogging.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/users")
public class UserController {

    private UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/login")
    public String login() {
        return "users/login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String processUserRegistration(@ModelAttribute("user") User user, Model model) {
        user.setActive(1);
        try {
            userDao.save(user);
        } catch (DataIntegrityViolationException e) {
            System.out.println("User already exists: " + e.toString());
            model.addAttribute("error", "User already exists");
            return "users/register";
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.toString());
            model.addAttribute("error", "An unexpected error occurred");
            return "users/register";
        }

        return "users/register-success";
    }


}
