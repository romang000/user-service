package org.example.users.controller;

import org.example.users.controller.dto.ProductDto.ProductToAdd;
import org.example.users.controller.dto.ProductDto.ProductToGetWithoutUser;
import org.example.users.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    final private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductToGetWithoutUser addProduct(@RequestBody ProductToAdd productToAdd) {
        return productService.addProduct(productToAdd);
    }

    @GetMapping
    public List<ProductToGetWithoutUser> getProducts() {
        return productService.getProducts();
    }
}
