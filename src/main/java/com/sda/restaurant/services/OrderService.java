package com.sda.restaurant.services;

import com.sda.restaurant.DTO.OrderDTO;
import com.sda.restaurant.form.OrderForm;
import com.sda.restaurant.model.Menu;
import com.sda.restaurant.model.Order;
import com.sda.restaurant.model.Reservation;
import com.sda.restaurant.repositories.MenuRepository;
import com.sda.restaurant.repositories.OrderRepository;
import com.sda.restaurant.repositories.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final MenuRepository menuRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ModelMapper modelMapper, MenuRepository menuRepository, ReservationRepository reservationRepository) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.menuRepository = menuRepository;
        this.reservationRepository = reservationRepository;
    }

    public Long addOrder(OrderForm orderForm) {

        Reservation reservation = reservationRepository.getById(orderForm.getReservationId());
        List<Long> menuIds = Arrays.asList(orderForm.getMenuIds());
        Double totalPrice = calculateTotal(orderForm);

        Set<Menu> menuToSave = menuRepository.findAll()
                .stream()
                .filter(menu -> menuIds.contains(menu.getId()))
                .collect(Collectors.toSet());

        Order order = new Order(
                reservation,
                menuToSave,
                totalPrice);

        return orderRepository.save(order).getId();
    }

    public OrderDTO setPaidOrder(Long id) {
        Order getOrder = orderRepository.getById(id);
        getOrder.setPaid(true);
        orderRepository.save(getOrder);
        return modelMapper.map(getOrder, OrderDTO.class);
    }

    public OrderDTO updateTipAmount(Long id, Float tipAmount) {
        Order getOrder = orderRepository.getById(id);
        getOrder.setTip(tipAmount);
        orderRepository.save(getOrder);
        return modelMapper.map(getOrder, OrderDTO.class);
    }

    private Double calculateTotal(OrderForm orderForm) {

        List<Long> menuIds = Arrays.asList(orderForm.getMenuIds());

        return menuRepository.findAll()
                .stream()
                .filter(menu -> menuIds.contains(menu.getId()))
                .mapToDouble(Menu::getPrice)
                .sum();
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
}
