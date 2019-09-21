package com.sda.restaurant.services;

import com.sda.restaurant.DTO.ReservationDTO;
import com.sda.restaurant.form.ReservationForm;
import com.sda.restaurant.model.Client;
import com.sda.restaurant.model.Reservation;
import com.sda.restaurant.model.Tables;
import com.sda.restaurant.repositories.ClientRepository;
import com.sda.restaurant.repositories.ReservationRepository;
import com.sda.restaurant.repositories.TableRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final TableRepository tableRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ClientRepository clientRepository, TableRepository tableRepository, ModelMapper modelMapper) {
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
        this.tableRepository = tableRepository;
        this.modelMapper = modelMapper;
    }

    public Long saveReservation(ReservationForm reservationForm) {

        Client clientToSave = clientRepository.getOne(reservationForm.getClientId());
        List<Long> getTables = Arrays.asList(reservationForm.getTablesId());

        Set<Tables> tablesToSave = tableRepository.findAll()
                .stream()
                .filter(tables -> getTables.contains(tables.getId()))
                .collect(Collectors.toSet());

        for (Tables occupy : tablesToSave) {
            occupy.setOccupied(true);
        }

        Reservation reservation = new Reservation(
                reservationForm.getDateAndTime(),
                clientToSave,
                tablesToSave
        );

        return reservationRepository.save(reservation).getId();
    }

    public void deleteReservationById(Long id) {
        Reservation reservationToDelete = reservationRepository.getById(id);
        Set<Tables> getTables = reservationToDelete.getTables();
        for (Tables tables : getTables) {
            tables.setOccupied(false);
        }
        reservationRepository.deleteById(id);
    }

    public ReservationDTO getReservationById(Long id) {
        Reservation foundReservation = reservationRepository.getById(id);
        return modelMapper.map(foundReservation, ReservationDTO.class);
    }

    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).stream()
                .map(reservationEntity -> modelMapper.map(reservationEntity, ReservationDTO.class))
                .collect(Collectors.toList());
    }
}
