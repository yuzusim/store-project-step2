package com.example.storeprojectstep1.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    //상품 상세 보기
//    public ProductResponse.DetailDTO getProductDetail(int id){
//        Product product = productRepository.findById(id);
//        return new ProductResponse.DetailDTO(product);
//    }

    public ProductResponse.DetailDTO findById(int id){
        Product product = productRepository.findById(id);
        return new ProductResponse.DetailDTO(product);

    }


    //상품 등록
    public ProductResponse.SaveDTO save(ProductRequest.SaveDTO reqDTO){
        Product product = productRepository.save(reqDTO.toEntity());
        return new ProductResponse.SaveDTO(product);
    }

    //상품 목록보기
    public List<ProductResponse.MainDTO> findAll(){
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(ProductResponse.MainDTO::new).toList();
    }
}
