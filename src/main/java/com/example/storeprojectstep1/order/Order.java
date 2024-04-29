package com.example.storeprojectstep1.order;

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
    private User user;

    // 여러번 상품을 주문할 수 있다.
    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id")
    private Product product;

    private String payment; //계좌이체

    //    @Column(nullable = false)
    private Integer orderQty; //수량

    private Integer totalQty; //수량

    private String status; //주문, 취소

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Order(Integer id, User user, Product product, String payment, Integer orderQty, Integer totalQty, String status, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.payment = payment;
        this.orderQty = orderQty;
        this.totalQty = totalQty;
        this.status = status;
        this.createdAt = createdAt;
    }
}
