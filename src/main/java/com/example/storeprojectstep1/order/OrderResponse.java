package com.example.storeprojectstep1.order;

import com.example.storeprojectstep1.cart.Cart;
import com.example.storeprojectstep1.product.Product;
import com.example.storeprojectstep1.product.ProductResponse;
import com.example.storeprojectstep1.user.User;
import com.example.storeprojectstep1.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.net.ssl.SSLSession;

public class OrderResponse {

//    @Data
//    public static class StatusDTO {
//        private Integer id;
//        private Boolean state;
//
//        public void setStatus(String status) {
//            if ("주문완료".equals(status)) {
//                this.state = true;
//            } else if ("주문취소".equals(status)) {
//                this.state = false;
//            }
//        }
//    }


    //주문서
    @Data
    public static class OrderSaveDTO {
        private Integer id; //
        private Integer cartId;
        private User user; // 사용자 ID만 포함
        private Product product; // 상품 ID만 포함
        private String img; //상품이미지
        private String name; //상품명
        private Integer price; //상품가격
        private Integer orderQty; //구매수량
        private Integer totalQty; //총 결제 금액
        private Boolean status;

        public OrderSaveDTO(Order order) {
            this.id = order.getId();
            this.cartId = order.getCart().getId();
            this.user = order.getUser();
            this.product = order.getProduct();
            this.img = order.getProduct().getImg();
            this.name = order.getProduct().getName();
            this.price = order.getProduct().getPrice();
            this.orderQty = order.getOrderQty();
            this.totalQty = order.getTotalQty();
            this.status = order.getStatus();
        }
    }



    //상세보기
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
        private Boolean status;
        private String address;

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
            this.address = order.getUser().getAddress();
        }
    }

    //상세보기
    @Data
    public static class DetailDTO {
        private Integer orderQty;

        public DetailDTO(Order Order) {
            this.orderQty = Order.getOrderQty();
        }
    }

    //업데이트
    @Data
    public static class UpdateDTO {
        private Integer orderQty;

        public UpdateDTO(Order Order) {
            this.orderQty = Order.getOrderQty();
        }
    }


    //구매
    @Data
    public static class SaveDTO {
        private Integer id;
        private User user; // 사용자 ID만 포함
        private Product product; // 상품 ID만 포함
        private String img;
        private String name;
        private Integer price;
        private Integer orderQty;
        private String address;

        public SaveDTO(Order order) {
            this.id = order.getId();
            this.user = order.getUser();
            this.product = order.getProduct();
            this.img = order.getProduct().getImg();
            this.name = order.getProduct().getName();
            this.price = order.getProduct().getPrice();
            this.orderQty = order.getOrderQty();
            this.address = order.getUser().getAddress();

        }
    }

    //주문 목록보기
    @Data
    public static class ListDTO {
        private Integer id;
        private User user; // 사용자 ID만 포함
        private Product product; // 상품 ID만 포함
        private String payment;
        private Integer orderQty;
        private Boolean status;

        public ListDTO(Order order) {
            this.id = order.getId();
            this.user = order.getUser();
            this.product = order.getProduct();
            this.payment = order.getPayment();
            this.orderQty = order.getOrderQty();
            this.status = order.getStatus();
            //buttonColor();
        }

        //버튼 변경 클래스 용~
//        public void buttonColor() {
//            this.buttonColor = "btn btn-primary";
//            if ("주문취소".equals(status)) {
//                buttonColor = "btn btn-danger";
//            }
//
//        }
    }

}
