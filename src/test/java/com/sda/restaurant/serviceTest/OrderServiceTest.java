package com.sda.restaurant.serviceTest;

import com.sda.restaurant.DTO.OrderDTO;
import com.sda.restaurant.form.OrderForm;
import com.sda.restaurant.model.Menu;
import com.sda.restaurant.model.Order;
import com.sda.restaurant.model.Reservation;
import com.sda.restaurant.repositories.MenuRepository;
import com.sda.restaurant.repositories.OrderRepository;
import com.sda.restaurant.repositories.ReservationRepository;
import com.sda.restaurant.services.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    MenuRepository menuRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    OrderService orderService;

    private Double totalPrice;
    private Menu menu;
    private Menu menu2;
    private OrderForm orderForm;
    private Reservation reservation;
    private Set<Menu> menus;
    private Order order;
    private List<Order> orderList;
    private OrderDTO orderDTO;
    private List<OrderDTO> orderDTOList;

    @Before
    public void setUp() {
        totalPrice = 5d;
        menu = new Menu();
        menu2 = new Menu();
        orderForm = new OrderForm();
        orderForm.setReservationId(5L);
        orderForm.setMenuIds(new Long[]{3L, 4L});
        reservation = new Reservation();
        menus = new HashSet<>();
        menus.add(menu);
        menus.add(menu2);
        order = new Order (reservation,menus,totalPrice);
        order.setId(4L);
        orderList = new ArrayList<>();
        orderList.add(order);
        orderDTO = new OrderDTO();
        orderDTO.setId(3L);
        orderDTOList = new ArrayList<>();
        orderDTOList.add(orderDTO);
    }
    @Test
    public void whenAddOrderShouldReturnItsIdTest(){
        when(orderRepository.save(any())).thenReturn(order);
        Long created = orderService.addOrder(orderForm);
        assertThat(created).isEqualTo(order.getId());
    }
    @Test
    public void deleteOrderByIdTest(){
        orderService.deleteOrderById(order.getId());
        verify(orderRepository,times(1)).deleteById(order.getId());
    }

    @Test
    public void getAllOrdersTest(){
        when(modelMapper.map(any(),any())).thenReturn(orderDTO);
        when(orderRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))).thenReturn(orderList);
        List<OrderDTO> result = orderService.getAllOrders();
        assertThat(result).isEqualTo(orderDTOList);
    }
}
