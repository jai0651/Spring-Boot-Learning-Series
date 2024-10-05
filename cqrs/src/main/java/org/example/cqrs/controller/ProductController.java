package org.example.cqrs.controller;

import org.example.cqrs.command.CreateProductCommand;
import org.example.cqrs.model.Product;
import org.example.cqrs.service.ProductCommandService;
import org.example.cqrs.service.ProductQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductCommandService productCommandService;

    @Autowired
    private ProductQueryService productQueryService;

    @PostMapping
    public String createProduct(@RequestBody CreateProductCommand command) {
        return productCommandService.createProduct(command);
    }

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String name) {
        return productQueryService.searchProducts(name);
    }
}