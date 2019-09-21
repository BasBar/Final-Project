package com.sda.restaurant.controllers;

import com.sda.restaurant.DTO.ClientDTO;
import com.sda.restaurant.model.Client;
import com.sda.restaurant.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/allClientsPage")
    public String displayAllClients(Model model) {
        setupModel(model);
        return "allClientsPage";
    }

    @PostMapping("/addClientAction")
    public RedirectView addNewClient(@ModelAttribute("newClient") ClientDTO client) {
        clientService.saveClient(client);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/allClientsPage");
        return redirectView;
    }

    @DeleteMapping("/deleteClientAction/{deleteClientId}")
    public RedirectView deleteClient(@PathVariable Long deleteClientId) {
        clientService.deleteClientById(deleteClientId);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/allClientsPage");
        return redirectView;
    }

    private void setupModel(Model model) {
        List<ClientDTO> allClients = clientService.getAllClients();
        model.addAttribute("allClients", allClients);
        model.addAttribute("newClient", new Client());
        model.addAttribute("deleteClientId", "");
    }
}