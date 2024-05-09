package com.example.storeprojectstep1.orderItem;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderItemRepository {
    private final EntityManager em;

}
