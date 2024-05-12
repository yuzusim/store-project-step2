package com.example.storeprojectstep1.order;

import com.example.storeprojectstep1.cart.Cart;
import com.example.storeprojectstep1.cart.CartRepository;
import com.example.storeprojectstep1.cart.CartResponse;
import com.example.storeprojectstep1.orderItem.OrderItem;
import com.example.storeprojectstep1.orderItem.OrderItemRepository;
import com.example.storeprojectstep1.product.Product;
import com.example.storeprojectstep1.product.ProductRepository;
import com.example.storeprojectstep1.user.User;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final OrderItemRepository orderItemRepo;
    private final ProductRepository productRepo;
    private final CartRepository cartRepo;
    private final HttpSession session;
    private final EntityManager em;


    //상세보기
    public OrderResponse.DetailDTO findById(int id) {
        Order order = orderRepo.findById(id);
        return new OrderResponse.DetailDTO(order);
    }

    @Transactional
    public List<OrderResponse.OrderSaveDTO> save(OrderRequest.SaveDTO reqDTO, Product product, Cart cart, User user) {
        System.out.println("들어올꺼니?" + reqDTO);

        // 입력된 객체들이 null인지 검사
        if (product == null || cart == null || user == null) {
            throw new IllegalArgumentException("Product, Cart, User 객체는 null일 수 없습니다.");
        }

        // Order 객체 저장
        List<OrderResponse.OrderSaveDTO> orderList = (List<OrderResponse.OrderSaveDTO>) orderRepo.save(reqDTO.toEntity(product, cart, user));

        // OrderItem 생성 및 저장
        OrderItem orderItem = OrderItem.builder()
                .product(product)
                .orderQty(reqDTO.getOrderQty())
                .build();
        orderItemRepo.save(orderItem);

        // 체크한 장바구니는 삭제시킨다.
        if (reqDTO.getCart() != null && reqDTO.getCart().getId() != null) {
            cartRepo.deleteBySelectId(Collections.singletonList(reqDTO.getCart().getId()));
        } else {
            System.out.println("삭제할 장바구니 항목이 없습니다.");
        }

        return orderList;
    }



    //주문서 (카트에 있는거 들고 와서 뿌림 .. 카트를 DI)
    @Transactional
    public List<CartResponse.CartDTO> findByCartIdAndUserIdAndStatus(Integer userId) {
        // 저장된 리스트를 사용자 ID와 카트 ID를 기준으로 조회
        List<CartResponse.CartDTO> cartList = orderRepo.findByCartIdAndUserIdAndStatus(userId);
        cartRepo.updateStatus();
        // 조회된 주문 리스트를 DTO로 변환
        return cartList;
    }


    //주문 폼 order-form
//    public List<OrderResponse.OrderDTO> findByCartAndUserId() {
//        List<Order> saveList = orderRepo.findByCartAndUserId();
//        return saveList.stream().map(OrderResponse.OrderDTO::new).toList();
//    }

    //목록 메서드 order/list
    public List<OrderResponse.OrderSaveDTO> findAll() {
        List<Order> orderList = orderRepo.findAll();
        return orderList.stream().map(OrderResponse.OrderSaveDTO::new).toList();
    }


//    public List<OrderResponse.OrderSaveDTO> findAllOrder() {
////        User sessionUser = (User) session.getAttribute("sessionUser");
//        List<OrderResponse.OrderSaveDTO> orderList = orderRepo.findAllOrder();
//        System.out.println("!!!!" + orderList);
//        return orderList;
//    }
//


    // 저장된 리스트를 사용자 ID와 카트 ID를 기준으로 조회
//    List<CartResponse.CartDTO> cartList = orderRepo.findByCartIdAndUserIdAndStatus(userId);
//        cartRepo.updateStatus();
//    // 조회된 주문 리스트를 DTO로 변환
//        return cartList;
//}

    //장바구니에 있는거 주문하는 폼
//    public OrderResponse.OrderSaveDTO getOrderSave(Integer cartId, Integer userId) {
//        //Product product = productRepo.findById(productId); //상품id조회
//        Order order = orderRepo.findByProductIdAndUserId(cartId, userId); //해당 제품과 사용자에 해당하는 주문찾음
//        return new OrderResponse.OrderSaveDTO(order);
//    }


    //    //구매하기(주문하기)
//    @Transactional
//    public Order save(OrderRequest.SaveDTO reqDTO, Product product, Cart cart, User user) {
//        System.out.println("들어올꺼니?" + reqDTO);
//
//        //order 저장
//        Order order = orderRepo.save(reqDTO.toEntity(product, cart, user));
//
//        // OrderItem 생성 및 저장
//        OrderItem orderItem = OrderItem.builder()
//                .product(product)
//                .orderQty(reqDTO.getOrderQty())
//                .order(order)
//                .build();
//
//        orderItemRepo.save(orderItem);
//
//        //체크한 장바구니는 삭제시킨다.
//        cartRepo.deleteBySelectId(Collections.singletonList(reqDTO.getCart().getId()));
//
//        return order;
//    }

}
