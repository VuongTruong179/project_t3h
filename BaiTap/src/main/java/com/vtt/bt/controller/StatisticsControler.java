package com.vtt.bt.controller;

import com.vtt.bt.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsControler {

    @Autowired
    private StatisticService service;

    @GetMapping("/api/statistic-food-sel")
    public Object foodSel(@RequestParam("TimeA") String TimeA,
                          @RequestParam("TimeB") String TimeB) {
        return service.foodSel(TimeA, TimeB);
    }

    @GetMapping("/api/sum-order-statistics")
    public Object orderStatistics(@RequestParam("TimeA") String TimeA,
                                  @RequestParam("TimeB") String TimeB) {
        return service.orderStatistics(TimeA, TimeB);
    }
}
