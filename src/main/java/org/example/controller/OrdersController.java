package org.example.controller;

import org.example.controller.request.OfferRequest;
import org.example.controller.request.OrdersRequest;
import org.example.controller.response.OrdersResponse;
import org.example.entity.Orders;
import org.example.entity.characteristic.Country;
import org.example.entity.characteristic.Transport;
import org.example.entity.characteristic.Type;
import org.example.service.HotelService;
import org.example.service.OfferService;
import org.example.service.OrdersService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private OfferService offerService;
    @Autowired
    private UserService userService;
    @Autowired
    private HotelService hotelService;

    @GetMapping("/search")
    public ModelAndView getOrders(Model model,
                                  @RequestParam(required = false) String paramtr,
                                  @RequestParam(required = false, defaultValue = "id") String sort,
                                  @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                  @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        model.addAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        model.addAttribute("paramtr", paramtr);
        model.addAttribute("sort", sort);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);

        model.addAttribute("types", Type.values());
        model.addAttribute("transports", Transport.values());
        model.addAttribute("countries", Country.values());

        model.addAttribute("role",SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        model.addAttribute("currentUserId",userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        model.addAttribute("users",userService.getAllUser());
        model.addAttribute("offers",offerService.getAllOffer());
        model.addAttribute("hotels",hotelService.getAllHotel());
        return new ModelAndView("orders", "orders", ordersService.findOrders(paramtr, sort, pageNumber, pageSize));
    }
    @GetMapping("/myOrder")
    public ModelAndView getMyOrder(Model model,
                                  @RequestParam(required = false) String paramtr,
                                  @RequestParam(required = false, defaultValue = "id") String sort,
                                  @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                  @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        model.addAttribute("paramtr", paramtr);
        model.addAttribute("sort", sort);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);

        model.addAttribute("types", Type.values());
        model.addAttribute("transports", Transport.values());
        model.addAttribute("countries", Country.values());

        model.addAttribute("role",SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        model.addAttribute("currentUserId",userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        model.addAttribute("orderByCurrentUserId",ordersService.findOrderByUserId(userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));

        model.addAttribute("users",userService.getAllUser());
        model.addAttribute("offers",offerService.getAllOffer());
        model.addAttribute("hotels",hotelService.getAllHotel());
        model.addAttribute("orders",ordersService.getAllOrder());
        return new ModelAndView("myOrder", "orders", ordersService.findOrders(paramtr, sort, pageNumber, pageSize));
    }
    @GetMapping("/{id}/redirect/update")
    public ModelAndView findById(@PathVariable Long id, Model model) {
        model.addAttribute("types", Type.values());
        model.addAttribute("transports", Transport.values());
        model.addAttribute("countries", Country.values());
        model.addAttribute("order", new OrdersRequest());

        return new ModelAndView("order", "order", ordersService.findById(id));
    }

    @GetMapping("/{offerId}/redirect/create")
    public ModelAndView getOrder( @PathVariable Long offerId,Model model) {
        model.addAttribute("types", Type.values());
        model.addAttribute("transports", Transport.values());
        model.addAttribute("countries", Country.values());
        model.addAttribute("currentUserId",userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        model.addAttribute("order", new OrdersRequest());
        model.addAttribute("offerId",offerId);
        model.addAttribute("offerByCurrentId",offerService.findById(offerId));
        model.addAttribute("hotels",hotelService.getAllHotel());
        return new ModelAndView("addOrder");
    }

    @DeleteMapping("/{id}/delete")
    public ModelAndView deleteOrder(@PathVariable Long id, Model model,
                                    @RequestParam(required = false) String paramtr,
                                    @RequestParam(required = false, defaultValue = "start") String sort,
                                    @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                    @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        model.addAttribute("paramtr", paramtr);
        model.addAttribute("sort", sort);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        ordersService.deleteOrder(id);
        model.addAttribute("types", Type.values());
        model.addAttribute("transports", Transport.values());
        model.addAttribute("countries", Country.values());
        model.addAttribute("order", new OrdersRequest());

        model.addAttribute("role",SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        model.addAttribute("currentUserId",userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        model.addAttribute("users",userService.getAllUser());
        model.addAttribute("offers",offerService.getAllOffer());
        model.addAttribute("hotels",hotelService.getAllHotel());
        return new ModelAndView("offers", "offers", offerService.findOffers(paramtr, sort, pageNumber, pageSize));
    }

    @PostMapping("/create")
    public ModelAndView createOrder(@ModelAttribute("orders")OrdersRequest ordersRequest, Model model,
                                    @RequestParam(required = false) String paramtr,
                                    @RequestParam(required = false, defaultValue = "start") String sort,
                                    @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                    @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        model.addAttribute("paramtr", paramtr);
        model.addAttribute("sort", sort);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        ordersService.createOrder(ordersRequest);
        model.addAttribute("types", Type.values());
        model.addAttribute("transports", Transport.values());
        model.addAttribute("countries", Country.values());
        model.addAttribute("order", new OrdersRequest());

        model.addAttribute("role",SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        model.addAttribute("currentUserId",userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        model.addAttribute("users",userService.getAllUser());
        model.addAttribute("offers",offerService.getAllOffer());
        model.addAttribute("hotels",hotelService.getAllHotel());
        return new ModelAndView("offers", "offers", offerService.findOffers(paramtr, sort, pageNumber, pageSize));
    }

    @PutMapping("/update")
    public ModelAndView updateOrder(@ModelAttribute("orders") OrdersRequest ordersRequest, Model model,
                                    @RequestParam(required = false) String paramtr,
                                    @RequestParam(required = false, defaultValue = "order_date") String sort,
                                    @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                    @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        paramtr=ordersRequest.getUserId().toString();
        model.addAttribute("paramtr", paramtr);
        model.addAttribute("sort", sort);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        ordersService.updateOrder(ordersRequest.getId(),ordersRequest);
        model.addAttribute("types", Type.values());
        model.addAttribute("transports", Transport.values());
        model.addAttribute("countries", Country.values());
        model.addAttribute("hotelId", offerService.getAllHotelId());
        model.addAttribute("order", new OrdersRequest());

        model.addAttribute("role",SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        model.addAttribute("currentUserId",userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        model.addAttribute("users",userService.getAllUser());
        model.addAttribute("offers",offerService.getAllOffer());
        model.addAttribute("hotels",hotelService.getAllHotel());
        return new ModelAndView("orders", "orders", ordersService.findOrders(paramtr, sort, pageNumber, pageSize));
    }
}