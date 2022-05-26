package com.example.myecommerce.api.dto.Order;

import com.example.myecoomerce.myecommercecore.Order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderListResDto {
    private Long id;
    private String productTitle;
    private String productContent;

    public OrderListResDto(Order entity) {
        this.id = entity.getId();
        this.productTitle = entity.getProduct().getTitle();
        this.productContent = entity.getProduct().getContent();
    }
}
