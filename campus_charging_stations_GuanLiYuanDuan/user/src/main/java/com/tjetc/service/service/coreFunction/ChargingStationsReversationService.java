package com.tjetc.service.service.coreFunction;

import com.tjetc.common.JsonResult;
//充电桩预约
public interface ChargingStationsReversationService {

    //预约充电根据id   查询充电桩状态    预约充电    如果现在空闲只能在一小时内预约 现在不空闲在最多可以预约到空闲后的一小时    预约表  充电桩表格

    /**
     * 预约充电桩
     * @param stationId 充电桩ID
     * @param userId 用户ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 预约结果
     */
    JsonResult reserveChargingStation(Integer stationId, String userId, String startTime, String endTime);

    /**
     * 取消预约
     * @param reservationId 预约ID
     * @param userId 用户ID
     * @return 取消结果
     */
    JsonResult cancelReservation(Integer reservationId, String userId);

    /**
     * 根据用户ID查询预约记录
     * @param userId 用户ID
     * @return 预约记录列表
     */
    JsonResult getUserReservations(String userId);

    /**
     * 根据充电桩ID查询预约记录
     * @param stationId 充电桩ID
     * @return 预约记录列表
     */
    JsonResult getStationReservations(Integer stationId);

    /**
     * 查询特定时间段内充电桩的预约情况
     * @param stationId 充电桩ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 预约记录列表
     */
    JsonResult getStationReservationsByTimeRange(Integer stationId, String startTime, String endTime);
}
