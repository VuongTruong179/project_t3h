package com.vtt.bt.controller;


import com.vtt.bt.model.MenuRequest;
import com.vtt.bt.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MenuControler {
    @Autowired
    private MenuService service;

    @PostMapping("/api/add-menu")
    public Object addMenu(@RequestBody MenuRequest menu) {
        return service.addMenu(menu);
    }


    @DeleteMapping("/api/delete-menu/{Id} ")
    public Object deleteMenu(
            @RequestParam("Id") int Id) {
        return service.deleteMenu(Id);
    }

    @PutMapping("/api/update-menu")
    public Object changeMenu(@RequestParam("id") int id,
                             @RequestParam("price") long price,
                             @RequestParam("foodname") String foodname) {
        return service.changeMenu(id, price, foodname);
    }
}
