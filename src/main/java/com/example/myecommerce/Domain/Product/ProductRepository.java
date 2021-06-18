package com.example.myecommerce.Domain.Product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long > {
    public List<Product> findAllByUserUsername(String username);

    List<Product> findProductsByCategoryId(Long id);
}
