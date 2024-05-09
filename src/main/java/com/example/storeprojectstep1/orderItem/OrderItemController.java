package com.example.storeprojectstep1.orderItem;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class OrderItemController {
    private final OrderItemService orderItemService;
    private final HttpSession session;

}
