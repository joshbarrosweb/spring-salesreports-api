package com.joshbarrosweb.springsalesapi.controller;

import com.joshbarrosweb.springsalesapi.entity.Product;
import com.joshbarrosweb.springsalesapi.formrequest.ProductFormRequest;
import com.joshbarrosweb.springsalesapi.repository.ProductRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public List<ProductFormRequest> index(){
        return repository
                    .findAll()
                    .stream()
                    .map(ProductFormRequest::fromModel)
                    .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductFormRequest> getById(@PathVariable  Long id) {
        Optional<Product> productExists = repository.findById(id);

        if (productExists.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var product = productExists.map( ProductFormRequest::fromModel ).get();
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ProductFormRequest save(@RequestBody ProductFormRequest product) {
        Product productEntity = product.toModel();
        repository.save(productEntity);
        return ProductFormRequest.fromModel(productEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ProductFormRequest product) {
        Optional<Product> productExists = repository.findById(id);

        if(productExists.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Product entity = product.toModel();
        entity.setId(id);
        repository.save(entity);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Product> productExists = repository.findById(id);

        if(productExists.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        repository.delete(productExists.get());
        return ResponseEntity.noContent().build();
    }
}
