package com.example.myecommerce.Service.Order;

import com.example.myecommerce.Domain.Order.Order;
import com.example.myecommerce.Domain.Order.OrderRepository;
import com.example.myecommerce.Domain.Product.Product;
import com.example.myecommerce.Domain.Product.ProductRepository;
import com.example.myecommerce.Domain.User.User;
import com.example.myecommerce.Domain.User.UserRepository;
import com.example.myecommerce.Web.Dto.Order.OrderListResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Long save(Long prodId, String username) {
        User user = userRepository.findByUsername(username);
        Product product = productRepository.findById(prodId).orElseThrow(() -> new IllegalArgumentException("상품이없습니다. id:" + prodId));
        return orderRepository.save(Order.builder()
                .product(product)
                .user(user)
                .build()).getId();
    }

    @Transactional
    public void delete(Long id){
        Order order = orderRepository.findById(id).orElseThrow(()->new IllegalArgumentException("주문 정보가 없습니다."));
        orderRepository.delete(order);
    }

    @Transactional
    public List<OrderListResDto> findOrders(String username){
       return orderRepository.findAllByUserUsername(username)
               .stream()
               .map(OrderListResDto::new)
               .collect(Collectors.toList());
    }
}
