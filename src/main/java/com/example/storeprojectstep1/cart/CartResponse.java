package com.example.storeprojectstep1.cart;

import com.example.storeprojectstep1.product.Product;
import com.example.storeprojectstep1.user.User;
import lombok.Data;

public class CartResponse {

    @Data
    public static class CartDTO {
        private Integer id;
        private User user; // 사용자 ID만 포함
        private Product product; // 상품 ID만 포함
        private String img;
        private String name;
        private Integer price;
        private Integer qty;
        private Integer orderQty;
        private String status;
        private String address;


        public CartDTO(Cart cart) {
            this.id = cart.getId();
            this.user = cart.getUser();
            this.product = cart.getProduct();
            this.img = cart.getProduct().getImg();
            this.name = cart.getProduct().getName();
            this.price = cart.getProduct().getPrice();
            this.qty = cart.getOrderQty();
            this.orderQty = cart.getOrderQty();
            this.status = String.valueOf(cart.getStatus());
            this.address = cart.getUser().getAddress();
        }
    }

}
