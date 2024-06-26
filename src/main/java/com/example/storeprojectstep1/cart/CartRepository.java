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

    //장바구니삭제
    public void deleteBySelectId(List<Integer> cartId) {
        String q= "DELETE FROM Cart c WHERE c.id IN :id";
        Query query = em.createQuery(q);
        query.setParameter("id", cartId);
        query.executeUpdate();
    }


    //장바구니 체크박스 업데이트
    public void updateStatusChecke(CartRequest.UpdateDTO reqDTO) {
//        for (CartRequest.UpdateDTO dto : reqDTOs) {}

        String q = """
                  update Cart c set c.orderQty = :orderQty, c.status = :status where c.id = :id
                """;
        Query query = em.createQuery(q);
        //query.setParameter("orderQty", 1000); // 새로운 수량 값 설정
        query.setParameter("orderQty", reqDTO.getOrderQty()); // c.orderQty = :orderQty,
        query.setParameter("status", true); // 새로운 상태 값 설정
        query.setParameter("id", reqDTO.getCartId());
        query.executeUpdate();

    }
    
    //롤백
    public void updateStatus() {
        String q = "update Cart c set c.status = :newStatus where c.status = :oldStatus";
        Query query = em.createQuery(q);
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
