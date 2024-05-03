package com.dev.web.blogging.dao;

import com.dev.web.blogging.entity.User;

public interface UserDao {
    public User getUserByUserId(String userId);
    public void save(User user);
}
