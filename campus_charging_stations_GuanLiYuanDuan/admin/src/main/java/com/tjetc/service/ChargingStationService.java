package com.tjetc.service;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.core.ChargingStation;

import java.util.List;

/**
 * 充电桩业务层接口
 */
public interface ChargingStationService {

    /**
     * 查找所有充电桩信息
     */
    List<ChargingStation> selectAll();

    /**
     * 根据位置查找充电桩
     * (已修改为String类型，匹配实体类修改)
     */
    List<ChargingStation> selectByLocation(String location);

    /**
     * 根据状态筛选充电桩
     * (已修改为Integer类型，匹配实体类修改)
     */
    List<ChargingStation> selectByStatus(Integer status);

    /**
     * 新增充电桩
     */
    JsonResult add(ChargingStation chargingStation);

    /**
     * 更新充电桩
     */
    JsonResult updateById(ChargingStation chargingStation);

    /**
     * 删除充电桩
     */
    JsonResult deleteById(Integer stationId);

    /**
     * 分页查询（支持名称模糊匹配）
     * @param pageNum 当前页
     * @param pageSize 每页条数
     * @param stationName 模糊查询的名称关键词
     */
    JsonResult findPage(Integer pageNum, Integer pageSize, String stationName);

    /**
     * 根据ID查询充电桩详情
     */
    JsonResult findById(Integer stationId);

    /**
     * 查询所有返回统一格式
     */
    JsonResult findAll();
}