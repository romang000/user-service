package org.example.users.controller;

import org.example.users.controller.dto.PurchaseDto.PurchaseToGet;
import org.example.users.service.PurchaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("{id}")
    public PurchaseToGet getPurchase(@PathVariable Long id) {
        return purchaseService.findById(id);
    }
}
