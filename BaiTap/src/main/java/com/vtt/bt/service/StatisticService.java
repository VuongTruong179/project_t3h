package com.vtt.bt.service;

import com.vtt.bt.common.JWTUtil;
import com.vtt.bt.model.BaseResponse;
import com.vtt.bt.model.NewDTO.NewStatistic;
import com.vtt.bt.model.StatisticRequest;
import com.vtt.bt.model.entity.Menu;
import com.vtt.bt.model.entity.Oder;
import com.vtt.bt.model.entity.OrderDetail;
import com.vtt.bt.model.entity.Rol;
import com.vtt.bt.model.orderStatisticsService;
import com.vtt.bt.repository.MenuRepository;
import com.vtt.bt.repository.OrderDetailRepository;
import com.vtt.bt.repository.OrderRepository;
import com.vtt.bt.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private MenuRepository menuRepository;

    public Object foodSel(String timeA, String timeB) {
        int userId = JWTUtil.getUserLogin();
        List<Rol> rols = rolRepository.getNameRol(userId);
        boolean isNUll;

        for (Rol rol : rols) {
            if (rol.getRol().equals("Admin")) {
                List<OrderDetail> orderDetails = orderDetailRepository.takeSell(timeA,timeB);
                List<StatisticRequest> list = new ArrayList<>();
                if (orderDetails.size() == 0){
                    return new BaseResponse(400, "Fail");
                }else {
                    for (OrderDetail orderDetail : orderDetails) {
                        Menu menu = menuRepository.takeName(orderDetail.getMenuID());
                        list.add(NewStatistic.stc(orderDetail,menu));
                    }
                    return list;
                }
            }
            BaseResponse res = new BaseResponse();
            res.setCode(BaseResponse.FAIL);
            res.setMessage("Fail");
            isNUll = false;
        }
        return new BaseResponse(BaseResponse.FAIL, "Fail");

    }

    public Object orderStatistics(String timeA, String timeB) {
        int userId = JWTUtil.getUserLogin();
        List<Rol> rols = rolRepository.getNameRol(userId);
        boolean isNUll;

        for (Rol rol : rols) {
            if (rol.getRol().equals("Admin")) {
                Oder Oder = orderRepository.orderStatistics(timeA,timeB);
                orderStatisticsService service = new orderStatisticsService();
                service.setOrderStatistics(Oder.getTotalBill());

                return service;

            }
            BaseResponse res = new BaseResponse();
            res.setCode(BaseResponse.FAIL);
            res.setMessage("Fail");
            isNUll = false;
        }
        return new BaseResponse(BaseResponse.FAIL, "Fail");

    }
}
