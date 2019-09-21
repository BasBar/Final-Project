package com.sda.restaurant.services;

import com.sda.restaurant.DTO.ClientDTO;
import com.sda.restaurant.model.Client;
import com.sda.restaurant.repositories.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    public Long saveClient(ClientDTO clientDTO) {
        Client clientEntity = modelMapper.map(clientDTO, Client.class);
        return clientRepository.save(clientEntity).getId();
    }

    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }

    public Client findByEmail(String email) {
        Client clientToFind = clientRepository.findByEmail(email);
        return modelMapper.map(clientToFind, Client.class);
    }

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll(Sort.by(Sort.Direction.ASC, "email")).stream()
                .map(client -> modelMapper.map(client, ClientDTO.class))
                .collect(Collectors.toList());
    }

    @PostConstruct
    public void createClientsForPresentation() {
        if (clientRepository.count() < 8) {
            clientRepository.deleteAll();
            clientRepository.save(new Client("John", "Wik", "johnyjohn@gmail.com", "518592019"));
            clientRepository.save(new Client("Franklin", "Brown", "f.brooown2@gmail.com", "552674658"));
            clientRepository.save(new Client("Benjamin", "Scott", "scott.benj@gmail.com", "715325424"));
            clientRepository.save(new Client("Emma", "Morgan", "emm5to@outlook.com", "591783040"));
            clientRepository.save(new Client("Evelyn", "Patterson", "eve.p0938n@hotmail.com", "500261164"));
            clientRepository.save(new Client("Amelia", "Hughes", "amelia.mail12002@icloud.com", "656834286"));
            clientRepository.save(new Client("William", "Richardson", "will.i.a.m.R@gmail.com", "799449706"));
            clientRepository.save(new Client("Logan", "Foster", "logan.foster@gmail.com", "633974471"));
        }
    }
}
