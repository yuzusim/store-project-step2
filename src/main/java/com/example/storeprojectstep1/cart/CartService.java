 package com.example.storeprojectstep1.cart;

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



//    //업데이트
//    @Transactional
//    public Cart updateById(List<CartRequest.UpdateDTO> reqDTO) {
//        return cartRepo.updateById(id, (CartRequest.UpdateDTO) reqDTO);
//    }

    @Transactional
    public List<Cart> updateById(List<CartRequest.UpdateDTO> reqDTOs) {
        List<Cart> updatedCarts = new ArrayList<>();
        for (CartRequest.UpdateDTO reqDTO : reqDTOs) {
            // 각 UpdateDTO를 사용하여 업데이트된 카트를 가져와서 리스트에 추가
            System.out.println("!!!"+reqDTOs);
            Cart updatedCart = cartRepo.updateById(reqDTO.getCartId(), reqDTO);
            System.out.println("!!!"+reqDTOs);
            updatedCarts.add(updatedCart);
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

    //장바구니 담기
    public List<CartResponse.CartDTO> findByUserId(int id) {
        List<Cart> saveList = cartRepo.findByUserId(id);
        return saveList.stream().map(CartResponse.CartDTO::new).toList();
    }

}