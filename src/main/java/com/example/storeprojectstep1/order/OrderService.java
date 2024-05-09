package com.example.storeprojectstep1.order;

import com.example.storeprojectstep1.cart.Cart;
import com.example.storeprojectstep1.cart.CartRepository;
import com.example.storeprojectstep1.cart.CartResponse;
import com.example.storeprojectstep1.orderItem.OrderItemRepository;
import com.example.storeprojectstep1.product.Product;
import com.example.storeprojectstep1.product.ProductRepository;
import com.example.storeprojectstep1.user.User;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final OrderItemRepository orderItemRepo;
    private final ProductRepository productRepo;
    private final CartRepository cartRepo;
    private final HttpSession session;
    private final EntityManager em;

    //업데이트
//    @Transactional
//    public Order updateById(int id, OrderRequest.UpdateDTO reqDTO) {
//        return orderRepo.updateById(id, reqDTO);
//    }

    //상세보기
//    public OrderResponse.OrderDTO getOrderDetail(Integer userId, Integer productId) {
//        //Product product = productRepo.findById(productId); //상품id조회
//        Order order = orderRepo.findByProductIdAndUserId(userId, productId); //해당 제품과 사용자에 해당하는 주문찾음
//        return new OrderResponse.OrderDTO(order);
//    }


    //서비스에서 상태 업데이트 트루로 하는 트랜잭션을 걸어주고 업데이트로

    //상세보기
    public OrderResponse.DetailDTO findById(int id) {
        Order order = orderRepo.findById(id);
        return new OrderResponse.DetailDTO(order);
    }

    //User user = orderService.findUser(sessionUser.getId());


    //주문서 (카트에 있는거 들고 와서 뿌림 .. 카트를 DI)
    @Transactional
    public List<CartResponse.CartDTO> findByCartIdAndUserIdAndStatus(Integer userId) {
        // 저장된 리스트를 사용자 ID와 카트 ID를 기준으로 조회
        List<CartResponse.CartDTO> cartList = orderRepo.findByCartIdAndUserIdAndStatus(userId);
        cartRepo.updateStatus();
        // 조회된 주문 리스트를 DTO로 변환
        return cartList;
    }


    //구매하기(주문하기)
    @Transactional
    public OrderResponse.SaveDTO save(OrderRequest.SaveDTO reqDTO, Product product, Cart cart, User user) {
        Order order = orderRepo.save(reqDTO.toEntity(product, cart, user));

        ////order 저장
        //Integer orderId = orderRepo.save(reqDTO);

        //orderitem에 저장
        //orderItemRepo.save(requestDTO, orderId);

        //수량업데이트(구매한 것만)
        //orderRepo.updateQty(requestDTO);

        //체크한 장바구니는 딜리트 시킨다아!!!
        //orderItemRepo.save(requestDTO, orderId);

        return new OrderResponse.SaveDTO(order);
        //return null;
    }






    //주문 폼 order-form
//    public List<OrderResponse.OrderDTO> findByCartAndUserId() {
//        List<Order> saveList = orderRepo.findByCartAndUserId();
//        return saveList.stream().map(OrderResponse.OrderDTO::new).toList();
//    }

    //구매목록 메서드 order/list
    public List<OrderResponse.ListDTO> findAll() {
        List<Order> orderList = orderRepo.findAll();
        return orderList.stream().map(OrderResponse.ListDTO::new).toList();
    }


    public List<OrderResponse.OrderSaveDTO> findAllOrder() {
//        User sessionUser = (User) session.getAttribute("sessionUser");
        List<Order> orderList = orderRepo.findAll();
        System.out.println("!!!!" + orderList);
        return orderList.stream().map(OrderResponse.OrderSaveDTO::new).toList();
    }



    //장바구니에 있는거 주문하는 폼
//    public OrderResponse.OrderSaveDTO getOrderSave(Integer cartId, Integer userId) {
//        //Product product = productRepo.findById(productId); //상품id조회
//        Order order = orderRepo.findByProductIdAndUserId(cartId, userId); //해당 제품과 사용자에 해당하는 주문찾음
//        return new OrderResponse.OrderSaveDTO(order);
//    }


}
