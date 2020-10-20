package com.foxconn.sw.macaddress.service;

import com.foxconn.sw.macaddress.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAll(String username);

    User user(Integer pid);

    void save(User provider);

    void add(User provider);

    void delete(Integer pid);

    String selectPassword(String username);

    void updatePassword(String old, String news, String name);
}
