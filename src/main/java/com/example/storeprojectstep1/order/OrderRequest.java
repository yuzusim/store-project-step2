package com.example.storeprojectstep1.order;

import com.example.storeprojectstep1.cart.Cart;
import com.example.storeprojectstep1.product.Product;
import com.example.storeprojectstep1.user.User;
import lombok.Data;

public class OrderRequest {

    @Data
    public static class PaymentDTO {
        private String payment;
    }

    @Data
    public static class UpdateDTO {
        // 주문수량
        private Integer orderQty;
    }

    @Data
    public static class SaveDTO {
        // 주문수량
        private Integer orderQty;

        // 카트 정보 추가
        private Cart cart;

        // DTO에서 엔티티로 변환하는 메서드
        public Order toEntity(Product product, Cart cart, User user) {
            return Order.builder()
                    .orderQty(orderQty)
                    .product(product)
                    .user(user)
                    .cart(cart)  // 카트 정보 설정
                    .build();
        }
    }
}
