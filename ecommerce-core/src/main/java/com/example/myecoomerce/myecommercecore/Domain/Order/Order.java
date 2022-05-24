package com.example.myecoomerce.myecommercecore.Domain.Order;

import com.example.myecoomerce.myecommercecore.Domain.BaseTimeEntity;
import com.example.myecoomerce.myecommercecore.Domain.Product.Product;
import com.example.myecoomerce.myecommercecore.Domain.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "tb_order")
@Getter
@NoArgsConstructor
public class Order extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "product_id",nullable = false, foreignKey = @ForeignKey(name = "FK_ORDER_PRODUCT"))
    private Product product;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id",nullable = false, foreignKey = @ForeignKey(name = "FK_ORDER_USER"))
    private User user;

    @Builder
    public Order(Product product, User user) {
        this.product = product;
        this.user = user;
    }
}
