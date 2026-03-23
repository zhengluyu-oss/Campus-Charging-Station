package com.tjetc.service.service.coreFunction;

import com.tjetc.common.JsonResult;
import com.tjetc.enums.core.chargingStation.ChargingStationLocation;
import com.tjetc.enums.core.chargingStation.ChargingStationStatus;
//充电桩查询
public interface ChargingStationSelectService {


    //    核心功能
    /*
    查询所有的充电桩的信息
     */
    JsonResult showChargingStations();




    //    根据位置查找充电桩信息
    JsonResult showChargingStationbyLocation(ChargingStationLocation location);

    //    查询指定状态的充电桩
    JsonResult showChargingStationbyStatus(ChargingStationStatus status);


}
