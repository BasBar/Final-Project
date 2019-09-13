package com.sda.restaurant.restaurant.services;

import com.sda.restaurant.restaurant.DTO.OrderDTO;
import com.sda.restaurant.restaurant.form.OrderForm;
import com.sda.restaurant.restaurant.model.MenuEntity;
import com.sda.restaurant.restaurant.model.OrderEntity;
import com.sda.restaurant.restaurant.model.ReservationEntity;
import com.sda.restaurant.restaurant.repositories.MenuRepository;
import com.sda.restaurant.restaurant.repositories.OrderRepository;
import com.sda.restaurant.restaurant.repositories.ReservationRepository;
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

    public Long addOrder(OrderForm orderForm){

        ReservationEntity reservationEntity = reservationRepository.getById(orderForm.getReservationId());
        List<Long> menuIds = Arrays.asList(orderForm.getMenuIds());

        Set<MenuEntity> menuToSave = menuRepository.findAll()
                .stream()
                .filter(menuEntity -> menuIds.contains(menuEntity.getId()))
                .collect(Collectors.toSet());

        Double totalPrice = calculateTotal(orderForm);

        OrderEntity orderEntity = new OrderEntity(
                reservationEntity,
                menuToSave,
                totalPrice);


        return orderRepository.save(orderEntity).getId();
    }

    public OrderDTO setPaidOrder(Long id) {
        OrderEntity getOrder = orderRepository.getById(id);
        getOrder.setPaid(true);
        orderRepository.save(getOrder);
        return modelMapper.map(getOrder, OrderDTO.class);
    }

    public OrderDTO updateTipAmount(Long id, Float tipAmount) {
        OrderEntity getOrder = orderRepository.getById(id);
        getOrder.setTip(tipAmount);
        orderRepository.save(getOrder);
        return modelMapper.map(getOrder, OrderDTO.class);
    }

    private Double calculateTotal(OrderForm orderForm) {

        ReservationEntity reservationEntity = reservationRepository.getById(orderForm.getReservationId());

        List<Long> menuIds = Arrays.asList(orderForm.getMenuIds());

        return menuRepository.findAll()
                .stream()
                .filter(menuEntity -> menuIds.contains(menuEntity.getId()))
                .mapToDouble(MenuEntity::getPrice)
                .sum();
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    /*public Set<Long> getAllOrderMenuItems(OrderController.OrderForm orderForm){
        return orderForm.getMenuIds();
    }*/
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
}
