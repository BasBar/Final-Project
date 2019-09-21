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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceTest {

    @Mock
    MenuRepository menuRepository;

    @InjectMocks
    MenuService menuService;

    @Mock
    ModelMapper modelMapper;

    private Menu menu = new Menu();
    private MenuDTO menuDTO = new MenuDTO();

    @Before
    public void setUp() {
        when(modelMapper.map(any(), any())).thenReturn(menu);
    }

    @Test
    public void whenSaveMenuItShouldReturnItsId() {
        menu.setId(5L);
        when(menuRepository.save(any(Menu.class))).thenReturn(menu);
        Long created = menuService.saveMenu(menuDTO);
        assertThat(created).isEqualTo(menu.getId());
    }
}
