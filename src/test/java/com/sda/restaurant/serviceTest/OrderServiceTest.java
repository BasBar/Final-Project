package com.sda.restaurant.serviceTest;

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

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    MenuRepository menuRepository;

    @InjectMocks
    OrderService orderService;

    Double totalPrice;
    Menu menu;
    Menu menu2;
    OrderForm orderForm;
    Reservation reservation;
    Set<Menu> menus;
    Order order;

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
    }
    @Test
    public void whenAddOrderShouldReturnItsId(){
        when(orderRepository.save(any())).thenReturn(order);
        Long created = orderService.addOrder(orderForm);
        assertThat(created).isEqualTo(order.getId());
    }
}
