package com.example.demo.service;

import com.example.demo.exception.DemoException;
import com.example.demo.model.User;
import com.example.demo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public class UserService implements IUserService{
    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public void create(User user) throws DemoException{
        try {
            iUserRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new DemoException();
        }
    }

    @Override
    public List<User> users() {
        return iUserRepository.findAll();
    }
}
