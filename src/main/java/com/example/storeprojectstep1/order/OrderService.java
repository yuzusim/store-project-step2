package com.example.storeprojectstep1.order;

import com.example.storeprojectstep1.product.Product;
import com.example.storeprojectstep1.product.ProductRepository;
import com.example.storeprojectstep1.user.User;
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
    private final ProductRepository productRepository;
    private final EntityManager em;


//    //상품 등록하기
//
//    public Order save(OrderRequest.SaveDTO reqDTO) {
//        Order order = reqDTO.toEntity();
//        return orderRepo.save(order); // 주문 정보를 저장하고 저장된 주문 정보 반환
//    }

//    @Transactional
//    public OrderResponse.SaveDTO save(OrderRequest.SaveDTO reqDTO){
//        Order order = orderRepo.save(reqDTO.toEntity());
//        return new OrderResponse.SaveDTO(order);
//    }


//    @Transactional
//    public int updateQty(int orderId, int updateQty) {
//        return orderRepo.updateQty(orderId, updateQty);
//    }

    @Transactional
    public Order updateById(int id, OrderRequest.UpdateDTO reqDTO){
        return  orderRepo.updateById(id, reqDTO);

    }

    // 주문 목록을 가져오는 메서드
    public List<OrderResponse.ListDTO> findAll(){
        List<Order> orderList = orderRepo.findAll();
        return orderList.stream().map(OrderResponse.ListDTO::new).toList();
    }

    public OrderResponse.OrderDTO getOrderDetail(User user, Integer productId){
        Product product = productRepository.findById(productId);
        Order order = orderRepo.findByProductIdAndUserId(user, product);
        return new OrderResponse.OrderDTO(order);

    }


//    public OrderResponse.DetailDTO findById(int id) {
//        Order order = orderRepo.findById(id);
//        return new OrderResponse.DetailDTO(order);
//    }

}