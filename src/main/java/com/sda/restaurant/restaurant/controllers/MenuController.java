package com.sda.restaurant.restaurant.controllers;

import com.sda.restaurant.restaurant.DTO.MenuDTO;
import com.sda.restaurant.restaurant.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class MenuController {

    private MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) { this.menuService = menuService; }

    @GetMapping("/allMenusPage")
    public String displayAllMenus(Model model){
        setupModel(model);
        return "allMenusPage";
    }

    @GetMapping("/ristoranteMenu")
    public String displayMenusUnsecured(Model model){
        setupModel(model);
        return "ristoranteMenu";
    }

    @PostMapping("/addMenuAction")
    public RedirectView addNewMenu(@ModelAttribute("newMenu") MenuDTO menu){
        menuService.saveMenu(menu);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/allMenusPage");
        return redirectView;
    }

    private void setupModel(Model model){
        List<MenuDTO> allMenus = menuService.getAllMenus();
        model.addAttribute("allMenus",allMenus);
        model.addAttribute("newMenu",new MenuDTO());
    }

    @DeleteMapping("deleteMenuAction/{deleteMenuId}")
    public RedirectView deleteMenu(@PathVariable Long deleteMenuId){
        menuService.deleteMenuById(deleteMenuId);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/allMenusPage");
        return redirectView;
    }

}
