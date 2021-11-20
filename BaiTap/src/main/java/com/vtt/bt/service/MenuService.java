package com.vtt.bt.service;


import com.vtt.bt.common.JWTUtil;
import com.vtt.bt.model.MenuRequest;
import com.vtt.bt.model.BaseResponse;
import com.vtt.bt.model.entity.Menu;
import com.vtt.bt.model.entity.Rol;
import com.vtt.bt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private com.vtt.bt.repository.MenuRepository MenuRepository;


    @Autowired
    private RolRepository rolRepository;



    public Object addMenu(MenuRequest menu) {
        int userId = JWTUtil.getUserLogin();
        List<Rol> rols = rolRepository.getNameRol(userId);
        boolean isNUll;

        for (Rol rol : rols) {
            if (rol.getRol().equals("Admin")) {
                Menu menu1 = new Menu();
                menu1.setCategory(menu.getCategory());
                menu1.setFoodname(menu.getFoodname());
                menu1.setPrice(menu.getPrice());
                menu1.setImage(menu.getImage());
                menu1 = MenuRepository.save(menu1);
                return new BaseResponse(BaseResponse.SUCCESS, "Add Menu Success", menu1);

            }
            BaseResponse res = new BaseResponse();
            res.setCode(BaseResponse.FAIL);
            res.setMessage("No Admin");
            isNUll = false;

        }
        return new BaseResponse(BaseResponse.FAIL, " Login fail");
    }

    public Object deleteMenu(int Id) {
        int userId = JWTUtil.getUserLogin();
        List<Rol> rols = rolRepository.getNameRol(userId);
        boolean isNUll;

        for (Rol rol : rols) {
            if (rol.getRol().equals("Admin")) {
                List<Menu> menus = MenuRepository.menu(Id);
                if (menus.size() == 0) {
                    return new BaseResponse(400, "Does not exist in the list ");
                } else {
                    MenuRepository.deleteById(Id);
                    return new BaseResponse(BaseResponse.SUCCESS, "Delete success");
                }
            }
            BaseResponse res = new BaseResponse();
            res.setCode(BaseResponse.FAIL);
            res.setMessage("No Admin");
            isNUll = false;
        }
        return new BaseResponse(BaseResponse.FAIL, "");
    }


    public Object changeMenu(int Id, long price, String foodname) {
        int userId = JWTUtil.getUserLogin();
        List<Rol> rols = rolRepository.getNameRol(userId);
        boolean isNUll;

        for (Rol rol : rols) {
            if (rol.getRol().equals("Admin")) {
                List<Menu> menus = MenuRepository.menu(Id);
                if (menus.size() == 0) {
                    return new BaseResponse(400, "Does not exist in the list ");
                } else {

                    MenuRepository.changeMenu(Id, foodname, price);
                    return new BaseResponse(BaseResponse.SUCCESS, "Change Menu Success");
                }
            }
            BaseResponse res = new BaseResponse();
            res.setCode(BaseResponse.FAIL);
            res.setMessage("No Admin");
            isNUll = false;
        }
        return new BaseResponse(BaseResponse.FAIL, "");
    }
}
