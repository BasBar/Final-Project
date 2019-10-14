package com.sda.restaurant.serviceTest;

import com.sda.restaurant.DTO.ReservationDTO;
import com.sda.restaurant.form.ReservationForm;
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
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTest {

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    ClientRepository clientRepository;

    @Mock
    TableRepository tableRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    ReservationService reservationService;

    private Set<Tables> tablesSet;
    private Tables tables1;
    private ReservationForm reservationForm;
    private Reservation reservation;
    private ReservationDTO reservationDTO;
    private List<Reservation> reservationList;
    private List<ReservationDTO> reservationDTOList;

    @Before
    public void setUp() {

        tablesSet = new HashSet<>();
        tables1 = new Tables();
        tablesSet.add(tables1);
        reservationForm = new ReservationForm();
        reservationForm.setTablesId(new Long[]{1L, 2L});
        reservation = new Reservation();
        reservation.setId(5L);
        reservationDTO = new ReservationDTO();
        reservationDTO.setId(3L);
        reservationList = new ArrayList<>();
        reservationList.add(reservation);
        reservationDTOList = new ArrayList<>();
        reservationDTOList.add(reservationDTO);

    }
    @Test
    public void whenSaveReservationReturnItsId(){
        when(reservationRepository.save(any())).thenReturn(reservation);
        Long created = reservationService.saveReservation(reservationForm);
        assertThat(created).isEqualTo(5L);
    }

    @Test
    public void deleteReservationByIdTest(){
        reservationService.deleteReservationById(reservation.getId());
        verify(reservationRepository,times(1)).deleteById(reservation.getId());
    }

    @Test
    public void getAllReservationTest(){
        when(modelMapper.map(any(),any())).thenReturn(reservationDTO);
        when(reservationRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))).thenReturn(reservationList);
        List<ReservationDTO> result = reservationService.getAllReservations();
        assertThat(result).isEqualTo(reservationDTOList);
    }
}
