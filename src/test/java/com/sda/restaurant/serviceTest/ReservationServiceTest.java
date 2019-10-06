package com.sda.restaurant.serviceTest;

import com.sda.restaurant.form.ReservationForm;
import com.sda.restaurant.model.Client;
import com.sda.restaurant.model.Reservation;
import com.sda.restaurant.model.Tables;
import com.sda.restaurant.repositories.ClientRepository;
import com.sda.restaurant.repositories.ReservationRepository;
import com.sda.restaurant.repositories.TableRepository;
import com.sda.restaurant.services.ReservationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTest {

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    ClientRepository clientRepository;

    @Mock
    TableRepository tableRepository;

    @InjectMocks
    ReservationService reservationService;

    Set<Tables> tablesSet;
    Tables tables1;
    ReservationForm reservationForm;
    Reservation reservation;

    @Before
    public void setUp() {

        tablesSet = new HashSet<>();
        tables1 = new Tables();
        tablesSet.add(tables1);
        reservationForm = new ReservationForm();
        reservationForm.setTablesId(new Long[]{1L, 2L});
        reservation = new Reservation();
        reservation.setId(5L);
    }
    @Test
    public void whenSaveReservationReturnItsId(){
        when(reservationRepository.save(any())).thenReturn(reservation);
        Long created = reservationService.saveReservation(reservationForm);
        assertThat(created).isEqualTo(5L);
    }
}
