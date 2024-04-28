package com.example.storeprojectstep1.order;

import com.example.storeprojectstep1.product.Product;
import com.example.storeprojectstep1.product.ProductResponse;
import com.example.storeprojectstep1.user.User;
import com.example.storeprojectstep1.user.UserResponse;
import lombok.Data;

import javax.net.ssl.SSLSession;

public class OrderResponse {

    @Data
    public static class SaveDTO {
        private Integer id;
        private Integer userId; // 사용자 ID만 포함
        private Integer productId; // 상품 ID만 포함
        private String payment;
        private Integer orderQty;
        private String status;

        public SaveDTO(Order order) {
            this.id = order.getId();
            this.userId = order.getUser().getId();
            this.productId = order.getProduct().getId();
            this.payment = order.getPayment();
            this.orderQty = order.getOrderQty();
            this.status = order.getStatus();
        }

    }

    //목록보기
    @Data
    public static class ListDTO {
        private Integer id;
        private User userId; // 사용자 ID만 포함
        private Product productId; // 상품 ID만 포함
        private String payment;
        private Integer orderQty;
        private String status;

        public ListDTO(Order order) {
            this.id = order.getId();
            this.userId = order.getUser();
            this.productId =order.getProduct();
            this.payment = order.getPayment();
            this.orderQty = order.getOrderQty();
            this.status = order.getStatus();
        }
    }

}
