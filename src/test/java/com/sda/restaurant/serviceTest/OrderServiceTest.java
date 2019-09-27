package com.sda.restaurant.serviceTest;

import com.sda.restaurant.form.OrderForm;
import com.sda.restaurant.model.Menu;
import com.sda.restaurant.model.Order;
import com.sda.restaurant.model.Reservation;
import com.sda.restaurant.repositories.MenuRepository;
import com.sda.restaurant.repositories.OrderRepository;
import com.sda.restaurant.repositories.ReservationRepository;
import com.sda.restaurant.services.OrderService;
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

    @Test
    public void whenAddOrderShouldReturnItsId(){

        Double totalPrice = 5d;

        Menu menu = new Menu();
        Menu menu2 = new Menu();

        OrderForm orderForm = new OrderForm();
        orderForm.setReservationId(5L);
        orderForm.setMenuIds(new Long[]{3L, 4L});

        Reservation reservation = new Reservation();

        Set<Menu> menus = new HashSet<>();
        menus.add(menu);
        menus.add(menu2);

        Order order = new Order (reservation,menus,totalPrice);
        order.setId(4L);
        when(orderRepository.save(any())).thenReturn(order);
        Long created = orderService.addOrder(orderForm);
        assertThat(created).isEqualTo(order.getId());

    }
}
