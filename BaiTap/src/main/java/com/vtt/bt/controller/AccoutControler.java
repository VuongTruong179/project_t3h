package com.vtt.bt.controller;

import com.vtt.bt.model.BaseResponse;
import com.vtt.bt.model.LoginRequest;
import com.vtt.bt.model.NewPassword;
import com.vtt.bt.model.RegisterRequest;
import com.vtt.bt.service.AccoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccoutControler {

    @Autowired
    private AccoutService service;

    @PostMapping("/register")
    public Object register(@RequestBody RegisterRequest request) {
        return service.register(request);
    }

    @PostMapping("/login")
    public BaseResponse login(
            @RequestBody LoginRequest request) {
        return service.login(request);
    }


    @PutMapping("/api/userId")
    public Object changePassword(
            @RequestBody NewPassword request

    ) {
        return service.changePassword(request);
    }

    @GetMapping("/api/get-all-accout")
    public Object getAllAccout() {
        return service.getAllAccout();
    }

    @DeleteMapping("/api/delete-accout/{Id}")
    public Object deleteAccout(@PathVariable("Id") int Id) {
        return service.deleteAccout(Id);
    }
}
