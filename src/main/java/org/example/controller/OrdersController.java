package org.example.controller;

import org.example.controller.request.OrdersRequest;
import org.example.controller.response.OrdersResponse;
import org.example.entity.Orders;
import org.example.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    OrdersService ordersService;

    @GetMapping
    public List<OrdersResponse> getAllOrder(){
        return ordersService.getAllOrder();
    }
    @GetMapping("/{id}")
    public List<OrdersResponse> findById(@PathVariable Long id){
        return ordersService.findById(id);
    }
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id){
        return ordersService.deleteOrder(id);
    }
    @PostMapping
    public OrdersResponse createOrder(@RequestBody OrdersRequest ordersRequest){
        return ordersService.createOrder(ordersRequest);
    }
    @PutMapping("/{id}")
    public OrdersResponse updateOrder(@PathVariable Long id, @RequestBody OrdersRequest ordersRequest){
        return ordersService.updateOrder(id, ordersRequest);
    }
}