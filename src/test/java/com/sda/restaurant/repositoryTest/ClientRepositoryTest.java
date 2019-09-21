package com.sda.restaurant.repositoryTest;

import com.sda.restaurant.model.Client;
import com.sda.restaurant.repositories.ClientRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ClientRepositoryTest {

    @Mock
    private ClientRepository clientRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findClientByEmailTest() {

        Client client = new Client(
                "Janusz",
                "Kowalski",
                "jak0007@gmail.com",
                "510599522");

        clientRepository.save(client);
        when(clientRepository.findByEmail("jak0007@gmail.com")).thenReturn(client);
        assertEquals(client, clientRepository.findByEmail("jak0007@gmail.com"));
    }
}
