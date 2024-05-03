package com.dev.web.blogging.dao;

import com.dev.web.blogging.entity.User;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl implements UserDao{

    private EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User getUserByUserId(String userId) {
        return entityManager.find(User.class, userId);
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }
}
