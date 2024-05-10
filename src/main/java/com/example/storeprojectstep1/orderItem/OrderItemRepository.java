package com.example.storeprojectstep1.orderItem;

import com.example.storeprojectstep1.cart.CartResponse;
import com.example.storeprojectstep1.order.Order;
import com.example.storeprojectstep1.order.OrderRequest;
import com.example.storeprojectstep1.product.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class OrderItemRepository {
    private final EntityManager em;


    //주문서 확인
//    public void save(OrderRequest.SaveDTO reqDTO, Integer orderId) {
//        // Order 엔티티 찾기 (orderId를 사용)
//        Order order = em.find(Order.class, orderId);
//
//        for (int i = 0; i < reqDTO.getName().size(); i++) {
//            // Product 엔티티 찾기 (productId를 사용)
//            Product product = em.find(Product.class, reqDTO.getProduct().getId(i));
//
//            // OrderItem 엔티티 생성
//            OrderItem orderItem = new OrderItem();
//            orderItem.setOrderQty(reqDTO.getOrderQty().get(i));0
//            orderItem.setOrder(order); // 찾은 Order 엔티티 설정
//            orderItem.setProduct(product); // 찾은 Product 엔티티 설정
////  0
//
//            // EntityManager를 사용하여 OrderItem 엔티티 저장
//            em.persist(orderItem);
//        }
    }




