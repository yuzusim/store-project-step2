package com.example.storeprojectstep1.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class OrderRepository {
    private final EntityManager em;


    //업데이트
//    public Order updateById(int id, OrderRequest.UpdateDTO reqDTO) {
//        Order order = findById(id);
//        order.setOrderQty(reqDTO.getOrderQty());
//        return order;
//    }

    //상세보기
    public Order findById(int id) {
        Order order = em.find(Order.class, id);
        return order;
    }

    //상품 구매하기
    public Order save(Order order) {
        em.persist(order);
        return order;
    }

    //상세보기
    //상품아이디랑 유저아이디 조회 (join fetch u.role -> 하등 필요 없는 거였음)
    public Order findByProductIdAndUserId(int productId, int userId) {
        Query query = em.createQuery("select o from Order o JOIN FETCH o.product p JOIN FETCH o.user u WHERE p.id =:product_id and u.id =:user_id");
        query.setParameter("product_id", productId);
        query.setParameter("user_id", userId);
        //query.setParameter("order_id", orderId);

        return (Order) query.getSingleResult();
    }


    //주문 목록보기 order/list
    public List<Order> findAll() {
        Query query = em.createQuery("SELECT o FROM Order o JOIN FETCH o.user JOIN FETCH o.product ORDER BY o.id DESC", Order.class);
        return query.getResultList();
    }


    //주문 폼
    public List<Order> findByOrderAndUserId(int id) {
        Query query = em.createQuery("SELECT o FROM Order o JOIN FETCH o.user u JOIN FETCH o.product p where u.id =:id", Order.class);
        query.setParameter("id", id);
        return query.getResultList();
    }


//    public List<Order> findByOrderIdAndUserId(int orderId, int userId) {
//        Query query = em.createQuery("SELECT o FROM Order o JOIN FETCH o.user u WHERE o.id = :orderId AND u.id = :userId", Order.class);
//        query.setParameter("orderId", orderId);
//        query.setParameter("userId", userId);
//        return query.getResultList();
//    }

    // 1. 주문 폼 order-form 가지고 왔음
    // 2. 체크박스 체크시 상태값 true로 바뀌면서 체크 한값만 리스트에 담기게 하기


}
