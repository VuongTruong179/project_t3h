package com.vtt.bt.controller;

import com.vtt.bt.model.OrderDetailRequest;
import com.vtt.bt.service.OderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OderDetailControler {

    @Autowired
    private OderDetailService service;

    @PostMapping("/api/order-detail")
    public Object createOrderDetail(@RequestBody OrderDetailRequest request) {
        return service.createOrderDetail(request);
    }

    @DeleteMapping("/api/{Id}")
    public Object deleteOrderDetail(@PathVariable("Id") int Id) {
        return service.deleteOrderDetail(Id);
    }


    @PutMapping("/api/change-oder")
    public Object changeOderDetail(@RequestParam("quantity") int quantity,
                                   @RequestParam("Id") int Id) {
        return service.changeOderDetail(quantity, Id);
    }

    @GetMapping("/api/oder-detail")
    public Object takeOderDetail(@RequestParam("Id") int Id,
                                    @RequestParam("TimeA") String TimeA,
                                    @RequestParam("TimeB") String TimeB) {
        return service.takeOderDetail(Id, TimeA, TimeB);
    }

}
