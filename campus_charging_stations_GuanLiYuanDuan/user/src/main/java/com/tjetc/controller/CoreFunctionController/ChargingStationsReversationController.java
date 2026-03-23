package com.tjetc.controller.CoreFunctionController;

import com.tjetc.common.JsonResult;
import com.tjetc.service.service.coreFunction.ChargingStationsReversationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("reversation")
@Slf4j
public class ChargingStationsReversationController {
    @Autowired
    private ChargingStationsReversationService reversationService;

//    预约充电桩
    @RequestMapping("/reserve")
    public JsonResult reserveChargingStation(@RequestParam("stationId") Integer stationId,@RequestParam("userId") String userId,@RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime){
        return reversationService.reserveChargingStation(stationId,userId,startTime,endTime);
    }

//    取消预约
    @RequestMapping("/cancel")
    public JsonResult cancelReservation(@RequestParam("reservationId") Integer reservationId,@RequestParam("userId") String userId){
        return reversationService.cancelReservation(reservationId,userId);
    }

//    获取用户预约记录
    @RequestMapping("/getUserReservations")
    public JsonResult getUserReservations(@RequestParam("userId") String userId){
        return reversationService.getUserReservations(userId);
    }


//    获取充电桩的预约情况
    @RequestMapping("/getStationReservations")
    public JsonResult getStationReservations(@RequestParam("stationId") Integer stationId){
        return reversationService.getStationReservations(stationId);
    }
}
