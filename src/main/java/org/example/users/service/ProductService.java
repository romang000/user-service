package org.example.users.service;

import org.example.users.controller.dto.ProductDto.ProductToAdd;
import org.example.users.controller.dto.ProductDto.ProductToGetWithoutUser;
import org.example.users.controller.dto.UserDto.UserDto;
import org.example.users.entity.Product;
import org.example.users.entity.User;
import org.example.users.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    final private ProductRepository productRepository;

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductToGetWithoutUser addProduct(ProductToAdd productToAdd) {

        Product product = productRepository.findByName(productToAdd.name())
                .map(existing -> {
                    existing.setAmount(existing.getAmount() + productToAdd.amount());
                    existing.setDescription(productToAdd.description());
                    existing.setPrice(productToAdd.price());
                    return existing;
                })
                .orElseGet(() -> new Product(
                        null,
                        productToAdd.name(),
                        productToAdd.description(),
                        productToAdd.amount(),
                        productToAdd.price(),
                        null,
                        null
                ));

        productRepository.save(product);

        log.info("Added product {}", product);

        return new ProductToGetWithoutUser(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAmount(),
                product.getPrice()
        );
    }

    public List<ProductToGetWithoutUser> getProducts() {
        List<Product> products = productRepository.findAll();

        log.info("Found {} products", products.size());

        return products.stream()
                .map(p -> new ProductToGetWithoutUser(
                     p.getId(),
                     p.getName(),
                     p.getDescription(),
                     p.getAmount(),
                     p.getPrice()
                )).toList();
    }
}
