package com.dev.web.blogging.controller.rest;


import com.dev.web.blogging.dao.UserDao;
import com.dev.web.blogging.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/users")
public class RestController {

    private UserDao userDao;

    public RestController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/test")
    public User getUser() {
        return userDao.getUserByUserId("aditya");
    }
}
