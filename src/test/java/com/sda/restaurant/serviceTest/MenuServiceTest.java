package com.sda.restaurant.serviceTest;

import com.sda.restaurant.DTO.MenuDTO;
import com.sda.restaurant.model.Menu;
import com.sda.restaurant.repositories.MenuRepository;
import com.sda.restaurant.services.MenuService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceTest {

    @Mock
    MenuRepository menuRepository;

    @InjectMocks
    MenuService menuService;

    @Mock
    ModelMapper modelMapper;

    private Menu menu;
    private MenuDTO menuDTO;

    @Before
    public void setUp() {
        menu = new Menu();
        menuDTO = new MenuDTO();
        menu.setId(5L);

    }

    @Test
    public void whenSaveMenuItShouldReturnItsId() {
        when(modelMapper.map(any(), any())).thenReturn(menu);
        when(menuRepository.save(any(Menu.class))).thenReturn(menu);
        Long created = menuService.saveMenu(menuDTO);
        assertThat(created).isEqualTo(menu.getId());
    }

    @Test
    public void deleteMenuByIdTest(){
        menuService.deleteMenuById(menu.getId());
        verify(menuRepository,times(1)).deleteById(menu.getId());
    }
}
