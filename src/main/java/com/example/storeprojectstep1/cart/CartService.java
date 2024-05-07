package com.example.storeprojectstep1.cart;

import com.example.storeprojectstep1.order.Order;
import com.example.storeprojectstep1.order.OrderRepository;
import com.example.storeprojectstep1.order.OrderRequest;
import com.example.storeprojectstep1.order.OrderResponse;
import com.example.storeprojectstep1.product.Product;
import com.example.storeprojectstep1.product.ProductRepository;
import com.example.storeprojectstep1.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepo;
    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;



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
