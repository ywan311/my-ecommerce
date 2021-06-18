package com.example.myecommerce.Web.ApiController;

import com.example.myecommerce.Service.Order.OrderService;
import com.example.myecommerce.Web.Dto.Order.OrderListResDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "주문 controller")
@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;

    @GetMapping(value = "/api/v1/order")
    public List<OrderListResDto> findOrders(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return orderService.findOrders(authentication.getName());
    }

    @PostMapping(value = "/api/v1/order/{prodId}")
    public Long save(@PathVariable Long prodId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return orderService.save(prodId,authentication.getName());
    }

    @DeleteMapping(value = "/api/v1/order/{id}")
    public Long delete(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        orderService.delete(id);
        return id;
    }


}
