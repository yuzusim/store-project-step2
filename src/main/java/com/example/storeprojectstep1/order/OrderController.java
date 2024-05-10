package com.example.storeprojectstep1.order;

import com.example.storeprojectstep1.cart.Cart;
import com.example.storeprojectstep1.cart.CartResponse;
import com.example.storeprojectstep1.cart.CartService;
import com.example.storeprojectstep1.product.Product;
import com.example.storeprojectstep1.product.ProductService;
import com.example.storeprojectstep1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final CartService cartService;
    private final ProductService productService;
    private final OrderService orderService;
    private final HttpSession session;



//    //상세보기
//    @GetMapping("/order/{id}")
//    public String detail(@PathVariable int id, HttpServletRequest request) {
//        OrderResponse.DetailDTO order = orderService.findById(id);
//        request.setAttribute("Order", order);
//        return "order/update-form";
//    }


//    @PostMapping("/order/order-save")
//    public String place(HttpServletRequest request){
//        List<OrderResponse.ListDTO> orderList = orderService.findAll();
//        request.setAttribute("orderList", orderList);
//        return "/order/order-save";
//    }



    //구매목록보기 -->
    @GetMapping({"/order/list"})
    public String list(HttpServletRequest request) {
        //User sessionUser = (User) session.getAttribute("sessionUser");

//        List<OrderResponse.ListDTO> orderItemList = orderService.findAll();
//        request.setAttribute("orderItemList", orderItemList);
//        System.out.println("!!!"+orderItemList);
        return "/order/list";
    }


    //구매(주문) 하기
    @PostMapping("/order/order-save")
    public String save(OrderRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        // ID를 사용하여 product, cart 객체를 데이터베이스에서 조회
//        Product product = productService.findById(reqDTO.getProductId());
//        Cart cart = cartService.findById(reqDTO.getCartId());

        System.out.println("!!!들어왕???"+reqDTO);

        orderService.save(reqDTO, sessionUser);
        return "redirect:order/list";
    }



    // 주문서 확인
    @GetMapping("/order-save-form")
    public String orderSaveForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        // 사용자의 ID와 카트 ID를 세션에서 가져와서 주문 목록 조회
        List<CartResponse.CartDTO> orderList =
                orderService.findByCartIdAndUserIdAndStatus(sessionUser.getId());
        System.out.println("주문서확인: " + orderList);

        request.setAttribute("orderList", orderList);
        session.setAttribute("user", sessionUser);
        return "/order/order-save-form";
    }


}
