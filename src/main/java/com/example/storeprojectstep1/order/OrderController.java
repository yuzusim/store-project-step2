package com.example.storeprojectstep1.order;

import com.example.storeprojectstep1.product.Product;
import com.example.storeprojectstep1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;
    private final HttpSession session;


    //구매하기
    @PostMapping("/order/add")
    public String save(OrderRequest.SaveDTO reqDTO) {
        orderService.save(reqDTO);
        return "redirect:/order/orderList";
    }


    //@GetMapping("/order/orderForm")
    //public String orderForm(HttpServletRequest request) {
    //    User sessionUser = (User) session.getAttribute("sessionUser");
//
    //    List<OrderResponse.SaveDTO> orderList = orderService.findOrdersByUserId(sessionUser.getId());
    //    request.setAttribute("orderList", orderList);
//
    //    return "order/orderForm";
    //}




    @GetMapping("/order/orderForm")
    public String orderForm(HttpServletRequest request){
        User sessionUser = (User) session.getAttribute("sessionUser");

        List<OrderResponse.ListDTO> orderList = orderService.findAll();
        request.setAttribute("orderList", orderList);

        // 세션에서 유저 정보를 가져와서 주문 폼에 필요한 데이터 설정
        request.setAttribute("user", sessionUser);
        return "order/orderForm";

    }


    //목록보기
    @GetMapping({"/orderList"})
    public String list(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        List<OrderResponse.ListDTO> orderList = orderService.findAll();
        request.setAttribute("orderList", orderList);
        return "/order/orderList";
    }


}
