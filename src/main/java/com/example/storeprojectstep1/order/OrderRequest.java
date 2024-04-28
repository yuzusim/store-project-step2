package com.example.storeprojectstep1.order;


import com.example.storeprojectstep1.product.Product;
import com.example.storeprojectstep1.user.User;
import lombok.Builder;
import lombok.Data;

public class OrderRequest {

    @Data
    public static class SaveDTO {
        private Integer id;

        // 유저 정보
        private Integer userId; // 사용자 ID만 포함
        private String username;
        private String phone; // 폰번호
        private String email; // 이메일
        private String address; // 주소

        // 상품 정보
        private Integer productId; // 상품 ID만 포함
        private String name; // 상품명
        private Integer price; // 가격

        // 결제, 주문수량
        private String payment;
        private Integer orderQty;
        private String status;

        // DTO에서 엔티티로 변환하는 메서드
        @Builder
        public Order toEntity() {
            return Order.builder()
                    .id(id)
                    .user(User.builder()
                            .id(userId)
                            .username(username)
                            .phone(phone)
                            .email(email)
                            .address(address)
                            .build())
                    .product(Product.builder()
                            .id(productId)
                            .name(name)
                            .price(price)
                            .build())
                    .payment(payment)
                    .orderQty(orderQty)
                    .status(status)
                    .build();
        }
    }

}
