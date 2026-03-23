package com.tjetc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjetc.common.JsonResult;
import com.tjetc.dao.ChargingStationsMapper;
import com.tjetc.entity.core.ChargingStation;
import com.tjetc.service.ChargingStationService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 充电桩Service实现类
 */
@Service
@Slf4j
public class ChargingStationServiceImpl implements ChargingStationService {

    @Resource
    private ChargingStationsMapper chargingStationsMapper;

    // 时间格式化工具
    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<ChargingStation> selectAll() {
        return chargingStationsMapper.selectAll();
    }

    @Override
    public List<ChargingStation> selectByLocation(String location) {
        return chargingStationsMapper.selectByLocation(location);
    }

    @Override
    public List<ChargingStation> selectByStatus(Integer status) {
        return chargingStationsMapper.selectByStatus(status);
    }

    // -------------------------- 新增 --------------------------
    @Override
    public JsonResult add(ChargingStation chargingStation) {
        if (chargingStation == null) {
            return JsonResult.fail("参数不能为空");
        }
        try {
            // 自动填充创建时间和更新时间
            String now = LocalDateTime.now().format(DF);
            if (chargingStation.getCreatedTime() == null) {
                chargingStation.setCreatedTime(now);
            }
            if (chargingStation.getUpdatedTime() == null) {
                chargingStation.setUpdatedTime(now);
            }

            int rows = chargingStationsMapper.insert(chargingStation);
            if (rows > 0) {
                return JsonResult.success("新增成功");
            }
            return JsonResult.fail("新增失败");
        } catch (Exception e) {
            log.error("新增充电桩异常", e);
            return JsonResult.fail("新增异常：" + e.getMessage());
        }
    }

    // -------------------------- 更新 --------------------------
    @Override
    public JsonResult updateById(ChargingStation chargingStation) {
        if (chargingStation == null || chargingStation.getStationId() == null) {
            return JsonResult.fail("ID不能为空");
        }
        try {
            // 自动更新修改时间
            chargingStation.setUpdatedTime(LocalDateTime.now().format(DF));

            int rows = chargingStationsMapper.updateById(chargingStation);
            if (rows > 0) {
                return JsonResult.success("更新成功");
            }
            return JsonResult.fail("更新失败，可能ID不存在");
        } catch (Exception e) {
            log.error("更新异常", e);
            return JsonResult.fail("更新异常：" + e.getMessage());
        }
    }

    // -------------------------- 删除 --------------------------
    @Override
    public JsonResult deleteById(Integer stationId) {
        try {
            int rows = chargingStationsMapper.deleteById(stationId);
            if (rows > 0) {
                return JsonResult.success("删除成功");
            }
            return JsonResult.fail("删除失败，数据可能不存在");
        } catch (Exception e) {
            log.error("删除异常", e);
            return JsonResult.fail("删除异常：" + e.getMessage());
        }
    }

    // -------------------------- 分页查询 --------------------------
    @Override
    public JsonResult findPage(Integer pageNum, Integer pageSize, String stationName) {
        try {
            // 1. 开启分页
            PageHelper.startPage(pageNum, pageSize);

            // 2. 查询数据 (Mapper中已配置模糊查询)
            List<ChargingStation> list = chargingStationsMapper.selectLikeName(stationName);

            // 3. 封装分页信息
            PageInfo<ChargingStation> pageInfo = new PageInfo<>(list);

            return JsonResult.success(pageInfo);
        } catch (Exception e) {
            log.error("分页查询异常", e);
            return JsonResult.fail("查询失败");
        }
    }

    // -------------------------- 详情查询 --------------------------
    @Override
    public JsonResult findById(Integer stationId) {
        ChargingStation station = chargingStationsMapper.selectById(stationId);
        if (station != null) {
            return JsonResult.success(station);
        }
        return JsonResult.fail("未找到该充电桩");
    }

    // -------------------------- 查询所有 --------------------------
    @Override
    public JsonResult findAll() {
        try {
            List<ChargingStation> list = selectAll();
            return JsonResult.success(list);
        } catch (Exception e) {
            return JsonResult.fail("查询失败");
        }
    }
}