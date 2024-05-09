package com.example.storeprojectstep1.order;

import com.example.storeprojectstep1.cart.Cart;
import com.example.storeprojectstep1.cart.CartResponse;
import com.example.storeprojectstep1.cart.CartService;
import com.example.storeprojectstep1.product.Product;
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
    private final OrderService orderService;
    private final HttpSession session;

//    //수정하기
//    @GetMapping("/order/{id}/update-form")
//    public String updateForm(@PathVariable int id, HttpServletRequest request){
//        User sessionUser = (User) session.getAttribute("sessionUser");
//
//        OrderResponse.OrderDTO order = orderService.getOrderDetail(sessionUser.getId(), id);
//        request.setAttribute("order", order);
//        System.out.println("test::::::::::::" + sessionUser);
//        return "order/update-form";
//    }
//
//    @PostMapping("/order/{id}/update")
//    public String update(@PathVariable int id, OrderRequest.UpdateDTO reqDTO){
//        Order order = orderService.updateById(id, reqDTO);
//        session.setAttribute("newOrder", order);
////        orderService.updateQty(id, orderQty); // 주문 수량을 업데이트
//        return "redirect:/order/"+id;
//    }

    //삭제하기
    @PostMapping("/order/{id}/delete")
    public String delete(@PathVariable int id) {
//        productService.deleteById(id);
        return "redirect:/";
    }


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

    // 주문서 확인
    @GetMapping("/order-save-form")
    public String orderSaveForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        // 사용자의 ID와 카트 ID를 세션에서 가져와서 주문 목록 조회
        List<CartResponse.CartDTO> orderList =
                orderService.findByCartIdAndUserIdAndStatus(sessionUser.getId());
        System.out.println("orderList: " + orderList);

        request.setAttribute("orderList", orderList);
        session.setAttribute("user", sessionUser);
        return "/order/order-save-form";
    }



    //구매목록보기
    @GetMapping({"/order/list"})
    public String list(HttpServletRequest request) {
        //User sessionUser = (User) session.getAttribute("sessionUser");

        List<OrderResponse.OrderSaveDTO> orderList = orderService.findAllOrder();
        request.setAttribute("orderList", orderList);
        return "/order/list";
    }



    //구매(주문하기)
    @PostMapping("/order/save")
    public String save(OrderRequest.SaveDTO reqDTO, Product product, Cart cart) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        System.out.println("!!!구매할꺼야???"+reqDTO);
        orderService.save(reqDTO, product, cart, sessionUser);


        session.setAttribute("user", sessionUser);

        return "redirect:order/list";
    }


//    @PostMapping("/order/{id}/add")
//    public String save(OrderRequest.SaveDTO reqDTO, Product product, Cart cart, User sessionUser) {
//        //User sessionUser = (User) session.getAttribute("sessionUser");
//        System.out.println("!!!구매됐옹??" + reqDTO);
//        // 예시: id를 어떤 식으로든 사용
//        orderService.save(reqDTO, product, cart, sessionUser);
//
//        return "redirect:/order/list";
//    }


    //주문 폼
//    @GetMapping("/order/{id}/order-form")
//    public String orderForm(@PathVariable int id,
//                            HttpServletRequest request){
//        User sessionUser = (User) session.getAttribute("sessionUser");
//
//        // 사용자의 ID를 세션에서 가져와서 주문 목록 조회
//        List<OrderResponse.OrderDTO> orderSaveList =
//                orderService.findByOrderAndUserId(sessionUser.getId());
//        request.setAttribute("orderSaveList", orderSaveList);
//        session.setAttribute("user", sessionUser);
//        return "order/order-form";
//    }



}
