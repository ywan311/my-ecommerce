package com.example.myecommerce.Domain.Comment;

import com.example.myecommerce.Domain.BaseTimeEntity;
import com.example.myecommerce.Domain.Product.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@ToString(exclude = "product")
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private String title;

    @Column
    private String content;

    @Builder
    public Comment(Product product,String title, String content) {
        this.product = product;
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
