package com.example.myecommerce.Domain.Product;

import com.example.myecommerce.Domain.BaseTimeEntity;
import com.example.myecommerce.Domain.Comment.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class Product extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int price;

    @OneToMany(mappedBy = "product")
    private Set<Comment> comments = new HashSet<>();

    @Builder
    public Product(String title, String content, int price) {
        this.title = title;
        this.content = content;
        this.price = price;
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }
}
