package com.sda.restaurant.services;

import com.sda.restaurant.DTO.TablesDTO;
import com.sda.restaurant.model.Tables;
import com.sda.restaurant.repositories.TableRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableService {

    private final TableRepository tableRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public TableService(TableRepository tableRepository, ModelMapper modelMapper) {
        this.tableRepository = tableRepository;
        this.modelMapper = modelMapper;
    }

    public Long saveTable(TablesDTO tables) {
        Tables tablesToSave = modelMapper.map(tables, Tables.class);
        tablesToSave.setOccupied(false);
        return tableRepository.save(tablesToSave).getId();
    }

    public List<TablesDTO> getAllTables(){
        return tableRepository.findAll(Sort.by(Sort.Direction.ASC, "size")).stream()
                .map(tables -> modelMapper.map(tables, TablesDTO.class))
                .collect(Collectors.toList());
    }
    public void updateTableToOccupied(Long[] tableId){
        List<Long>tablesToSetOccupied = Arrays.asList(tableId);
        List<Tables> foundTables = tableRepository.findAllById(tablesToSetOccupied);
        foundTables.forEach(p->p.setOccupied(true));
        foundTables.forEach(tableRepository::save);
        foundTables.forEach(p->modelMapper.map(p,TablesDTO.class));

    }
    public TablesDTO updateTableToNotOccupied(Long tableId){
        Tables foundTable = tableRepository.getOne(tableId);
        foundTable.setOccupied(false);
        tableRepository.save(foundTable);
        return modelMapper.map(foundTable,TablesDTO.class);
    }

    /*public List<TablesDTO> getAllUnoccupiedTables(){
        return tableRepository.findAll(Sort.by(Sort.Direction.ASC,"size")).stream()
                .filter(tablesEntity -> !tablesEntity.getOccupied())
                .map(tables -> modelMapper.map(tables, TablesDTO.class))
                .collect(Collectors.toList());
    }*/
    public void deleteTableById(List<Long> id){
        tableRepository.findAllById(id).forEach(tableRepository::delete);
    }

    @PostConstruct
    public void createTablesForPresentation(){
        if (tableRepository.count() < 9) {
            tableRepository.deleteAll();
            tableRepository.save(new Tables(2));
            tableRepository.save(new Tables(2));
            tableRepository.save(new Tables(2));
            tableRepository.save(new Tables(4));
            tableRepository.save(new Tables(4));
            tableRepository.save(new Tables(6));
            tableRepository.save(new Tables(6));
            tableRepository.save(new Tables(6));
            tableRepository.save(new Tables(8));
            tableRepository.save(new Tables(8));
            tableRepository.save(new Tables(10));
            tableRepository.save(new Tables(10));
        }
    }
}
