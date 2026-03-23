package com.tjetc.dao;

import com.tjetc.entity.core.ChargingStation;
import com.tjetc.enums.core.chargingStation.ChargingStationLocation;
import com.tjetc.enums.core.chargingStation.ChargingStationStatus;
import org.apache.ibatis.annotations.Mapper; // 建议加上 @Mapper 注解防止扫描不到
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 充电桩Mapper接口
 */
@Mapper
public interface ChargingStationsMapper {

    /**
     * 查询所有充电桩
     */
    List<ChargingStation> selectAll();

    /**
     * 根据位置枚举查询
     */
    List<ChargingStation> selectByLocation(@Param("location") String location);

    /**
     * 根据状态枚举查询
     */
    List<ChargingStation> selectByStatus(@Param("status") Integer status);

    /**
     * 根据充电桩名称模糊查询
     * @param stationName 名称关键词
     */
    List<ChargingStation> selectLikeName(@Param("stationName") String stationName);

    /**
     * 根据ID查询
     */
    ChargingStation selectById(@Param("stationId") Integer stationId);

    /**
     * 新增
     */
    int insert(ChargingStation chargingStation);

    /**
     * 更新
     */
    int updateById(ChargingStation chargingStation);

    /**
     * 删除
     */
    int deleteById(@Param("stationId") Integer stationId);
}