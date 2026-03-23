package com.tjetc.controller.charge;

import com.tjetc.entity.core.ChargingStation;
import com.tjetc.entity.core.Orders;
import com.tjetc.entity.core.Reservation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/charge-monitor")
public class ChargeMonitorController {

    // 由于没有实现服务，这里仅提供接口定义
    // 实际使用时需要注入相应的服务

    // 获取用户当前充电状态
    @GetMapping("/user/{userId}/current-status")
    public Map<String, Object> getUserCurrentChargeStatus(@PathVariable Integer userId) {
        // 需要实现服务后才能调用
        return null;
    }

    // 获取用户充电历史记录
    @GetMapping("/user/{userId}/history")
    public List<Orders> getUserChargeHistory(@PathVariable Integer userId) {
        // 需要实现服务后才能调用
        return null;
    }

    // 获取用户当前预约信息
    @GetMapping("/user/{userId}/current-reservation")
    public Reservation getCurrentReservation(@PathVariable Integer userId) {
        // 需要实现服务后才能调用
        return null;
    }

    // 获取充电统计数据
    @GetMapping("/user/{userId}/statistics")
    public Map<String, Object> getChargeStatistics(@PathVariable Integer userId) {
        // 需要实现服务后才能调用
        return null;
    }

    // 获取附近充电桩状态（模拟）
    @GetMapping("/stations/nearby")
    public List<ChargingStation> getNearbyStations(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam(defaultValue = "1.0") double radius) {
        // 需要实现服务后才能调用
        return null;
    }
}