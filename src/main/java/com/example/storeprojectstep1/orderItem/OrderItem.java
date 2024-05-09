package com.example.storeprojectstep1.orderItem;

import com.example.storeprojectstep1.order.Order;
import com.example.storeprojectstep1.product.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Table(name = "order_item_tb")
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column
    private Integer orderQty; //수량

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public OrderItem(Integer id, Product product, Integer orderQty, Order order, Timestamp createdAt) {
        this.id = id;
        this.product = product;
        this.orderQty = orderQty;
        this.order = order;
        this.createdAt = createdAt;
    }
}
