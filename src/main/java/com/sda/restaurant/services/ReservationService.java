package com.sda.restaurant.services;

import com.sda.restaurant.DTO.ReservationDTO;
import com.sda.restaurant.form.ReservationForm;
import com.sda.restaurant.model.ClientEntity;
import com.sda.restaurant.model.ReservationEntity;
import com.sda.restaurant.model.TablesEntity;
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

        ClientEntity clientToSave = clientRepository.getOne(reservationForm.getClientId());

        List<Long> getTables = Arrays.asList(reservationForm.getTablesId());

        Set<TablesEntity> tablesToSave = tableRepository.findAll()
                .stream()
                .filter(tablesEntity -> getTables.contains(tablesEntity.getId()))
                .collect(Collectors.toSet());

        for (TablesEntity occupy : tablesToSave) {
            occupy.setOccupied(true);
        }

        ReservationEntity reservationEntity = new ReservationEntity(
                reservationForm.getDateAndTime(),
                clientToSave,
                tablesToSave
        );

        return reservationRepository.save(reservationEntity).getId();
    }

    public void deleteReservationById(Long id) {
        ReservationEntity reservationToDelete = reservationRepository.getById(id);
        Set<TablesEntity> getTables = reservationToDelete.getTables();
        for (TablesEntity tables : getTables){
            tables.setOccupied(false);
        }
        reservationRepository.deleteById(id);
    }

    public ReservationDTO getReservationById(Long id) {
        ReservationEntity foundReservation = reservationRepository.getById(id);
        return modelMapper.map(foundReservation, ReservationDTO.class);
    }

    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).stream()
                .map(reservationEntity -> modelMapper.map(reservationEntity, ReservationDTO.class))
                .collect(Collectors.toList());
    }


}
