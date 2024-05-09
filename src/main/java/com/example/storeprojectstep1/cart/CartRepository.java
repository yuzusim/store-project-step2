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


    //서비스에서 상태 업데이트 트루로 하는 트랜잭션을 걸어주고 업데이트로

    //업데이트
//    public Cart updateById(int id, CartRequest.UpdateDTO reqDTO) {
//        Cart cart = findById(id);
//        cart.setOrderQty(reqDTO.getOrderQty());
//        return cart;
//    }

    //상세보기
    public Cart findById(int id) {
        Cart cart = em.find(Cart.class, id);
        return cart;
    }


// 애
    public void updateStatusV2(CartRequest.UpdateDTO reqDTO) {
//        for (CartRequest.UpdateDTO dto : reqDTOs) {
            String jpql = """
                      update Cart c set c.orderQty = :orderQty, c.status = :status where c.id = :id
                    """;
            Query query = em.createQuery(jpql);
            query.setParameter("orderQty", 1000); // 새로운 수량 값 설정
            query.setParameter("status", true); // 새로운 상태 값 설정
            query.setParameter("id", reqDTO.getCartId());
            query.executeUpdate();
//        }
    }


    //롤백
    public void updateStatus() {
        String jpql = "update Cart c set c.status = :newStatus where c.status = :oldStatus";
        Query query = em.createQuery(jpql);
        query.setParameter("newStatus", false);
        query.setParameter("oldStatus", true);
        query.executeUpdate();
    }


    //장바구니 담기
//    public Cart save(Cart cart) {
//        cart.setStatus(false); // status 설정
//        em.persist(cart);
//        return cart;
//    }


    public Cart save(Cart cart) {
        cart.setStatus(false); // status 설정
        if (cart.getId() == null) {
            em.persist(cart);
        } else {
            cart = em.merge(cart);
        }
        return cart;
    }


    //장바구니 담기(목록)
    public List<Cart> findByUserId(int id) {
        Query query =
                em.createQuery("SELECT c FROM Cart c JOIN FETCH c.user u JOIN FETCH c.product p WHERE u.id = :id ORDER BY c.id DESC", Cart.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
