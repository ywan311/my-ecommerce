package com.example.myecommerce.Domain.Product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int price;

    @Builder
    public Product(String title, String content, int price) {
        this.title = title;
        this.content = content;
        this.price = price;
    }
}
