package com.sda.restaurant.restaurant.controllers;

import com.sda.restaurant.restaurant.DTO.TablesDTO;
import com.sda.restaurant.restaurant.services.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class TableController {

    private TableService tableService;

    @Autowired
    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping("/tablesPage")
    public String displayAllTables(Model model){
        setupModel(model);
        return "tablesPage";
    }
    @PostMapping("/updateTableToOccupied/{id}")
    public RedirectView updateTableToOccupied(@PathVariable Long[] id){
        tableService.updateTableToOccupied(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("allReservationsPage");
        return redirectView;
    }

    @PostMapping("/addNewTableAction")
    public RedirectView addNewTable(@ModelAttribute("newTable") TablesDTO tables) {
        tableService.saveTable(tables);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/tablesPage");
        return redirectView;
    }

    @DeleteMapping("/deleteTableAction/{deleteTableId}")
    public RedirectView deleteTable(@PathVariable List<Long> deleteTableId){
       // tableService.updateTableToNotOccupied(deleteTableId);
        tableService.deleteTableById(deleteTableId);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/tablesPage");
        return redirectView;
    }

    private void setupModel(Model model){
        List<TablesDTO> allTables = tableService.getAllTables();
//        List<TablesDTO> allUnoccupiedTables = tableService.getAllUnoccupiedTables();
        model.addAttribute("allTables", allTables);
//        model.addAttribute("allUnoccupiedTables", allUnoccupiedTables);
        model.addAttribute("newTable", new TablesDTO());
        model.addAttribute("deleteTableId", "");
    }
}
