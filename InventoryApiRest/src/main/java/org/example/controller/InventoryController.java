package org.example.controller;

import org.example.entities.Products;
import org.example.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Optional;

@RestController
public class InventoryController {
    ArrayList<Products> products = new ArrayList();
    private final ProductRepository productRepository;

    public InventoryController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/product/{code}")
    public ResponseEntity<?> getProducts(@PathVariable String code) {
        Optional<Products> product = productRepository.findByCode(code);
        if (!product.isPresent()) {
            return new ResponseEntity<>("Products doesn't exist", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<String> createProducts(@RequestBody Products productDetails){
        Optional<Products> product = productRepository.findByCode(productDetails.getCode());
        if(!product.isPresent()){
            productRepository.save(productDetails);
            System.out.println("Products received: " + productDetails);
            return new ResponseEntity<>("Products loaded", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("This product already exist", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/product/{code}")
    public ResponseEntity<Products> updateProducts(@PathVariable String code, @RequestBody Products productDetails) {
        Optional<Products> productOptional = productRepository.findByCode(productDetails.getCode());
        if (!productOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Products product = productOptional.get();
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
        Products savedProducts = productRepository.save(product);
        return ResponseEntity.ok(savedProducts);
    }
    @DeleteMapping("/product/{code}")
    public ResponseEntity<?> deleteProducts(@PathVariable String code) {
        Optional<Products> product = productRepository.findByCode(code);
        if (!product.isPresent()) {
            return new ResponseEntity<>("Products doesn't exist", HttpStatus.BAD_REQUEST);
        }
        productRepository.delete(product.get());
        return new ResponseEntity<>("The product has delete", HttpStatus.ACCEPTED);
    }
}
