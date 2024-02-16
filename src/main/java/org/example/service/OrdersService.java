package org.example.service;

import org.example.entity.Orders;
import org.example.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersService {
    @Autowired
    OrdersRepository ordersRepository;

    public List<Orders> getAllOrder() {
        return ordersRepository.findAllOrders().stream().collect(Collectors.toList());
    }
    public List<Orders> findById(int id){
        try {
            return ordersRepository.findAll().stream().filter(orders -> orders.getId()==id).collect(Collectors.toList());
        } catch (Exception e){
            return null;
        }
    }
    public String deleteOrder(int id){
        try {
            ordersRepository.findById(id).get();
            ordersRepository.deleteById(id);
            return "Orders deleted";
        } catch (Exception e){
            return "Orders not found";
        }
    }
    public Orders createOrder(Orders orders){
        return  ordersRepository.save(orders);
    }
    public Orders updateOrder(int id, Orders orders){
        Orders existingOrders = ordersRepository.findById(id).get();
        existingOrders.setUser(orders.getUser());
        existingOrders.setOffer(orders.getOffer());
        existingOrders.setOrderDate(orders.getOrderDate());
        return ordersRepository.save(existingOrders);
    }
}