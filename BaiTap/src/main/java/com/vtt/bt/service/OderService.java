package com.vtt.bt.service;

import com.vtt.bt.common.JWTUtil;
import com.vtt.bt.model.BaseResponse;
import com.vtt.bt.model.OderRequest;
import com.vtt.bt.model.entity.Oder;
import com.vtt.bt.model.entity.Rol;
import com.vtt.bt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OderService {


    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RolRepository rolRepository;


    public Object createOder(OderRequest request) {
        int userId = JWTUtil.getUserLogin();
        List<Rol> rols = rolRepository.getNameRol(userId);
        boolean isNUll;

//        for (Rol rol : rols) {
//            if (rol.getRol().equals("Admin") || rol.getRol().equals("Employee")) {
        Oder oder = new Oder();
        oder.setIdOfTable(request.getIdOfTable());

        oder = orderRepository.save(oder);
        return new BaseResponse(BaseResponse.SUCCESS, "SUCCESS", oder);
//            }
//            BaseResponse res = new BaseResponse();
//            res.setCode(BaseResponse.FAIL);
//            res.setMessage("Fail");
//            isNUll = false;
//        }
//        return new BaseResponse(BaseResponse.FAIL, "");
    }


    public Object sumOrder(int Id, String TimeA, String TimeB) {
        int userId = JWTUtil.getUserLogin();
        List<Rol> rols = rolRepository.getNameRol(userId);
        boolean isNUll;
//
//        for (Rol rol : rols) {
//            if (rol.getRol().equals("Admin")) {
        orderDetailRepository.getSumOfBill(Id, TimeA, TimeB);
        return new BaseResponse(BaseResponse.SUCCESS, "SUCCESS");
//            }
//            BaseResponse res = new BaseResponse();
//            res.setCode(BaseResponse.FAIL);
//            res.setMessage("Fail");
//            isNUll = false;
//        }
//        return new BaseResponse(BaseResponse.FAIL, "Fail");
    }


    public Object takeSumOrder(int Id,String TimeA,String TimeB) {
        int userId = JWTUtil.getUserLogin();
        List<Rol> rols = rolRepository.getNameRol(userId);
        boolean isNUll;

//        for (Rol rol : rols) {
//            if (rol.getRol().equals("Admin")) {
        Oder oder = orderRepository.getSumAllOrder(Id, TimeA, TimeB);
        if (oder == null) {
            return new BaseResponse(400, "Fail");
        } else {
            return oder;
        }

//            }
//            BaseResponse res = new BaseResponse();
//            res.setCode(BaseResponse.FAIL);
//            res.setMessage("Fail");
//            isNUll = false;
//        }
//        return new BaseResponse(BaseResponse.FAIL, "Fail");

    }




}
