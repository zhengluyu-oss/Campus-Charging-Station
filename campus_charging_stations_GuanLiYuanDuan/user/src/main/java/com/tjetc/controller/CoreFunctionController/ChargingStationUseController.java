package com.tjetc.controller.CoreFunctionController;

import com.tjetc.common.JsonResult;
import com.tjetc.service.service.coreFunction.ChargingStationUseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/charging")
@Slf4j
public class ChargingStationUseController {

    @Autowired
    private ChargingStationUseService chargingStationUseService;

    // 开始充电
    @PostMapping("/start")
    public JsonResult start(@RequestParam Integer stationId,
                            @RequestParam Integer userId,
                            @RequestParam double durationMinutes) {
        log.info("用户{}开始使用充电桩{}", userId, stationId);
        return chargingStationUseService.useChargingStation(stationId, userId, durationMinutes);
    }

    // 结束充电
    @PostMapping("/stop")
    public JsonResult stop(@RequestParam Integer orderId) {
        return chargingStationUseService.stopCharging(orderId);
    }
}