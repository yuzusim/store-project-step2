package com.example.storeprojectstep1.order;

import com.example.storeprojectstep1.errors.exception.Exception404;
import com.example.storeprojectstep1.product.Product;
import com.example.storeprojectstep1.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class OrderRepository {
    private final EntityManager em;




//    public Order save(Order order) {
//        String q = """
//            INSERT INTO Order (orderQty)
//            VALUES (:orderQty)
//            """;
//
//        Query query = em.createQuery(q);
//        query.setParameter("orderQty", order.getOrderQty());
//
//        query.executeUpdate();
//
//        return order; // 저장된 엔티티 반환
//    }


//    public int updateQty(int orderId, int orderQty) {
//        String q = "UPDATE Order o SET o.orderQty = :orderQty WHERE o.id = :orderId";
//        Query query = em.createQuery(q);
//        query.setParameter("orderQty", orderQty);
//        query.setParameter("orderId", orderId);
//        return query.executeUpdate();
//    }


    //업데이트
    public Order updateById(int id, OrderRequest.UpdateDTO reqDTO) {
        Order order = findById(id);
        order.setOrderQty(reqDTO.getOrderQty());
        return order;
    }

    public Order findById(int id) {
        Order order = em.find(Order.class, id);
        return order;
    }

    //상품 등록하기
    public Order save(Order order) {
        em.persist(order);
        return order;
    }

    //상품아이디랑 유저아이디 조회 join fetch u.role
    public Order findByProductIdAndUserId(int productId, int userId) {
        Query query = em.createQuery("select o from Order o JOIN FETCH o.product p JOIN FETCH o.user u WHERE p.id =:product_id and u.id =:user_id");
        query.setParameter("product_id", productId);
        query.setParameter("user_id", userId);

        return (Order) query.getSingleResult();
    }


    //목록보기
    public List<Order> findAll() {
        Query query = em.createQuery("SELECT o FROM Order o JOIN FETCH o.user JOIN FETCH o.product ORDER BY o.id DESC", Order.class);
        return query.getResultList();
    }


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


}
