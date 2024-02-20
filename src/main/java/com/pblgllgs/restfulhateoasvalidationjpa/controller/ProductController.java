package com.pblgllgs.restfulhateoasvalidationjpa.controller;

import com.pblgllgs.restfulhateoasvalidationjpa.dto.ProductRecordDto;
import com.pblgllgs.restfulhateoasvalidationjpa.dto.map.ProductMapper;
import com.pblgllgs.restfulhateoasvalidationjpa.model.ProductModel;
import com.pblgllgs.restfulhateoasvalidationjpa.model.Specs;
import com.pblgllgs.restfulhateoasvalidationjpa.repository.ProductRepository;
import com.pblgllgs.restfulhateoasvalidationjpa.repository.SpecsRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private static final String NOT_FOUND = "Product not found";

    private final ProductRepository productRepository;
    private final SpecsRepository specsRepository;

    public ProductController(ProductRepository productRepository, SpecsRepository specsRepository) {
        this.productRepository = productRepository;
        this.specsRepository = specsRepository;
    }

    @PostMapping
    public ResponseEntity<ProductModel> createProduct(@Valid @RequestBody ProductRecordDto productRecordDto) {
        ProductModel model = ProductMapper.productMapper.toEntity(productRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(model));
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> retrieveProducts() {
        List<ProductModel> products = productRepository.findAll();
        products.forEach(p ->
                p
                        .add(linkTo(methodOn(ProductController.class).retrieveProductById(p.getIdProduct())).withSelfRel())
                        .add(linkTo(methodOn(ProductController.class).retrieveProducts()).withRel("products"))
                        .add(linkTo(methodOn(ProductController.class).retrieveSpecs(p.getIdProduct(), p.getSpec().getIdSpec())).withRel("specs"))
        );
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> retrieveProductById(@PathVariable("id") UUID idProduct) {
        Optional<ProductModel> productDb = productRepository.findById(idProduct);
        if (productDb.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND);
        }
        productDb.ifPresent(p -> p
                .add(linkTo(methodOn(ProductController.class).retrieveProducts()).withSelfRel())
                .add(linkTo(methodOn(ProductController.class).retrieveSpecs(p.getIdProduct(), p.getSpec().getIdSpec())).withRel("specs"))
        );
        return ResponseEntity.status(HttpStatus.OK).body(productDb);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") UUID idProduct, @RequestBody ProductRecordDto productRecordDto) {
        Optional<ProductModel> productDb = productRepository.findById(idProduct);
        if (productDb.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND);
        }
        ProductModel product = ProductMapper.productMapper.toEntity(productRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") UUID idProduct) {
        Optional<ProductModel> productDb = productRepository.findById(idProduct);
        if (productDb.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND);
        }
        productRepository.deleteById(idProduct);
        return ResponseEntity.status(HttpStatus.OK).body("deleted");
    }

    @GetMapping("/{idProduct}/specs/{idSpecs}")
    public ResponseEntity<Object> retrieveSpecs(@PathVariable("idProduct") UUID idProduct, @PathVariable Long idSpecs) {
        Optional<ProductModel> productDb = productRepository.findById(idProduct);
        if (productDb.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND);
        }
        Optional<Specs> spec = specsRepository.findById(idSpecs);
        if (spec.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(spec);
    }


}
