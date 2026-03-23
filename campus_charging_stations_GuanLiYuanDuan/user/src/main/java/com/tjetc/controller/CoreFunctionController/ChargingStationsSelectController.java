package com.tjetc.controller.CoreFunctionController;

import com.tjetc.common.JsonResult;
import com.tjetc.enums.core.chargingStation.ChargingStationLocation;
import com.tjetc.enums.core.chargingStation.ChargingStationStatus;
import com.tjetc.service.service.coreFunction.ChargingStationSelectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ChargingStationsSelectController {


    /*
    查找充电桩
     */

    //    查找所有的充电桩
    @Autowired
    private ChargingStationSelectService chargingStationSelectService;

    @RequestMapping("/showAll")
    public JsonResult showChargingStations() {
        return chargingStationSelectService.showChargingStations();
    }

    //根据充电桩的位置查找所有的充电桩
    @RequestMapping("/showByLocation")
    public JsonResult showChargingStationsByLocation(@RequestParam("location") ChargingStationLocation location) {
        return chargingStationSelectService.showChargingStationbyLocation(location);
    }

    //根据装药发查找充电桩
    @RequestMapping("/showByStatus")
    public JsonResult showChargingStationsByStatus(@RequestParam("status")
                                                   ChargingStationStatus status) {
        return chargingStationSelectService.showChargingStationbyStatus(status);
    }


}
