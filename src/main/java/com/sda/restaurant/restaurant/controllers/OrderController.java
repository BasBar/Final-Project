package com.sda.restaurant.restaurant.controllers;

import com.sda.restaurant.restaurant.DTO.ReservationDTO;
import com.sda.restaurant.restaurant.form.OrderForm;
import com.sda.restaurant.restaurant.repositories.OrderRepository;
import com.sda.restaurant.restaurant.services.MenuService;
import com.sda.restaurant.restaurant.services.OrderService;
import com.sda.restaurant.restaurant.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class OrderController {
    private OrderService orderService;
    private OrderRepository orderRepository;
    private ReservationService reservationService;
    private MenuService menuService;

    @Autowired
    public OrderController(OrderService orderService, OrderRepository orderRepository, ReservationService reservationService, MenuService menuService) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
        this.reservationService = reservationService;
        this.menuService = menuService;
    }

    @GetMapping("/allOrdersPage")
    public String displayAllOrders(Model model) {
        setupModel(model);
        return "allOrdersPage";
    }

    @PostMapping("/addOrderAction")
    public RedirectView addNewOrder(@ModelAttribute("orderForm") OrderForm orderForm, Model model) {
        orderService.addOrder(orderForm);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/allOrdersPage");
        return redirectView;
    }

    @DeleteMapping("/deleteOrderAction/{id}")
    public RedirectView deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/allOrdersPage");
        return redirectView;
    }

    // copy to orders
    @PutMapping("/setPaid/{id}")
    public RedirectView setPaidOrder(@PathVariable Long id) {
        orderService.setPaidOrder(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/allOrdersPage");
        return redirectView;
    }
    @PostMapping("/setTipAmount/{id}")
    public RedirectView updateTipAmount(@PathVariable Long id, @RequestParam Float tip) {
        orderService.updateTipAmount(id, tip);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/allOrdersPage");
        return redirectView;
    }
    //end of.

    private void setupModel(Model model) {
        List<ReservationDTO> allReservations = reservationService.getAllReservations();
        model.addAttribute("AllReservations", allReservations);
        model.addAttribute("allMenus", menuService.getAllMenus());
        model.addAttribute("allOrders", orderService.getAllOrders());
        model.addAttribute("orderForm", new OrderForm());
    }
}


