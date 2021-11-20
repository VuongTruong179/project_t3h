package com.vtt.bt.service;

import com.vtt.bt.common.JWTUtil;
import com.vtt.bt.model.BaseResponse;
import com.vtt.bt.model.TableRequest;
import com.vtt.bt.model.entity.Menu;
import com.vtt.bt.model.entity.Rol;
import com.vtt.bt.model.entity.Table;
import com.vtt.bt.repository.RolRepository;
import com.vtt.bt.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private com.vtt.bt.repository.MenuRepository MenuRepository;


    @Autowired
    private RolRepository rolRepository;

    public Object getAllTable() {
        int userId = JWTUtil.getUserLogin();
        List<Table> list = tableRepository.getAllTable();
        return list;
    }

    public Object addTable(TableRequest TB) {
        int userId = JWTUtil.getUserLogin();
        List<Rol> rols = rolRepository.getNameRol(userId);
        boolean isNUll;

        for (Rol rol : rols) {
            if (rol.getRol().equals("Admin")) {

        Table table = new Table();
        table.setTable(TB.getTable());
        table.setStatus("Trống");
        table = tableRepository.save(table);
        return new BaseResponse(BaseResponse.SUCCESS, "Add Table Success", table);

    }
            BaseResponse res = new BaseResponse();
            res.setCode(BaseResponse.FAIL);
            res.setMessage("No Admin");
            isNUll = false;

        }
        return new BaseResponse(BaseResponse.FAIL, "fail");

}

    public Object getTableEmpty() {
        int userId = JWTUtil.getUserLogin();
        List<Table> list = tableRepository.getTableEmpty();
        if (list.size() == 0) {
            return "Full Table";
        } else {
            return list;
        }
    }

    public Object changeTbale(String nameFood, int Id) {
        int userId = JWTUtil.getUserLogin();
        List<Rol> rols = rolRepository.getNameRol(userId);
        boolean isNUll;

        for (Rol rol : rols) {
            if (rol.getRol().equals("Admin")) {
                List<Table> list = tableRepository.getTableById(Id);
                if (list.size() == 0){
                    return "No Table";
                }else {
                    tableRepository.changeTable(nameFood,Id);
                    return new BaseResponse(BaseResponse.SUCCESS, "Change Table Success");
                }
            }
            BaseResponse res = new BaseResponse();
            res.setCode(BaseResponse.FAIL);
            res.setMessage("No Admin");
            isNUll = false;
        }
        return new BaseResponse(BaseResponse.FAIL, "fail");
    }

    public Object deleteTableById(int Id) {
        int userId = JWTUtil.getUserLogin();
        List<Rol> rols = rolRepository.getNameRol(userId);
        boolean isNUll;

        for (Rol rol : rols) {
            if (rol.getRol().equals("Admin")) {
                List<Table> list = tableRepository.getTableById(Id);
                if (list.size() == 0){
                    return "No Table";
                }else {
                    tableRepository.deleteTableById(Id);
                    return new BaseResponse(BaseResponse.SUCCESS, "Delete Table Success");
                }
            }
            BaseResponse res = new BaseResponse();
            res.setCode(BaseResponse.FAIL);
            res.setMessage("No Admin");
            isNUll = false;
        }
        return new BaseResponse(BaseResponse.FAIL, "fail");
    }

    public Object changeTableEmpty(int Id) {
        int userId = JWTUtil.getUserLogin();
        Table table = tableRepository.TableEmpty(Id);
        if (table == null){
            return "Bàn đang trống sẵn r nhé bro";
        }else {
            tableRepository.changeTableEmpty(Id);
            return new BaseResponse(BaseResponse.SUCCESS, "SUCCESS");
        }

    }

    public Object changeTableActive(int Id) {
        int userId = JWTUtil.getUserLogin();
        Table table = tableRepository.TableActive(Id);
        if (table == null){
            return "Bàn đang có người";
        }else {
            tableRepository.changeTableActive(Id);
            return new BaseResponse(BaseResponse.SUCCESS, "SUCCESS");
        }

    }
}
