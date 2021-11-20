package com.vtt.bt.controller;

import com.vtt.bt.model.OderRequest;
import com.vtt.bt.service.OderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OderControler {

    @Autowired
    private OderService service;

    @PostMapping("/api/create-oder")
    public Object createOder(@RequestBody OderRequest request) {
        return service.createOder(request);
    }

    @PutMapping("/api/sum-all-oder")
    public Object sumOrder(@RequestParam("Id") int Id,
                           @RequestParam("TimeA") String TimeA,
                           @RequestParam("TimeB") String TimeB) {
        return service.sumOrder(Id, TimeA, TimeB);
    }

    @GetMapping("/api/get-sum-all-oder/{Id}")
    public Object takeSumOrder(@PathVariable("Id") int Id,
                               @RequestParam("TimeA") String TimeA,
                               @RequestParam("TimeB") String TimeB) {
        return service.takeSumOrder(Id,TimeA,TimeB);

    }
}
