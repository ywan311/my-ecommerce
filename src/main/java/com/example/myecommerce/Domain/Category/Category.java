package com.example.myecommerce.Domain.Category;

import com.example.myecommerce.Domain.BaseTimeEntity;
import com.example.myecommerce.Domain.Product.Product;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Category extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    private Set<Product> products;

    @Builder
    public Category(String title, Set<Product> products) {
        this.title = title;
        this.products = products;
    }

    public void addProduct(Product product){
        this.products.add(product);
    }

    public void update(String title){
        this.title = title;
    }
}
