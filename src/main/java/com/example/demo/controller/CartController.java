package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
//@SessionAttributes("carts")
@RequestMapping("/cart")
public class CartController {
    private List<Product> productList;
    private List<Cart> cartList;

    public CartController() {
        this.productList = new ArrayList<>();
        productList.add(new Product(1L,"milo"));
        productList.add(new Product(2L,"coca"));
        productList.add(new Product(3L,"pepsi"));

        this.cartList = new ArrayList<>();
        cartList.add(new Cart(productList.get(0), 2));
        cartList.add(new Cart(productList.get(1), 3));
        cartList.add(new Cart(productList.get(2), 4));
    }

    @ModelAttribute("carts")
    public List<Cart> getCartList() {
        return cartList;
    }

    @GetMapping
    public ModelAndView getProduct() {
//        cartList.get(0).setQuantity(cartList.get(0).getQuantity() + 1);
        return new ModelAndView("index");
    }

    @GetMapping("/up/{id}")
    public String quantityCartUp(@PathVariable("id") Long id) {
        for (Cart cart : cartList) {
            if (cart.getProduct().getId().equals(id)) {
                cart.setQuantity(cart.getQuantity() + 1);
            }
        }
        return "/index";
    }

    @GetMapping("/down/{id}")
    public String quantityCartDown(@PathVariable("id") Long id) {
        for (Cart cart : cartList) {
            if (cart.getProduct().getId().equals(id)) {
                cart.setQuantity(cart.getQuantity() - 1);
            }
        }
        return "/index";
    }
}
