package com.example.storeprojectstep1.order;

import com.example.storeprojectstep1.product.Product;
import com.example.storeprojectstep1.product.ProductResponse;
import com.example.storeprojectstep1.user.User;
import com.example.storeprojectstep1.user.UserResponse;
import lombok.Data;

import javax.net.ssl.SSLSession;

public class OrderResponse {


    @Data
    public static class OrderDTO {
        private Integer id;
        private User user; // 사용자 ID만 포함
        private Product product; // 상품 ID만 포함
        private String img;
        private String name;
        private Integer price;
        private Integer qty;
        private String payment;
        private Integer orderQty;
        private String status;

        public OrderDTO(Order order) {
            this.id = order.getId();
            this.user = order.getUser();
            this.product = order.getProduct();
            this.img = order.getProduct().getImg();
            this.name = order.getProduct().getName();
            this.price = order.getProduct().getPrice();
            this.qty = order.getProduct().getQty();
            this.payment = order.getPayment();
            this.orderQty = order.getOrderQty();
            this.status = order.getStatus();
        }
    }

    @Data
    public static class DetailDTO{
        private Integer orderQty;

        public DetailDTO(Order Order) {
            this.orderQty = Order.getOrderQty();
        }
    }

    //업데이트
    @Data
    public static class UpdateDTO{
        private Integer orderQty;

        public UpdateDTO(Order Order) {
            this.orderQty = Order.getOrderQty();
        }
    }


    @Data
    public static class SaveDTO {
        private Integer orderQty;

        public SaveDTO(Order order) {
            this.orderQty = order.getOrderQty();
        }
    }

    //목록보기
    @Data
    public static class ListDTO {
        private Integer id;
        private User user; // 사용자 ID만 포함
        private Product product; // 상품 ID만 포함
        private String payment;
        private Integer orderQty;
        private String status;

        public ListDTO(Order order) {
            this.id = order.getId();
            this.user = order.getUser();
            this.product =order.getProduct();
            this.payment = order.getPayment();
            this.orderQty = order.getOrderQty();
            this.status = order.getStatus();
        }
    }

}
