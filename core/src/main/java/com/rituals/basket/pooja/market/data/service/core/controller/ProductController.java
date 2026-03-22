package com.rituals.basket.pooja.market.data.service.core.controller;

import com.rituals.basket.pooja.market.data.service.core.model.Product;
import com.rituals.basket.pooja.market.data.service.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*") // Allow requests from any origin for simplicity
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/instock")
    public List<Product> getInStockProducts() {
        return productService.getInStockProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productService.getProductById(id)
                .map(existingProduct -> {
                    existingProduct.setItemName(productDetails.getItemName());
                    existingProduct.setDescription(productDetails.getDescription());
                    existingProduct.setPrice(productDetails.getPrice());
                    existingProduct.setS3ImageKey(productDetails.getS3ImageKey());
                    existingProduct.setImageUrl(productDetails.getImageUrl());
                    existingProduct.setQuantityUnit(productDetails.getQuantityUnit());
                    existingProduct.setIsInStock(productDetails.getIsInStock());
                    existingProduct.setStockInQuantity(productDetails.getStockInQuantity());
                    existingProduct.setTotalQuantity(productDetails.getTotalQuantity());
                    return ResponseEntity.ok(productService.saveProduct(existingProduct));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productService.getProductById(id).isPresent()) {
            productService.deleteProduct(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
