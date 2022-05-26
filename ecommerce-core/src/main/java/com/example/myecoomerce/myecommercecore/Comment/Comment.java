package com.example.myecoomerce.myecommercecore.Comment;

import com.example.myecoomerce.myecommercecore.BaseTimeEntity;
import com.example.myecoomerce.myecommercecore.Product.Product;
import com.example.myecoomerce.myecommercecore.User.User;
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
    @JoinColumn(name = "product_id", nullable = false,foreignKey = @ForeignKey(name = "FK_COMMENT_PRODUCT"))
    private Product product;

    @Column(nullable = false)
    private String title;

    @Column
    private String content;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id",nullable = false,foreignKey = @ForeignKey(name = "FK_COMMENT_USER"))
    private User user;

    @Builder
    public Comment(Product product, String title, String content, User user) {
        this.product = product;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
