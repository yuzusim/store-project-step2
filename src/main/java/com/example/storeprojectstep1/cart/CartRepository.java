package com.example.storeprojectstep1.cart;

import com.example.storeprojectstep1.order.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CartRepository {
    private final EntityManager em;


    
    //장바구니 담기
    public Cart save(Cart cart) {
        em.persist(cart);
        return cart;
    }

    //장바구니 담기
    public List<Cart> findByUserId(int id) {
        Query query = em.createQuery("SELECT c FROM Cart c JOIN FETCH c.user u JOIN FETCH c.product p where u.id =:id", Cart.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

}
