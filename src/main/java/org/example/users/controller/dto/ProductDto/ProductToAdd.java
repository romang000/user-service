package org.example.users.controller.dto.ProductDto;

import java.math.BigDecimal;

public record ProductToAdd(
        String name,
        String description,
        Integer amount,
        BigDecimal price
) {
}
