package org.example.users.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.users.controller.dto.ProductDto.ProductToGetWithoutUser;
import org.example.users.controller.dto.PurchaseDto.PurchaseToGet;
import org.example.users.entity.Purchase;
import org.example.users.repository.PurchaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private static final Logger log = LoggerFactory.getLogger(PurchaseService.class);

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public PurchaseToGet findById(Long id) {
        Purchase purchaseEntity = purchaseRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Purchase not found with id: " + id));

        return new PurchaseToGet(
                purchaseEntity.getId(),
                purchaseEntity.getUser().getId(),
                purchaseEntity.getProducts().stream()
                        .map(p -> new ProductToGetWithoutUser(
                                p.getId(),
                                p.getName(),
                                p.getDescription(),
                                p.getAmount(),
                                p.getPrice()
                        )).toList()
        );
    }


}
