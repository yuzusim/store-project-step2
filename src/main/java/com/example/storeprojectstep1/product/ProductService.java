package com.example.storeprojectstep1.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    //상품 목록보기
    public List<ProductResponse.MainDTO> findAll(){
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(ProductResponse.MainDTO::new).toList();
    }

}
