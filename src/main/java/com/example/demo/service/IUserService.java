package com.example.demo.service;

import com.example.demo.exception.DemoException;
import com.example.demo.model.User;

import java.util.List;

public interface IUserService {
    void create(User user) throws DemoException;

    List<User> users();
}
