package com.vtt.bt.controller;

import com.vtt.bt.model.TableRequest;
import com.vtt.bt.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TableControler {

    @Autowired
    private TableService service;

    @GetMapping("/api/get-all-table")
    public Object getAllTable(){
        return service.getAllTable();
    }

    @PostMapping("/api/add-table")
    public Object addTable(@RequestBody TableRequest TB){
        return service.addTable(TB);
    }


    @PutMapping("/api/change-table")
    public Object changeTbale(@RequestParam("nameFood") String nameFood,
                              @RequestParam("Id") int Id){
        return service.changeTbale(nameFood,Id);
    }

    @DeleteMapping("/api/delete-table/{Id}")
    public Object deleteTableById(@PathVariable("Id")int Id){
        return service.deleteTableById(Id);
    }

    @GetMapping("/api/get-table-empty")
    public Object getTableEmpty(){
        return service.getTableEmpty();
    }

    @PutMapping("/api/change-status-table-empty/{Id}")
    public Object changeTableEmpty(@PathVariable("Id")int Id){
        return service.changeTableEmpty(Id);
    }

    @PutMapping("/api/change-status-table-Active/{Id}")
    public Object changeTableActive(@PathVariable("Id")int Id){
        return service.changeTableActive(Id);
    }
}
