package com.sda.restaurant.repositoryTest;

import com.sda.restaurant.model.Menu;
import com.sda.restaurant.repositories.MenuRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MenuRepositoryTest {

    @Mock
    private MenuRepository menuRepository;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findMenuByCategoryTest(){

        Menu menu = new Menu(
                1,
                "Drink",
                "Pepsi",
                5.5F);

        menuRepository.save(menu);
        when(menuRepository.findByCategory("Drink")).thenReturn(menu);
        assertEquals(menu,menuRepository.findByCategory("Drink"));
    }
}
