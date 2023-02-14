package org.example.controller;

import org.example.entities.Product;
import org.example.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class InventoryController {
    ArrayList<Product> products = new ArrayList();
    private final ProductRepository productRepository;

    public InventoryController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/product")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @PostMapping("/product")
    public ResponseEntity<String> createProduct(@RequestBody Product productDetails){
        Optional<Product> product = productRepository.findByCode(productDetails.getCode());
        if(!product.isPresent()){
            productRepository.save(productDetails);
            System.out.println("Product received: " + productDetails);
            return new ResponseEntity<>("Product loaded", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("This product already exist", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/product/{code}")
    public ResponseEntity<Product> updateProduct(@PathVariable String code,@RequestBody Product productDetails) {
        Optional<Product> productOptional = productRepository.findByCode(code);
        if (!productOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Product product = productOptional.get();
        Class<?> productClass = product.getClass();
        Class<?> productDetailsClass = productDetails.getClass();

        for (Field field : productClass.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            try {
                Field productDetailsField = productDetailsClass.getDeclaredField(fieldName);
                productDetailsField.setAccessible(true);
                Object newValue = productDetailsField.get(productDetails);
                if (newValue != null && !newValue.equals(field.get(product))) {
                    field.set(product, newValue);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                System.out.println("Error: " + e.getClass());
            }
        }
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.ok(savedProduct);
    }
    @DeleteMapping("/product/{code}")
    public ResponseEntity<?> deleteProduct(@PathVariable String code) {
        Optional<Product> product = productRepository.findByCode(code);
        if (!product.isPresent()) {
            return new ResponseEntity<>("Product doesn't exist", HttpStatus.BAD_REQUEST);
        }
        productRepository.delete(product.get());
        return new ResponseEntity<>("The product has delete", HttpStatus.ACCEPTED);
    }
}
