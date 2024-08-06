package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.controller.request.OrdersRequest;
import org.example.controller.response.OrdersResponse;
import org.example.entity.Offer;
import org.example.entity.Orders;
import org.example.entity.Users;
import org.example.exception.EntityNotExistException;
import org.example.repository.OfferRepository;
import org.example.repository.OrdersRepository;
import org.example.repository.UserRepository;
import org.example.utils.Messages;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrdersRepository ordersRepository;

    private final UserRepository userRepository;

    private final OfferRepository offerRepository;

    private final MoneyService moneyService;

    public List<OrdersResponse> getAllOrder() {
        return ordersRepository.findAllOrders().stream().map(OrdersResponse::new).toList();
    }

    public List<OrdersResponse> findOrders(String paramtr, String sort, Integer pageNumber, Integer pageSize) {
        PageRequest page = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, sort));
        return (paramtr == null || paramtr.isBlank())
                ? ordersRepository.findOrders(page).stream().map(OrdersResponse::new).toList()
                : ordersRepository.findOrders(paramtr.trim(), page).stream().map(OrdersResponse::new).toList();
    }

    public List<OrdersResponse> findById(Long id) {
        return ordersRepository.findAll().stream().map(OrdersResponse::new).filter(orders -> orders.getId() == id).toList();
    }

    public List<OrdersResponse> findOrderByUserId(Long id) {
        return ordersRepository.findOrderByUserId(id).stream().map(OrdersResponse::new).toList();
    }

    @Transactional
    public void deleteOrder(Long id) {
        Orders order = ordersRepository.findById(id).orElseThrow(() -> new EntityNotExistException(String.format(Messages.ORDER_NOT_EXIST, id)));
        Long userId = order.getUser().getId();
        Float price = order.getOffer().getPrice();
        //Return money to user
        moneyService.updateMoney(userId, price);
        ordersRepository.deleteById(id);
    }

    @Transactional
    public OrdersResponse createOrder(OrdersRequest ordersRequest) {
        Long userId = ordersRequest.getUserId();
        Float price = offerRepository.findOfferById(ordersRequest.getOfferId()).getPrice();
        //Debit money
        moneyService.updateMoney(userId, -price);
        Users userById = userRepository.findUserById(userId);
        Offer offerById = offerRepository.findOfferById(ordersRequest.getOfferId());
        //Create order
        Orders orders = new Orders(userById, offerById);
        orders.setOrderDate(new Timestamp(new Date().getTime()));
        return new OrdersResponse(ordersRepository.save(orders));
    }

    @Transactional
    public OrdersResponse updateOrder(Long id, OrdersRequest ordersRequest) {
        Orders existingOrders = ordersRepository.findById(id).orElseThrow(() -> new EntityNotExistException(String.format(Messages.ORDER_NOT_EXIST, id)));
        // Add money for initial user because existing offer not buys
        moneyService.updateMoney(existingOrders.getUser().getId(), existingOrders.getOffer().getPrice());
        // Debit money of new updated offer for new updated user
        moneyService.updateMoney(ordersRequest.getUserId(), -offerRepository.findOfferById(ordersRequest.getOfferId()).getPrice());
        // Update existing order
        existingOrders.setUser(userRepository.findUserById(ordersRequest.getUserId()));
        existingOrders.setOffer(offerRepository.findOfferById(ordersRequest.getOfferId()));
        return new OrdersResponse(ordersRepository.save(existingOrders));
    }

}