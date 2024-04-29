package com.example.storeprojectstep1.order;

import com.example.storeprojectstep1.product.ProductRequest;
import com.example.storeprojectstep1.product.ProductResponse;
import com.example.storeprojectstep1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        OrderResponse.OrderDTO order = orderService.getOrderDetail(sessionUser, id);
        request.setAttribute("order", order);
        return "order/update-form";
    }

    @PostMapping("/order/{id}/update")
    public String update(@PathVariable int id, OrderRequest.UpdateDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        Order order = orderService.updateById(sessionUser.getId(), reqDTO);
        session.setAttribute("newOrder", order);
//        orderService.updateQty(id, orderQty); // 주문 수량을 업데이트
        return "redirect:/order/order-list";
    }


    //구매하기
    @PostMapping("/order/add")
    public String save(OrderRequest.SaveDTO reqDTO) {
//        orderService.save(reqDTO);
        return "redirect:/order/order-list";
    }


    @GetMapping("/order/order-form")
    public String orderForm(HttpServletRequest request){
        User sessionUser = (User) session.getAttribute("sessionUser");

//        List<OrderResponse.ListDTO> orderList = orderService.findAll();
//        request.setAttribute("orderList", orderList);

        // 세션에서 유저 정보를 가져와서 주문 폼에 필요한 데이터 설정
        request.setAttribute("user", sessionUser);
        return "/order/order-form";

    }


    //목록보기
    @GetMapping({"/order/order-list"})
    public String list(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        List<OrderResponse.ListDTO> orderList = orderService.findAll();
        request.setAttribute("orderList", orderList);
        return "/order/order-list";
    }


}
