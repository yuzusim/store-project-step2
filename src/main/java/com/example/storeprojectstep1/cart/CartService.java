 package com.example.storeprojectstep1.cart;

import com.example.storeprojectstep1.errors.exception.Exception404;
import com.example.storeprojectstep1.order.OrderRepository;
import com.example.storeprojectstep1.product.Product;
import com.example.storeprojectstep1.product.ProductRepository;
import com.example.storeprojectstep1.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepo;
    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;


    //장바구니리스트 체크박스 업데이트
    //서비스에서 상태 업데이트 true 하는 트랜잭션을 걸어주고 업데이트로
    @Transactional
    public List<Cart> updateByCarts(List<CartRequest.UpdateDTO> reqDTOs) {
        List<Cart> updatedCarts = new ArrayList<>();
        for (CartRequest.UpdateDTO reqDTO : reqDTOs) {

            Cart cart = cartRepo.findById(reqDTO.getCartId());
            if(cart == null){
                // cart 없다고 예외처리 해주기
                throw new Exception404("존재하지 않는 카트입니다.");
            }

            // 각 UpdateDTO를 사용하여 업데이트된 카트를 가져와서 리스트에 추가
            System.out.println("!!!"+reqDTOs);

            cartRepo.updateStatusChecke(reqDTO);

//            Cart updatedCart = cartRepo.updateById(reqDTO.getCartId(), reqDTO);

            System.out.println("!!!"+reqDTOs);
//            updatedCarts.add(updatedCart);
        }
        return updatedCarts;
    }

    //장바구니 담기
    @Transactional
    public CartResponse.CartDTO save(CartRequest.SaveDTO reqDTO, int id, User user) {
        Product product = productRepo.findById(id);
        Cart cart = cartRepo.save(reqDTO.toEntity(product, user));
        System.out.println("!!!"+user);
        return new CartResponse.CartDTO(cart);
    }

    //장바구니 담기(리스트)
    public List<CartResponse.CartDTO> findByUserId(int id) {
        List<Cart> saveList = cartRepo.findByUserId(id);
        return saveList.stream().map(CartResponse.CartDTO::new).toList();
    }

}