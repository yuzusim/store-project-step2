package com.example.storeprojectstep1.cart;

import com.example.storeprojectstep1.product.Product;
import com.example.storeprojectstep1.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "cart_tb")
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 주문을 여러번 할수 있다.
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // 여러번 상품을 주문할 수 있다.
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column
    private Integer orderQty; //수량

    @Column
    private Boolean status; //주문, 취소

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Cart(Integer id, User user, Product product, Integer orderQty, Integer sum, Boolean status, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.orderQty = orderQty;
//        this.sum = sum;
        this.status = status;
        this.createdAt = createdAt;
    }


}