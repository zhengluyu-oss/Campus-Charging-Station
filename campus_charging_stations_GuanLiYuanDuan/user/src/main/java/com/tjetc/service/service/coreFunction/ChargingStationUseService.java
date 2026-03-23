package com.tjetc.service.service.coreFunction;

import com.tjetc.common.JsonResult;

public interface ChargingStationUseService {
    /**
     * 开始充电
     */
    JsonResult useChargingStation(Integer stationId, Integer userId, double durationMinutes);

    /**
     * 结束充电
     */
    JsonResult stopCharging(Integer orderId);
}