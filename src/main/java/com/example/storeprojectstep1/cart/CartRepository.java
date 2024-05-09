package com.example.storeprojectstep1.cart;

import com.example.storeprojectstep1.cart.Cart;
import com.example.storeprojectstep1.order.Order;
import com.example.storeprojectstep1.order.OrderRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CartRepository {
    private final EntityManager em;

    //업데이트
    public Cart updateById(int id, CartRequest.UpdateDTO reqDTO) {
        Cart cart = findById(id);
        cart.setOrderQty(reqDTO.getOrderQty());
        return cart;
    }

    //상세보기
    public Cart findById(int id) {
        Cart cart = em.find(Cart.class, id);
        return cart;
    }

    //장바구니 담기
    public Cart save(Cart cart) {
        em.persist(cart);
        return cart;
    }

    //장바구니 담기
    public List<Cart> findByUserId(int id) {
        Query query =
                em.createQuery("SELECT c FROM Cart c JOIN FETCH c.user u JOIN FETCH c.product p WHERE u.id = :id ORDER BY c.id DESC", Cart.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
