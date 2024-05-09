package com.example.storeprojectstep1.orderItem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepo;

}
