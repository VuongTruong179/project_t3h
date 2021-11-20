package com.vtt.bt.model.NewDTO;

import com.vtt.bt.model.StatisticRequest;
import com.vtt.bt.model.entity.Menu;
import com.vtt.bt.model.entity.OrderDetail;

public class NewStatistic {

    public static StatisticRequest stc(OrderDetail orderDetail, Menu menu){
        StatisticRequest request = new StatisticRequest();
        request.setFoodName(menu.getFoodname());
        request.setQuantity(orderDetail.getQuantity());
        request.setImge(menu.getImage());
        request.setTotal(orderDetail.getTotalPrice());

        return request;
    }
}
