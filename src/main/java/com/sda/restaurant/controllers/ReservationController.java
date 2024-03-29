package com.sda.restaurant.controllers;

import com.sda.restaurant.DTO.ReservationDTO;
import com.sda.restaurant.form.ReservationForm;
import com.sda.restaurant.model.Reservation;
import com.sda.restaurant.repositories.ReservationRepository;
import com.sda.restaurant.services.ClientService;
import com.sda.restaurant.services.ReservationService;
import com.sda.restaurant.services.TableService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class ReservationController {

    private ReservationService reservationService;
    private ReservationRepository reservationRepository;
    private ClientService clientService;
    private TableService tableService;

    @Autowired
    public ReservationController(ReservationService reservationService, ReservationRepository reservationRepository, ClientService clientService, TableService tableService) {
        this.reservationService = reservationService;
        this.reservationRepository = reservationRepository;
        this.clientService = clientService;
        this.tableService = tableService;
    }

    @GetMapping("/allReservationsPage")
    public String displayAllReservations(Model model) {
        setupModel(model);
        return "allReservationsPage";
    }

    @PostMapping("/addReservationAction")
    public RedirectView addNewReservation(@ModelAttribute("reservationForm") ReservationForm reservationForm, Model model) {
        reservationService.saveReservation(reservationForm);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/allReservationsPage");
        return redirectView;
    }

    @GetMapping("/reservations/{id}")
    public Reservation retrieveReservation(@PathVariable Long id) throws NotFoundException {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (!reservation.isPresent()) {
            throw new NotFoundException("id- " + id);
        }
        return reservation.get();
    }

    @DeleteMapping("/deleteReservationAction/{id}")
    public RedirectView deleteReservation(@PathVariable Long id) {
        reservationService.setTablesToUnoccupied(id);
        reservationService.deleteReservationById(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/allReservationsPage");
        return redirectView;
    }

    private void setupModel(Model model) {
        List<ReservationDTO> allReservations = reservationService.getAllReservations();
        model.addAttribute("allReservations", allReservations);
        model.addAttribute("allClients", clientService.getAllClients());
        model.addAttribute("allTables", tableService.getAllTables());
        model.addAttribute("reservationForm", new ReservationForm());
    }
}
