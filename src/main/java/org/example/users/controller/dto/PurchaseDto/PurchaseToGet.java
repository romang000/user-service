package org.example.users.controller.dto.PurchaseDto;

import org.example.users.controller.dto.ProductDto.ProductToGetWithoutUser;

import java.util.List;

public record PurchaseToGet(
        Long id,
        Long userId,
        List<ProductToGetWithoutUser> products
) {
}
