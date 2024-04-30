package com.example.storeprojectstep1.order;

import com.example.storeprojectstep1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;
    private final HttpSession session;

    //수정하기
    @GetMapping("/order/{id}/update-form")
    public String updateForm(@PathVariable int id, HttpServletRequest request){
        User sessionUser = (User) session.getAttribute("sessionUser");
        OrderResponse.OrderDTO order = orderService.getOrderDetail(sessionUser.getId(), id);
        request.setAttribute("order", order);
        System.out.println("test::::::::::::" + sessionUser);
        return "order/update-form";
    }

    @PostMapping("/order/{id}/update")
    public String update(@PathVariable int id, OrderRequest.UpdateDTO reqDTO){
        Order order = orderService.updateById(id, reqDTO);
        session.setAttribute("newOrder", order);
//        orderService.updateQty(id, orderQty); // 주문 수량을 업데이트
        return "redirect:/order/list";
    }


    //구매하기
    @PostMapping("/order/{id}/add")
    public String save(@PathVariable Integer id, OrderRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        orderService.save(reqDTO, id, sessionUser);

        return "redirect:/order/"+id+"/order-form";
    }




//    @GetMapping("/order/{id}/order-form")
//    public String orderForm(@PathVariable int id, HttpServletRequest request){
//
//
//        List<OrderResponse.OrderDTO> orderSaveList = orderService.findByOrderIdAndUserId(sessionUser.getId());
//        request.setAttribute("orderSaveList", orderSaveList);
//
//        // 세션에서 유저 정보를 가져와서 주문 폼에 필요한 데이터 설정
//        session.setAttribute("user", sessionUser);
//        return "order/order-form";
//
//    }

    @GetMapping("/order/{id}/order-form")
    public String orderForm(@PathVariable int id, HttpServletRequest request){
        User sessionUser = (User) session.getAttribute("sessionUser");

        // 사용자의 ID를 세션에서 가져와서 주문 목록 조회
        List<OrderResponse.OrderDTO> orderSaveList = orderService.findByOrderAndUserId(sessionUser.getId());
        request.setAttribute("orderSaveList", orderSaveList);

        // 세션에서 유저 정보를 가져와서 주문 폼에 필요한 데이터 설정
        session.setAttribute("user", sessionUser);
        return "order/order-form";
    }


    //목록보기
    @GetMapping({"/order/list"})
    public String list(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        List<OrderResponse.ListDTO> orderList = orderService.findAll();
        request.setAttribute("orderList", orderList);
        return "/order/list";
    }


}
