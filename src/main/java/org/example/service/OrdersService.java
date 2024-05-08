package org.example.service;

import org.example.controller.request.OrdersRequest;
import org.example.controller.response.HotelResponse;
import org.example.controller.response.OrdersResponse;
import org.example.controller.response.UserResponse;
import org.example.entity.Orders;
import org.example.entity.Users;
import org.example.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrdersService {
    @Autowired
    OrdersRepository ordersRepository;

    public List<OrdersResponse> getAllOrder() {
        return ordersRepository.findAllOrders().stream().map(OrdersResponse::new).toList();
    }

    public List<OrdersResponse> findOrders(String paramtr, String sort, Integer pageNumber, Integer pageSize) {
        PageRequest page = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, sort));
        if (paramtr == null || paramtr.equals("")) {
            return ordersRepository.findOrders(page).stream()
                    .map(OrdersResponse::new)
                    .toList();
        } else {
            return ordersRepository.findOrders(paramtr.trim(), page).stream()
                    .map(OrdersResponse::new)
                    .toList();
        }
    }


    public List<OrdersResponse> findById(Long id) {
        return ordersRepository.findAll().stream().map(OrdersResponse::new).filter(orders -> orders.getId() == id).toList();
    }

    public String deleteOrder(Long id) {
        try {
            ordersRepository.findById(id).get();
            ordersRepository.deleteById(id);
            return "Order deleted.";
        } catch (NoSuchElementException e) {
            System.out.println("Error. Class OrdersService. Order not found. " + e);
            return "Order not found. " + e;
        }
    }

    public OrdersResponse createOrder(OrdersRequest ordersRequest) {
        try {
            Orders orders = new Orders(ordersRepository.findUserById(ordersRequest.getUserId()), ordersRepository.findOfferById(ordersRequest.getOfferId()));
            orders.setOrderDate(new Timestamp(new Date().getTime()));
            return new OrdersResponse(ordersRepository.save(orders));
        } catch (DataIntegrityViolationException e) {
            System.out.println("Error. Class OrdersService. The order's user and offer cannot be null. " + e);
            return new OrdersResponse();
        }
    }

    public OrdersResponse updateOrder(Long id, OrdersRequest ordersRequest) {
        try {
            Orders existingOrders = ordersRepository.findById(id).get();
            existingOrders.setUser(ordersRepository.findUserById(ordersRequest.getUserId()));
            existingOrders.setOffer(ordersRepository.findOfferById(ordersRequest.getOfferId()));
            return new OrdersResponse(ordersRepository.save(existingOrders));
        } catch (NoSuchElementException e) {
            System.out.println("Error. Class OrdersService. Order not found. " + e);
            return new OrdersResponse();
        } catch (DataIntegrityViolationException e) {
            System.out.println("Error. Class OrdersService. The order's user and offer cannot be null. " + e);
            return new OrdersResponse();
        }
    }
}