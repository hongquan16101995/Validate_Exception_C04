package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {

    @ModelAttribute("user")
    public User setUpUserForm() {
        return new User();
    }

    @GetMapping
    //annotation @Cookie cho phép mọi người thêm hoặc truy cập tới cookie của máy client đang tương tác
    //value: giá trị của cookie đó
    //defaultValue: giá trị mặc định của cookie khi không tồn tại cookie này
    public String index(@CookieValue(value = "username", defaultValue = "") String username,
                        Model model) {
        //đối tượng Cookie
        Cookie cookie = new Cookie("username", username);
        model.addAttribute("cookieValue", cookie);
        return "/login";
    }

    @PostMapping
    public String doLogin(@ModelAttribute("user") User user, Model model,
                          @CookieValue(value = "username", defaultValue = "") String username,
                          HttpServletResponse response, HttpServletRequest request) {

        //kiểm tra điều kiên đăng nhập
        if (user.getUsername().equals("admin") && user.getPassword().equals("123456")) {
            if (user.getUsername() != null)
                username = user.getUsername();

            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(10000);
            response.addCookie(cookie);

//            Cookie[] cookies = request.getCookies();
//            for (Cookie ck : cookies) {
//                if (!ck.getName().equals("username")) {
//                    ck.setValue("");
//                }
//                model.addAttribute("cookieValue", ck);
//                break;
//            }
            model.addAttribute("message", "Login success. Welcome");
        } else {
            user.setUsername("");
            user.setPassword("");
            Cookie cookie = new Cookie("username", username);
            model.addAttribute("cookieValue", cookie);
            model.addAttribute("message", "Login failed. Try again.");
        }
        return "/login";
    }
}
