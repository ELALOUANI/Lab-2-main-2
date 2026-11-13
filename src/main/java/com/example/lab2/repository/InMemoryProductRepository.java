package com.example.lab2.repository;

import com.example.lab2.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private final ConcurrentSkipListMap<Long, Product> products = new ConcurrentSkipListMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(nextId.getAndIncrement());
        } else {
            nextId.accumulateAndGet(product.getId() + 1, Math::max);
        }
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        return List.copyOf(products.values());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public boolean deleteById(Long id) {
        return products.remove(id) != null;
    }
}
