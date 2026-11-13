package com.example.lab2.service;

import com.example.lab2.dto.ProductRequest;
import com.example.lab2.dto.ProductResponse;
import com.example.lab2.exception.ResourceNotFoundException;
import com.example.lab2.model.Product;
import com.example.lab2.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse create(ProductRequest request) {
        Product product = new Product(null, request.name(), request.description(),
                request.price(), request.quantity());
        return toResponse(productRepository.save(product));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public ProductResponse findById(Long id) {
        return toResponse(findProduct(id));
    }

    public ProductResponse update(Long id, ProductRequest request) {
        Product product = findProduct(id);
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setQuantity(request.quantity());
        return toResponse(productRepository.save(product));
    }

    public void delete(Long id) {
        if (!productRepository.deleteById(id)) {
            throw notFound(id);
        }
    }

    private Product findProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> notFound(id));
    }

    private ResourceNotFoundException notFound(Long id) {
        return new ResourceNotFoundException("Product with id " + id + " was not found");
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(),
                product.getPrice(), product.getQuantity());
    }
}
