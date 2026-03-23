package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.core.Reservation;
import com.tjetc.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * 预约管理控制器
 * 处理预约相关的HTTP请求
 */
@RestController
@RequestMapping("/reservation")
@Slf4j
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    /**
     * 查询所有预约记录
     * @return JsonResult - 包含所有预约记录列表或错误信息
     */
    @GetMapping("/all")
    public JsonResult<Reservation> findAll() {
        log.info("接收查询所有预约记录请求");
        return reservationService.findAll();
    }

    /**
     * 新增预约记录（包含重复预约校验）
     * @param reservation 预约实体（JSON格式）
     * @return JsonResult - 新增结果提示
     */
    @PostMapping("/add")
    public JsonResult add(@RequestBody Reservation reservation) {
        log.info("接收新增预约请求，用户ID：{}，充电桩ID：{}",
                reservation.getUserId(), reservation.getStationId());
        return reservationService.add(reservation);
    }

    /**
     * 按预约状态分页查询
     * @param pageNum 页码（必填，>0）
     * @param pageSize 每页数量（必填，>0）
     * @param status 预约状态（confirmed/used/cancelled）
     * @return JsonResult - 分页预约记录列表或错误信息
     */
    @GetMapping("/page/status")
    public JsonResult findPageByStatus(
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize,
            @RequestParam String status
    ) {
        log.info("接收按状态分页查询预约请求，状态：{}，页码：{}，每页数量：{}", status, pageNum, pageSize);
        return reservationService.findPageByStatus(pageNum, pageSize, status);
    }

    /**
     * 按用户ID分页查询预约记录
     * @param pageNum 页码（必填，>0）
     * @param pageSize 每页数量（必填，>0）
     * @param userId 用户ID（必填，>0）
     * @return JsonResult - 分页预约记录列表或错误信息
     */
    @GetMapping("/page/user")
    public JsonResult findPageByUserId(
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize,
            @RequestParam Integer userId
    ) {
        log.info("接收按用户ID分页查询预约请求，用户ID：{}，页码：{}，每页数量：{}", userId, pageNum, pageSize);
        return reservationService.findPageByUserId(pageNum, pageSize, userId);
    }

    /**
     * 按充电桩ID分页查询预约记录
     * @param pageNum 页码（必填，>0）
     * @param pageSize 每页数量（必填，>0）
     * @param stationId 充电桩ID（必填，>0）
     * @return JsonResult - 分页预约记录列表或错误信息
     */
    @GetMapping("/page/station")
    public JsonResult findPageByStationId(
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize,
            @RequestParam Integer stationId
    ) {
        log.info("接收按充电桩ID分页查询预约请求，充电桩ID：{}，页码：{}，每页数量：{}", stationId, pageNum, pageSize);
        return reservationService.findPageByStationId(pageNum, pageSize, stationId);
    }

    /**
     * 按充电桩ID+时间范围查询重叠预约（防重复预约校验）
     * @param stationId 充电桩ID（必填，>0）
     * @param startTime 预约开始时间（格式：yyyy-MM-dd HH:mm:ss）
     * @param endTime 预约结束时间（格式：yyyy-MM-dd HH:mm:ss）
     * @return JsonResult - 重叠预约记录列表或错误信息
     */
    @GetMapping("/time-range")
    public JsonResult findByTimeRangeAndStationId(
            @RequestParam Integer stationId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime
    ) {
        log.info("接收按时间范围查询预约请求，充电桩ID：{}，开始时间：{}，结束时间：{}", stationId, startTime, endTime);
        return reservationService.findByTimeRangeAndStationId(stationId, startTime, endTime);
    }

    /**
     * 根据预约ID查询详情
     * @param id 预约ID（必填，>0）
     * @return JsonResult - 预约详情或错误信息
     */
    @GetMapping("/{id}")
    public JsonResult findById(@PathVariable Integer id) {
        log.info("接收按ID查询预约请求，预约ID：{}", id);
        return reservationService.findById(id);
    }

    /**
     * 更新预约状态
     * @param id 预约ID（必填，>0）
     * @param status 新状态（confirmed/used/cancelled）
     * @return JsonResult - 更新结果提示
     */
    @PutMapping("/status/{id}")
    public JsonResult updateStatusById(
            @PathVariable Integer id,
            @RequestParam String status
    ) {
        log.info("接收更新预约状态请求，预约ID：{}，新状态：{}", id, status);
        return reservationService.updateStatusById(id, status);
    }

    /**
     * 根据预约ID删除预约记录
     * @param id 预约ID（必填，>0）
     * @return JsonResult - 删除结果提示
     */
    @DeleteMapping("/{id}")
    public JsonResult deleteById(@PathVariable Integer id) {
        log.info("接收删除预约请求，预约ID：{}", id);
        return reservationService.deleteById(id);
    }
}