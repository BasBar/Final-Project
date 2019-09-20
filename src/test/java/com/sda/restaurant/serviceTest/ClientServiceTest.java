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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private Client client = new Client();
    private ClientDTO clientDTO = new ClientDTO();

    @Before
    public void setUp(){
        when(modelMapper.map(any(),any())).thenReturn(client);
    }

    @Test
    public void whenSaveClientItShouldReturnItsId(){

        when(clientRepository.save(any(Client.class))).thenReturn(new Client());

        Long created = clientService.saveClient(clientDTO);
        assertThat(created).isEqualTo(client.getId());

    }
}
