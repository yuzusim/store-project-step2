package com.example.storeprojectstep1.order;

import com.example.storeprojectstep1.cart.Cart;
import com.example.storeprojectstep1.product.Product;
import com.example.storeprojectstep1.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@NoArgsConstructor
@Data
@Table(name = "order_tb")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 주문을 여러번 할수 있다.
    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
    private User user;

    // 여러번 상품을 주문할 수 있다.
   @ManyToOne(cascade = CascadeType.PERSIST) // 또는 CascadeType.ALL
   private Product product;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Cart cart;

    @Column
    private String payment; //계좌이체

    @Column(nullable = false)
    private Integer orderQty; //수량

    @Column
    private Integer totalQty; //합계수량

    @Column
    private Boolean status; //주문, 취소

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Order(Integer id, User user, Product product, Cart cart, String payment, Integer orderQty, Integer totalQty, Boolean status, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.cart = cart;
        this.payment = payment;
        this.orderQty = orderQty;
        this.totalQty = totalQty;
        this.status = status;
        this.createdAt = createdAt;
    }

}
