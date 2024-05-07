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

    // 주문하려는 물품 확인 폼
    @GetMapping("/order-save")
    public String orderCheckForm(HttpServletRequest request) {

        User sessionUser = (User) session.getAttribute("sessionUser");
//        User user = orderService.유저조회(sessionUser.getId());


//        if () {
        //지금 화면에 뿌려질게 화면에 뿌려질 유저 정보와 장바구니에 담은 상품 정보를 받아와서 뿌려야함!!!@

        // 사용자의 ID를 세션에서 가져와서 주문 목록 조회
        List<OrderResponse.OrderDTO> orderSaveList =
                orderService.findByOrderAndUserId(sessionUser.getId());
        request.setAttribute("orderSaveList", orderSaveList);
        session.setAttribute("user", sessionUser);

//        List<OrderResponse.SaveFormDTO> cartList = orderService.내장바구니내역(sessionUser.getId());
//            System.out.println("내장바구니 컨트롤러 확인용 : " + cartList);

        //totalSum 계산용...
//        Integer totalSum = cartList.stream().mapToInt(value -> value.getSum()).sum();

        // 모델에(request) 담기 .... 한 번에 담고싶다  !!
//        request.setAttribute("cartList", cartList);
//        request.setAttribute("totalSum", totalSum);
//        request.setAttribute("user", user);

//        return "/order/order-save-form";

//        } else {
//            //디테일에서 구매하기 누르면 여기서 담겨야하지않을가
//            OrderResponse.SaveFormDTO order = orderService.디테일주문폼(sessionUser.getId());
//            System.out.println("주문폼 dto 값 확인 : " + order);
//            request.setAttribute("order", order);
//
//            return "/order/order-save-form";
//        }



        return "/order/order-save";
    }

    //구매하기
//    @PostMapping("/order/{id}/add")
//    public String save(@PathVariable Integer id, OrderRequest.SaveDTO reqDTO) {
//        User sessionUser = (User) session.getAttribute("sessionUser");
//        orderService.save(reqDTO, id, sessionUser);
////
////        return "redirect:/order/"+id+"/order-form";
//        return "redirect:cart/cart-form";
//    }

    //주문 폼
    @GetMapping("/order/{id}/order-form")
    public String orderForm(@PathVariable int id,
                            HttpServletRequest request){
        User sessionUser = (User) session.getAttribute("sessionUser");

        // 사용자의 ID를 세션에서 가져와서 주문 목록 조회
        List<OrderResponse.OrderDTO> orderSaveList =
                orderService.findByOrderAndUserId(sessionUser.getId());
        request.setAttribute("orderSaveList", orderSaveList);
        session.setAttribute("user", sessionUser);
        return "order/order-form";
    }

    //구매목록
    @GetMapping({"/order/list"})
    public String list(HttpServletRequest request) {
        //User sessionUser = (User) session.getAttribute("sessionUser");

        List<OrderResponse.ListDTO> orderList = orderService.findAll();
        request.setAttribute("orderList", orderList);
        return "/order/list";
    }

}
