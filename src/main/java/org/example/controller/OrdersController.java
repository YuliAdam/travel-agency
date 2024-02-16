package org.example.controller;

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

    @GetMapping("/all")
    public List<Orders> getAllOrder(){
        return ordersService.getAllOrder();
    }
    @GetMapping("/id")
    public List<Orders> findById(int id){
        return ordersService.findById(id);
    }
    @DeleteMapping("/delete/id")
    public String deleteOrder(@RequestParam int id){
        return ordersService.deleteOrder(id);
    }
    @PostMapping("/create")
    public Orders createOrder(@RequestBody Orders orders){
        return ordersService.createOrder(orders);
    }
    @PutMapping("/id")
    public Orders updateOrder(@RequestParam int id, @RequestBody Orders orders){
        return ordersService.updateOrder(id, orders);
    }
}