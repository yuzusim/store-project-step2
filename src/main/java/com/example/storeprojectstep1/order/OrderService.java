package com.example.storeprojectstep1.order;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final EntityManager em;


    //상품 등록하기
    @Transactional
    public OrderResponse.SaveDTO save(OrderRequest.SaveDTO reqDTO){
        Order order = orderRepo.save(reqDTO.toEntity());
        return new OrderResponse.SaveDTO(order);
    }


    // 주문 목록을 가져오는 메서드
    public List<OrderResponse.ListDTO> findAll(){
        List<Order> orderList = orderRepo.findAll();
        return orderList.stream().map(OrderResponse.ListDTO::new).toList();
    }


}