package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.controller.request.OrdersRequest;
import org.example.service.*;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/orders")
@Validated
@RequiredArgsConstructor
public class OrdersController {

    private final OrderService orderService;

    private final OfferService offerService;

    private final UserService userService;

    private final HotelService hotelService;

    private final MoneyService moneyService;

    @GetMapping("/search")
    public ModelAndView getOrders(Model model,
                                  @RequestParam(required = false) String paramtr,
                                  @RequestParam(required = false, defaultValue = "id") String sort,
                                  @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                  @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        ModelAttributes.paginationAttributes(model, paramtr, sort, pageNumber, pageSize);
        ModelAttributes.moneyAttributes(model, moneyService, userService);
        ModelAttributes.offerAttributes(model, offerService, hotelService);
        model.addAttribute("users", userService.getAllUser());
        return new ModelAndView("orders", "orders", orderService.findOrders(paramtr, sort, pageNumber, pageSize));
    }

    @GetMapping("/myOrder")
    public ModelAndView getMyOrder(Model model,
                                   @RequestParam(required = false) String paramtr,
                                   @RequestParam(required = false, defaultValue = "id") String sort,
                                   @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                   @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        ModelAttributes.paginationAttributes(model, paramtr, sort, pageNumber, pageSize);
        ModelAttributes.moneyAttributes(model, moneyService, userService);
        ModelAttributes.offerAttributes(model, offerService, hotelService);
        ModelAttributes.orderAttributes(model, orderService, userService);
        return new ModelAndView("myOrder", "orders", orderService.findOrders(paramtr, sort, pageNumber, pageSize));
    }

    @GetMapping("/{id}/redirect/update")
    public ModelAndView findById(@PathVariable Long id, Model model) {
        ModelAttributes.orderAttributes(model, orderService, userService);
        ModelAttributes.offerAttributes(model, offerService, hotelService);
        return new ModelAndView("order", "order", orderService.findById(id));
    }

    @GetMapping("/{offerId}/redirect/create")
    public ModelAndView getOrder(@PathVariable Long offerId, Model model) {
        ModelAttributes.offerAttributes(model, offerService, hotelService);
        ModelAttributes.moneyAttributes(model, moneyService, userService);
        model.addAttribute("order", new OrdersRequest());
        model.addAttribute("offerId", offerId);
        model.addAttribute("offerByCurrentId", offerService.findById(offerId));
        return new ModelAndView("addOrder");
    }

    @DeleteMapping("/{id}/delete")
    public ModelAndView deleteOrder(@PathVariable Long id, Model model,
                                    @RequestParam(required = false) String paramtr,
                                    @RequestParam(required = false, defaultValue = "start") String sort,
                                    @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                    @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        ModelAttributes.paginationAttributes(model, paramtr, sort, pageNumber, pageSize);
        orderService.deleteOrder(id);
        ModelAttributes.moneyAttributes(model, moneyService, userService);
        ModelAttributes.orderAttributes(model, orderService, userService);
        ModelAttributes.offerAttributes(model, offerService, hotelService);
        return new ModelAndView("offers", "offers", offerService.findOffers(paramtr, sort, pageNumber, pageSize));
    }

    @PostMapping("/create")
    public ModelAndView createOrder(@ModelAttribute("orders") @Valid OrdersRequest ordersRequest, Model model,
                                    @RequestParam(required = false) String paramtr,
                                    @RequestParam(required = false, defaultValue = "start") String sort,
                                    @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                    @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        ModelAttributes.paginationAttributes(model, paramtr, sort, pageNumber, pageSize);
        orderService.createOrder(ordersRequest);
        ModelAttributes.moneyAttributes(model, moneyService, userService);
        ModelAttributes.orderAttributes(model, orderService, userService);
        ModelAttributes.offerAttributes(model, offerService, hotelService);
        return new ModelAndView("offers", "offers", offerService.findOffers(paramtr, sort, pageNumber, pageSize));
    }

    @PutMapping("/update")
    public ModelAndView updateOrder(@ModelAttribute("orders") @Valid OrdersRequest ordersRequest, Model model,
                                    @RequestParam(required = false) String paramtr,
                                    @RequestParam(required = false, defaultValue = "order_date") String sort,
                                    @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                    @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        paramtr = ordersRequest.getUserId().toString();
        ModelAttributes.paginationAttributes(model, paramtr, sort, pageNumber, pageSize);
        ModelAttributes.moneyAttributes(model, moneyService, userService);
        ModelAttributes.orderAttributes(model, orderService, userService);
        ModelAttributes.offerAttributes(model, offerService, hotelService);
        orderService.updateOrder(ordersRequest.getId(), ordersRequest);
        return new ModelAndView("orders", "orders", orderService.findOrders(paramtr, sort, pageNumber, pageSize));
    }
}