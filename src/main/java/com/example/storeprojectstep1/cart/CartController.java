package com.example.storeprojectstep1.cart;

import com.example.storeprojectstep1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CartController {
    private final CartService cartService;
    private final HttpSession session;


//    // 장바구니 목록
//    @GetMapping({"/cart-list"})
//    public String list() {
//        return "";
//    }
//
//    // 상세보기
//    @GetMapping("/cart/{id}")
//    public String detail() {
//        return "";
//    }
//
//    // 장바구니 담기
//    @PostMapping("/cart/{id}/save")
//    public String saveCart() {
//        return "redirect:/cart-list";
//    }

//    @PostMapping("/cart/update")
//    public @ResponseBody String save(@RequestBody List<CartRequest.UpdateDTO> updateDtos) {
//        System.out.println(updateDtos);
//
//        return "ok";
//    }

    //장바구니 췍
    @PostMapping("/cart/update")
    public @ResponseBody String update(@RequestBody List<CartRequest.UpdateDTO> reqDTO) {
        System.out.println("장바구니 값? : " + reqDTO);

        return "200";
    }


    //장바구니 폼
    @GetMapping("/cart/{id}/cart-form")
    public String cartForm(@PathVariable int id, HttpServletRequest request){
        User sessionUser = (User) session.getAttribute("sessionUser");

        List<CartResponse.CartDTO> cartSaveList =
                cartService.findByUserId(sessionUser.getId());
        request.setAttribute("cartSaveList", cartSaveList);
        session.setAttribute("user", sessionUser);
        return "cart/cart-form";
    }

    //장바구니 담기
    @PostMapping("/cart/{id}/save")
    public String cartSave(@PathVariable Integer id, CartRequest.SaveDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        cartService.save(reqDTO, id, sessionUser);
        return "redirect:/cart/" + id + "/cart-form";
    }

}
