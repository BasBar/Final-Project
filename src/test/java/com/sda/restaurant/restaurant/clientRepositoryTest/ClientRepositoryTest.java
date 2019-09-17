package com.sda.restaurant.restaurant.clientRepositoryTest;

import com.sda.restaurant.controllers.ClientController;
import com.sda.restaurant.model.ClientEntity;
import com.sda.restaurant.repositories.ClientRepository;
import com.sda.restaurant.services.ClientService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ClientRepositoryTest {

    @Mock
    private ClientRepository repositoryMock;

    @Autowired
    private ClientService clientService;

    @MockBean
    ClientController clientController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findClientByEmailTest() {

        ClientEntity client = new ClientEntity(
                "Janusz",
                "Kowalski",
                "jak0007@gmail.com",
                "510599522");

        when(repositoryMock.findByEmail("jak0007@gmail.com")).thenReturn(client);
        assertEquals(client, repositoryMock.findByEmail("jak0007@gmail.com"));

    }

}
