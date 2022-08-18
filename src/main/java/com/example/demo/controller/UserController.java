package com.example.demo.controller;

import com.example.demo.exception.DemoException;
import com.example.demo.model.User;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @GetMapping("/demo")
    public ModelAndView demo() throws IOException {
        throw new IOException();
    }

    @PostMapping("/register")
    public ModelAndView formRegister(@Validated @ModelAttribute("user") User user,
                                  //lớp chữa lỗi validate được gửi kèm trong request
                                  BindingResult bindingResult) throws DemoException{
        //xử lý khi có lỗi validate
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("register");
        }
        //sử dụng try-catch để xử lý lỗi

//        try {
            userService.create(user);
            ModelAndView modelAndView = new ModelAndView("register");
            modelAndView.addObject("message", "Register success!");
            return modelAndView;
//        } catch (DemoException e) {
//            return new ModelAndView("/error");
//        }
    }

    //annotation để xử lý cùng lỗi nhiều phương thức ném lỗi thay vì try-catch trên từng phương thức
    @ExceptionHandler(Exception.class)
    public ModelAndView showException() {
        return new ModelAndView("/error");
    }
}
