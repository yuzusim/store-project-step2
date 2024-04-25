package com.example.storeprojectstep1.product;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ProductController {
    private final ProductService productService;

    // 상품목록보기
    @GetMapping({"/"})
    public String list(HttpServletRequest request) {
        List<ProductResponse.MainDTO> productList = productService.findAll();
        request.setAttribute("productList", productList);
        return "product/list";
    }

    // 상품 상세보기
    @GetMapping("/product/{id}")
    public String detail(@PathVariable int id, HttpServletRequest request) {
        ProductResponse.DetailDTO product = productService.findById(id);
        request.setAttribute("product", product);
        return "product/detail";
    }

    // 상품 등록하기
    @GetMapping("/product/saveForm")
    public String saveForm() {
        return "product/saveForm";
    }

    @PostMapping("product/add")
    public String save(ProductRequest.SaveDTO reqDTO) {
        productService.save(reqDTO);
        return "redirect:/";
    }

    // 상품 수정하기
    @GetMapping("/product/1/updateForm")
    public String updateForm(@PathVariable int id) {

        return "product/updateForm";
    }

    @PostMapping("/product/1/update")
    public String update(@PathVariable int id) {
        return "redirect:/product/" + id;
    }

    // 상품 삭제하기
    @PostMapping("/product/1/delete")
    public String delete(@PathVariable int id) {
        return "redirect:/";
    }

}
