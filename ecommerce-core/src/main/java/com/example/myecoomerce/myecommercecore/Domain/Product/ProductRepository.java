package com.example.myecoomerce.myecommercecore.Domain.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long > {
    public List<Product> findAllByUserUsername(String username);

    List<Product> findProductsByCategoryId(Long id);
}
