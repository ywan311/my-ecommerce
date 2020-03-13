package com.example.myecommerce.Domain.Product;

import com.example.myecommerce.Domain.BaseTimeEntity;
import com.example.myecommerce.Domain.Category.Category;
import com.example.myecommerce.Domain.Comment.Comment;
import com.example.myecommerce.Domain.Order.Order;
import com.example.myecommerce.Domain.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@ToString(exclude = "category")
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
    @JsonManagedReference
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "FK_PRODUCT_CATEGORY"))
    private Category category;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_PRODUCT_USER"))
    private User user;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private Set<Order> orders = new HashSet<>();


    @Builder
    public Product(String title, String content, int price, Category category, User user) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.category = category;
        this.user = user;
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public void update(String title, String content, int price){
        this.title = title;
        this.content = content;
        this.price = price;
    }
    public void changeCategory(Category category){
        this.category = category;
    }
}
