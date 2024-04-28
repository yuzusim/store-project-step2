package com.example.storeprojectstep1.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class OrderRepository {
    private final EntityManager em;


    //등록하기
    @Transactional
    public Order save(Order order){
        em.persist(order);
        return order;
    }

    //목록보기
    public List<Order> findAll() {
        Query query = em.createQuery("SELECT o FROM Order o JOIN FETCH o.user JOIN FETCH o.product ORDER BY o.id DESC", Order.class);
        return query.getResultList();
    }


}
