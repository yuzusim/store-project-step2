package com.example.storeprojectstep1.order;

import com.example.storeprojectstep1.cart.Cart;
import com.example.storeprojectstep1.cart.CartResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class OrderRepository {
    private final EntityManager em;


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


//    public List<OrderResponse.OrderSaveDTO> findAllOrder() {
//        String q = """
//                SELECT o FROM Order o JOIN FETCH o.user u JOIN FETCH o.product p JOIN FETCH o.cart c ORDER BY o.id DESC";
//                """;
//        Query query = em.createQuery(q, Order.class);
//        return query.getResultList();
//    }




    //주문서 확인
    public List<CartResponse.CartDTO> findByCartIdAndUserIdAndStatus(int userId) {
        Query query =
                em.createQuery("select c from Cart c JOIN FETCH c.product p JOIN FETCH c.user u WHERE u.id =:user_id and c.status =:status");
//        query.setParameter("cart_id", cartId);
        query.setParameter("user_id", userId);
        query.setParameter("status", true);

        return query.getResultList();

        // 카트에 있는거 들고 와서 뿌림

    }

    //주문 목록보기 order/list
    public List<Order> findAll() {
        Query query =
                em.createQuery("SELECT o FROM Order o ORDER BY o.id DESC", Order.class);
        return query.getResultList();
    }


}

