package com.example.ESHOP.controller;

import com.example.ESHOP.model.Product;
import com.example.ESHOP.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("eshop")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping(value = "/products")
    public List<Product> getAllProducts() {
        logger.info("Get all products.");
        return productRepository.getAllProducts();
    }

    @GetMapping(value = "/product/{id}")
    public Product getProductById(@PathVariable int id) {
        Product product = productRepository.getProductById(id);
        logger.info(String.format("Get product by %s", id, "."));
        return null;
    }

    @PostMapping(value = "/product/add")
    public void addProduct(@RequestBody Product addProduct) {
        logger.info("Add new Product " + addProduct.toString() + ".");
        productRepository.addProduct(addProduct);
    }

    @PutMapping(value = "/{id}/price")
    public ResponseEntity<Product> updateProductPrice(@PathVariable int id, @RequestParam BigDecimal price) {
        Product product = productRepository.getProductById(id);
        logger.info(String.format("Update %s", price, "by ", id));
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        product.setPrice(price);
        productRepository.updatePriceById(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/out-of-sale")
    public ResponseEntity<Void> deleteOutOfSaleProducts() {
        productRepository.deleteOutOfSaleProducts();
        return ResponseEntity.noContent().build();
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String CatchException(Exception e) {
        return e.getLocalizedMessage();
    }
}
