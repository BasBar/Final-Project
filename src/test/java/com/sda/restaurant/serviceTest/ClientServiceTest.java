package com.sda.restaurant.serviceTest;

import com.sda.restaurant.DTO.ClientDTO;
import com.sda.restaurant.model.Client;
import com.sda.restaurant.repositories.ClientRepository;
import com.sda.restaurant.services.ClientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private Client client;
    private ClientDTO clientDTO;
    private List<ClientDTO> clientDTOList;
    private List<Client> clientList;

    @Before
    public void setUp() {
        client = new Client();
        clientDTO = new ClientDTO();
        clientDTO.setId(3L);
        client.setId(5L);
        clientDTOList = new ArrayList<>();
        clientDTOList.add(clientDTO);
        clientList = new ArrayList<>();
        clientList.add(client);
    }

    @Test
    public void whenSaveClientItShouldReturnItsId() {
        when(modelMapper.map(any(), any())).thenReturn(client);
        when(clientRepository.save(any(Client.class))).thenReturn(client);
        Long created = clientService.saveClient(clientDTO);
        assertThat(created).isEqualTo(client.getId());
    }

    @Test
    public void deleteClientByIdTest(){
        clientService.deleteClientById(client.getId());
        verify(clientRepository,times(1)).deleteById(client.getId());
    }

    @Test
    public void getAllClientsTest(){
       when(modelMapper.map(any(),any())).thenReturn(clientDTO);
       when(clientRepository.findAll(Sort.by(Sort.Direction.ASC, "email"))).thenReturn(clientList);
       List<ClientDTO> result =  clientService.getAllClients();
       assertThat(result).isEqualTo(clientDTOList);
    }
}
