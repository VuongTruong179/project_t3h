package com.vtt.bt.service;

import com.vtt.bt.common.JWTUtil;
import com.vtt.bt.model.BaseResponse;
import com.vtt.bt.model.OrderDetailRequest;
import com.vtt.bt.model.entity.Menu;
import com.vtt.bt.model.entity.OrderDetail;
import com.vtt.bt.model.entity.Rol;
import com.vtt.bt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OderDetailService {

    @Autowired
    private com.vtt.bt.repository.MenuRepository MenuRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Autowired
    private RolRepository rolRepository;


    public Object createOrderDetail(OrderDetailRequest request) {
        int userId = JWTUtil.getUserLogin();
        List<Rol> rols = rolRepository.getNameRol(userId);
        boolean isNUll;
//
//        for (Rol rol : rols) {
//            if (rol.getRol().equals("Employee") || rol.getRol().equals("Admin")) {

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setIdTable(request.getIdTable());
        orderDetail.setMenuID(request.getMenuId());
        orderDetail.setQuantity(request.getQuantity());
        Menu menu = MenuRepository.takeName(request.getMenuId());
        orderDetail.setTotalPrice(request.getQuantity() * menu.getPrice());

        orderDetail = orderDetailRepository.save(orderDetail);
        return new BaseResponse(BaseResponse.SUCCESS, " Create Oder Detail Success", orderDetail);
//            }
//            BaseResponse res = new BaseResponse();
//            res.setCode(BaseResponse.FAIL);
//            res.setMessage("Fail");
//            isNUll = false;
//
//        }
//        return new BaseResponse(BaseResponse.FAIL, "");
    }

    public Object deleteOrderDetail(int Id) {
        int userId = JWTUtil.getUserLogin();
        List<Rol> rols = rolRepository.getNameRol(userId);
        boolean isNUll;

//        for (Rol rol : rols) {
//            if (rol.getRol().equals("Employee") || rol.getRol().equals("Admin")) {

        orderDetailRepository.deleteOrderById(Id);
        return new BaseResponse(BaseResponse.SUCCESS, "Delete Order Success");

//            }
//            BaseResponse res = new BaseResponse();
//            res.setCode(BaseResponse.FAIL);
//            res.setMessage("No");
//            isNUll = false;
//        }
//        return new BaseResponse(BaseResponse.FAIL, "Fail");
    }

    public Object changeOderDetail(int quantity, int Id) {
        int userId = JWTUtil.getUserLogin();
        List<Rol> rols = rolRepository.getNameRol(userId);
        boolean isNUll;

//        for (Rol rol : rols) {
//            if (rol.getRol().equals("Employee") || rol.getRol().equals("Admin")) {
        orderDetailRepository.changeOderDetail(quantity, Id);
        return new BaseResponse(BaseResponse.SUCCESS, "SUCCESS");

//            }
//            BaseResponse res = new BaseResponse();
//            res.setCode(BaseResponse.FAIL);
//            res.setMessage("Fail");
//            isNUll = false;
//        }
//        return new BaseResponse(BaseResponse.FAIL, "Fail");
    }

    public Object takeOderDetail(int Id, String TimeA, String TimeB) {
        int userId = JWTUtil.getUserLogin();
        List<Rol> rols = rolRepository.getNameRol(userId);
        boolean isNUll;
//
//        for (Rol rol : rols) {
//            if (rol.getRol().equals("Admin")) {
        List<OrderDetail> orderDetails = orderDetailRepository.takeOder(Id, TimeA, TimeB);
        if (orderDetails.size() == 0) {
            return new BaseResponse(BaseResponse.FAIL, "No Oder ");
        } else {
            return orderDetails;
        }
//
//            }
//            BaseResponse res = new BaseResponse();
//            res.setCode(BaseResponse.FAIL);
//            res.setMessage("Fail");
//            isNUll = false;
//        }
//        return new BaseResponse(BaseResponse.FAIL, "Fail");


    }
}
