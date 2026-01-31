package com.example.ecommerce.controller;

import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.FileStorageService;
import com.example.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileStorageService fileStorageService;
    private final ProductService productService;

    /**
     * Upload product image
     */
    @PostMapping("/products/{productId}")
    public Product uploadProductImage(
            @PathVariable Long productId,
            @RequestParam("file") MultipartFile file
    ) {

        Product product = productService.getById(productId);
        if (product == null) {
            throw new ResourceNotFoundException("Product not found");
        }

        String imageUrl = fileStorageService.store(file);
        product.setImageUrl(imageUrl);

        return productService.save(product);
    }
}
