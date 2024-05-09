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
//    public Order findByProductIdAndUserId(int productId, int userId) {
//        Query query =
//                em.createQuery("select o from Order o JOIN FETCH o.product p JOIN FETCH o.user u WHERE p.id =:product_id and u.id =:user_id");
//        query.setParameter("product_id", productId);
//        query.setParameter("user_id", userId);
//        //query.setParameter("order_id", orderId);
//
//        return (Order) query.getSingleResult();
//    }
//


    //주문완료 버튼을 누르면
    //인서트, 오더(1), 오더아이템(n), 수량업데이트 구매한 것만, 체크한 장바구니는 딜리트 시킨다아!!!
    //죽음!!
    //오더리스트
    public List<OrderResponse.OrderSaveDTO> findAllOrder() {
        String q = """
                      SELECT o FROM Order o JOIN FETCH o.user JOIN FETCH o.product JOIN FETCH o.cart WHERE o.id =:order_id
                    """;
        Query query = em.createQuery(q);

        return query.getResultList();
    }


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
//    public List<Order> findAll() {
//        Query query =
//                em.createQuery("SELECT o FROM Order o ORDER BY o.id DESC", Order.class);
//        return query.getResultList();
//    }

    public List<Order> findAll() {
        Query query =
                em.createQuery("SELECT o FROM Order o JOIN FETCH o.user JOIN FETCH o.product JOIN FETCH o.cart ORDER BY o.id DESC", Order.class);
        return query.getResultList();
    }


//    //주문서
//    public List<Order> findByCartAndUserId() {
//        Query query =
//                em.createQuery("SELECT o FROM Order o JOIN FETCH o.user JOIN FETCH o.product ORDER BY o.id DESC", Order.class);
////        query.setParameter("order_id", orderId);
//        return query.getResultList();
//    }


}

