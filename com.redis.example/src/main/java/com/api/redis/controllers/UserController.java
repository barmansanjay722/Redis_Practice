package com.api.redis.controllers;

import com.api.redis.dao.UserDao;
import com.api.redis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDao userDao;


    //save user
    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userDao.save(user);
    }

    //get single user
    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId) {
        return userDao.get(userId);
    }

    //find all
    @GetMapping
    public List<User> getAll() {

//        return userDao.findAll();

        Map<Object,Object> all = userDao.findAll();
        Collection<Object> values = all.values();
        List<User> collect = values.stream().map(value -> (User) value).toList();
        return collect;
    }

    //delete user
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId){
        userDao.delete(userId);
    }
}
