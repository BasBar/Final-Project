package com.sda.restaurant.services;

import com.sda.restaurant.DTO.MenuDTO;
import com.sda.restaurant.model.Menu;
import com.sda.restaurant.repositories.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MenuService(MenuRepository menuRepository, ModelMapper modelMapper) {
        this.menuRepository = menuRepository;
        this.modelMapper = modelMapper;
    }

    public Long saveMenu(MenuDTO menu){
        Menu menu1 = modelMapper.map(menu, Menu.class);
        return menuRepository.save(menu1).getId();
    }

    public List<MenuDTO> getAllMenus(){
        return menuRepository.findAll(Sort.by(Sort.Direction.ASC,"number")).stream()
                .map(menu -> modelMapper.map(menu, MenuDTO.class))
                .collect(Collectors.toList());
    }

    public MenuDTO findByCategory (String category){
        Menu menuToFind = menuRepository.findByCategory(category);
        return modelMapper.map(menuToFind, MenuDTO.class);
    }

    public void deleteMenuById(Long id){
        menuRepository.deleteById(id);
    }


    @PostConstruct
    public void createMenuForPresentation() {
        if (menuRepository.count() < 30) {
            menuRepository.deleteAll();
            menuRepository.save(new Menu(1, "Drink", "Espresso", 11.50F));
            menuRepository.save(new Menu(2, "Drink", "Espresso Lungo", 14.50F));
            menuRepository.save(new Menu(3, "Drink", "Espresso Con Panna", 17.0F));
            menuRepository.save(new Menu(4, "Drink", "Caffe Americano", 18.50F));
            menuRepository.save(new Menu(5, "Drink", "Caffe Mocha", 19F));
            menuRepository.save(new Menu(6, "Drink", "Cafe Latte", 21.50F));
            menuRepository.save(new Menu(7, "Drink", "Cappuccino", 21.50F));
            menuRepository.save(new Menu(8, "Drink", "Paper Plane", 29F));
            menuRepository.save(new Menu(9, "Drink", "Rocking Horse", 35F));
            menuRepository.save(new Menu(10, "Drink", "Havana Club", 38F));
            menuRepository.save(new Menu(11, "Drink", "The Ivy Spiced Rum", 46.50F));
            menuRepository.save(new Menu(12, "Dinner", "Tomato Soup", 18.50F));
            menuRepository.save(new Menu(13, "Dinner", "Mushroom Soup", 18.50F));
            menuRepository.save(new Menu(14, "Dinner", "Dressed Cornish Crab", 92.00F));
            menuRepository.save(new Menu(15, "Dinner", "Oscietra Caviar 30g", 455.00F));
            menuRepository.save(new Menu(16, "Dinner", "Crispy Duck & Mango salad", 76.00F));
            menuRepository.save(new Menu(17, "Dinner", "The Ristorante Burger", 98.00F));
            menuRepository.save(new Menu(18, "Dinner", "Palermo Special Burger", 98.00F));
            menuRepository.save(new Menu(19, "Dinner", "The Ristorante Pizza 40cm", 82.00F));
            menuRepository.save(new Menu(20, "Dessert", "Chocolate Pyramid", 35.50F));
            menuRepository.save(new Menu(21, "Dessert", "Ristorante Cheesecake", 42.00F));
            menuRepository.save(new Menu(22, "Dessert", "Polish Strawberries", 32.50F));
            menuRepository.save(new Menu(23, "Vegetarian", "Beetroot Salad", 28.50F));
            menuRepository.save(new Menu(24, "Vegetarian", "The Ristorante Salad", 28.50F));
            menuRepository.save(new Menu(25, "Vegetarian", "Chickpeas Salad", 28.50F));
            menuRepository.save(new Menu(26, "Wine", "Burgundy", 379.0F));
            menuRepository.save(new Menu(27, "Wine", "Stellenbosch", 239.0F));
            menuRepository.save(new Menu(28, "Wine", "Castilla & Leon", 209.0F));
            menuRepository.save(new Menu(29, "Wine", "Castilla & Leon", 209.0F));
            menuRepository.save(new Menu(30, "Wine", "Piedmont", 750.0F));
            menuRepository.save(new Menu(31, "Wine", "Blanc de Blancs", 510.0F));
            menuRepository.save(new Menu(32, "Wine", "Le Mesnil Salon", 3700.0F));
        }
    }
}
