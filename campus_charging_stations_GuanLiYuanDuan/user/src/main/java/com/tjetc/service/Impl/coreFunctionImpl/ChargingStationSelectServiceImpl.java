package com.tjetc.service.Impl.coreFunctionImpl;

import com.tjetc.common.JsonResult;
import com.tjetc.dao.ChargingStationsMapper;
import com.tjetc.entity.core.ChargingStation;
import com.tjetc.enums.core.chargingStation.ChargingStationLocation;
import com.tjetc.enums.core.chargingStation.ChargingStationStatus;
import com.tjetc.service.service.coreFunction.ChargingStationSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargingStationSelectServiceImpl implements ChargingStationSelectService {
    //    充电桩表格的映射
    @Autowired
    private ChargingStationsMapper chargingStationsMapper;

    /*
    查找充电桩 信息
     */

    //    所有充电桩信息
    @Override
    public JsonResult showChargingStations() {
        List<ChargingStation> chargingStationsListAll = chargingStationsMapper.selectAll();
        return JsonResult.success(chargingStationsListAll);
    }

    //    根据位置查找充电桩信息
    @Override
    public JsonResult showChargingStationbyLocation(ChargingStationLocation location) {
        List<ChargingStation> chargingStationsByLocation = chargingStationsMapper.selectByLocation(location);
        return JsonResult.success(chargingStationsByLocation);
    }

    //    根据状态查找充电桩的信息
    @Override
    public JsonResult showChargingStationbyStatus(ChargingStationStatus status) {
        List<ChargingStation> chargingStationsByStatus = chargingStationsMapper.selectByStatus(status);
        return JsonResult.success(chargingStationsByStatus);
    }

}
