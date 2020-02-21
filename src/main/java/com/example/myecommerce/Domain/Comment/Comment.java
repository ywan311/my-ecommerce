package com.example.myecommerce.Domain.Comment;

import com.example.myecommerce.Domain.BaseTimeEntity;
import com.example.myecommerce.Domain.Product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column
    private String content;

    @Builder
    public Comment(Product product, String content) {
        this.product = product;
        this.content = content;
    }
}
