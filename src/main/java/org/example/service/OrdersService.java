package org.example.service;

import org.example.controller.request.OrdersRequest;
import org.example.controller.response.HotelResponse;
import org.example.controller.response.OrdersResponse;
import org.example.controller.response.UserResponse;
import org.example.entity.Orders;
import org.example.entity.Users;
import org.example.repository.OfferRepository;
import org.example.repository.OrdersRepository;
import org.example.repository.UserRepository;
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
    private OrdersRepository ordersRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private MoneyService moneyService;

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
    public List<OrdersResponse> findOrderByUserId(Long id){
        return ordersRepository.findOrderByUserId(id).stream().map(OrdersResponse::new).toList();
    }

    public String deleteOrder(Long id) {
            Orders order=ordersRepository.findById(id).get();
            moneyService.updateMoney(order.getUser().getId(),order.getOffer().getPrice());
            ordersRepository.deleteById(id);
            return "Order deleted.";
    }

    public OrdersResponse createOrder(OrdersRequest ordersRequest) {
            moneyService.updateMoney(ordersRequest.getUserId(),-offerRepository.findOfferById(ordersRequest.getOfferId()).getPrice());
            Orders orders = new Orders(userRepository.findUserById(ordersRequest.getUserId()), offerRepository.findOfferById(ordersRequest.getOfferId()));
            orders.setOrderDate(new Timestamp(new Date().getTime()));
            return new OrdersResponse(ordersRepository.save(orders));
    }

    public OrdersResponse updateOrder(Long id, OrdersRequest ordersRequest) {
            Orders existingOrders = ordersRepository.findById(id).get();
            moneyService.updateMoney(existingOrders.getUser().getId(),existingOrders.getOffer().getPrice());
            moneyService.updateMoney(ordersRequest.getUserId(),-offerRepository.findOfferById(ordersRequest.getOfferId()).getPrice());

            existingOrders.setUser(userRepository.findUserById(ordersRequest.getUserId()));
            existingOrders.setOffer(offerRepository.findOfferById(ordersRequest.getOfferId()));
            return new OrdersResponse(ordersRepository.save(existingOrders));
    }
}