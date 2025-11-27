package org.example.users.controller.dto.ProductDto;

import java.math.BigDecimal;

public record ProductToGetWithoutUser (
        Long id,
        String name,
        String description,
        Integer amount,
        BigDecimal price
){
}
