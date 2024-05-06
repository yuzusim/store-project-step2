package com.example.storeprojectstep1.cart;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {

    @PostMapping("/cart/cart-form")
    public String cartList(){
        return "cart/cart-form";
    }

}
