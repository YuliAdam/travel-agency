package org.example.service;

import org.example.controller.request.OrdersRequest;
import org.example.controller.response.OfferResponse;
import org.example.controller.response.OrdersResponse;
import org.example.entity.Hotel;
import org.example.entity.Orders;
import org.example.entity.User;
import org.example.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class OrdersService {
    @Autowired
    OrdersRepository ordersRepository;

    public List<OrdersResponse> getAllOrder() {
        return ordersRepository.findAllOrders().stream().map(OrdersResponse::new).toList();
    }
    public List<OrdersResponse> findById(Long id){
            return ordersRepository.findAll().stream().map(OrdersResponse::new).filter(orders -> orders.getId()==id).toList();
    }
    public String deleteOrder(Long id){
        try {
            ordersRepository.findById(id).get();
            ordersRepository.deleteById(id);
            return "Order deleted.";
        } catch (NoSuchElementException e){
            System.out.println("Error. Class OrdersService. Order not found. "+ e);
            return "Order not found. "+e;
        }
    }
    public OrdersResponse createOrder(OrdersRequest ordersRequest){
        try {
        Orders orders= new Orders(ordersRequest.getUser(),ordersRequest.getOffer());
        orders.setOrderDate(new Timestamp(new Date().getTime()));
        return  new OrdersResponse(ordersRepository.save(orders));
        }catch (DataIntegrityViolationException e){
            System.out.println("Error. Class OrdersService. The order's user and offer cannot be null. "+e);
            User user= new User();
            user.setUserName("The order's user and offer cannot be null. "+e);
            return new OrdersResponse(user);
        }
    }
    public OrdersResponse updateOrder(Long id, OrdersRequest ordersRequest){
        try {
        Orders existingOrders = ordersRepository.findById(id).get();
        existingOrders.setUser(ordersRequest.getUser());
        existingOrders.setOffer(ordersRequest.getOffer());
        return new OrdersResponse(ordersRepository.save(existingOrders));
        }catch (NoSuchElementException e){
            System.out.println("Error. Class OrdersService. Order not found. "+ e);
            User user= new User();
            user.setUserName("Order not found. "+e);
            return new OrdersResponse(user);
        }catch (DataIntegrityViolationException e){
            System.out.println("Error. Class OrdersService. The order's user and offer cannot be null. "+e);
            User user= new User();
            user.setUserName("The order's user and offer cannot be null. "+e);
            return new OrdersResponse(user);
        }
    }
}