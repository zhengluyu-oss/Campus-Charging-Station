package com.tjetc.service.Impl.coreFunctionImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.common.JsonResult;
import com.tjetc.dao.ChargingStationsMapper;
import com.tjetc.dao.ReservationMapper;
import com.tjetc.entity.core.ChargingStation;
import com.tjetc.entity.core.Reservation;
import com.tjetc.enums.core.chargingStation.ChargingStationStatus;
import com.tjetc.service.service.coreFunction.ChargingStationsReversationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ChargingStationsReversationServiceImpl implements ChargingStationsReversationService {

    private static final Logger logger = LoggerFactory.getLogger(ChargingStationsReversationServiceImpl.class);

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private ChargingStationsMapper chargingStationsMapper;

//    预约充电桩
    @Override
    public JsonResult reserveChargingStation(Integer stationId, String userId, String startTime, String endTime) {
        try {
            // 参数验证
            if (stationId == null || userId == null || startTime == null || endTime == null) {
                return JsonResult.fail("参数不能为空");
            }

            // 解析时间字符串
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime startDateTime;
            LocalDateTime endDateTime;

            try {
                startDateTime = LocalDateTime.parse(startTime, formatter);
                endDateTime = LocalDateTime.parse(endTime, formatter);
            } catch (Exception e) {
                return JsonResult.fail("时间格式错误，请使用 yyyy-MM-dd HH:mm:ss 格式");
            }

            // 检查时间有效性
            if (startDateTime.isAfter(endDateTime)) {
                return JsonResult.fail("开始时间不能晚于结束时间");
            }

            if (startDateTime.isBefore(LocalDateTime.now())) {
                return JsonResult.fail("开始时间不能早于当前时间");
            }

            // 检查充电桩是否存在
            ChargingStation chargingStation = chargingStationsMapper.selectById(stationId);
            if (chargingStation == null) {
                return JsonResult.fail("充电桩不存在");
            }

            // 检查充电桩是否可用
            if (chargingStation.getStatus() != ChargingStationStatus.available) {
                return JsonResult.fail("该充电桩当前不可用，无法预约");
            }

            // 检查时间段是否有冲突
            List<Reservation> conflictingReservations = reservationMapper.selectByTimeRangeAndStationId(
                    stationId, startDateTime, endDateTime);

            if (!conflictingReservations.isEmpty()) {
                return JsonResult.fail("该时间段已被预约，请选择其他时间");
            }

            // 转换用户ID为整数
            Integer userIdInt;
            try {
                userIdInt = Integer.parseInt(userId);
            } catch (NumberFormatException e) {
                return JsonResult.fail("用户ID格式错误");
            }

            // 创建预约记录
            Reservation reservation = new Reservation();
            reservation.setUserId(userIdInt);
            reservation.setStationId(stationId);
            reservation.setReservedStartTime(startDateTime);
            reservation.setReservedEndTime(endDateTime);
            reservation.setStatus("confirmed"); // 设置为已确认状态
            reservation.setCreatedAt(LocalDateTime.now());

            // 插入预约记录
            int result = reservationMapper.insert(reservation);

            if (result > 0) {
                // 预约成功，返回预约信息
                return JsonResult.success(reservation);
            } else {
                return JsonResult.fail("预约失败，请稍后重试");
            }
        } catch (Exception e) {
            logger.error("预约充电桩时发生异常", e);
            return JsonResult.fail("系统异常：" + e.getMessage());
        }
    }


//    取消预约

    @Override
    public JsonResult cancelReservation(Integer reservationId, String userId) {
        try {
            // 参数验证
            if (reservationId == null || userId == null) {
                return JsonResult.fail("参数不能为空");
            }

            // 验证用户ID格式
            Integer userIdInt;
            try {
                userIdInt = Integer.parseInt(userId);
            } catch (NumberFormatException e) {
                return JsonResult.fail("用户ID格式错误");
            }

            // 查询预约记录
            Reservation reservation = reservationMapper.selectById(reservationId);
            if (reservation == null) {
                return JsonResult.fail("预约记录不存在");
            }

            // 验证是否是当前用户的预约
            if (!reservation.getUserId().equals(userIdInt)) {
                return JsonResult.fail("您没有权限取消此预约");
            }

            // 检查预约状态
            if ("cancelled".equals(reservation.getStatus())) {
                return JsonResult.fail("该预约已取消");
            }

            if ("used".equals(reservation.getStatus())) {
                return JsonResult.fail("该预约已使用，无法取消");
            }

            // 更新预约状态为已取消
            int result = reservationMapper.updateStatusById(reservationId, "cancelled");

            if (result > 0) {
                return JsonResult.success("预约取消成功");
            } else {
                return JsonResult.fail("预约取消失败，请稍后重试");
            }
        } catch (Exception e) {
            logger.error("取消预约时发生异常", e);
            return JsonResult.fail("系统异常：" + e.getMessage());
        }
    }


//   获取预约情况
    @Override
    public JsonResult getUserReservations(String userId) {
        try {
            // 参数验证
            if (userId == null) {
                return JsonResult.fail("用户ID不能为空");
            }

            // 验证用户ID格式
            Integer userIdInt;
            try {
                userIdInt = Integer.parseInt(userId);
            } catch (NumberFormatException e) {
                return JsonResult.fail("用户ID格式错误");
            }

            // 分页查询用户预约记录
            Page<Reservation> page = new Page<>(1, 20); // 默认第一页，每页20条记录
            IPage<Reservation> reservationPage = reservationMapper.selectPageByUserId(page, userIdInt);

            return JsonResult.success(reservationPage.getRecords());
        } catch (Exception e) {
            logger.error("查询用户预约记录时发生异常", e);
            return JsonResult.fail("系统异常：" + e.getMessage());
        }
    }


//    获得充电桩预约情况

    @Override
    public JsonResult getStationReservations(Integer stationId) {
        try {
            // 参数验证
            if (stationId == null) {
                return JsonResult.fail("充电桩ID不能为空");
            }

            // 检查充电桩是否存在
            ChargingStation chargingStation = chargingStationsMapper.selectById(stationId);
            if (chargingStation == null) {
                return JsonResult.fail("充电桩不存在");
            }

            // 分页查询充电桩预约记录
            Page<Reservation> page = new Page<>(1, 20); // 默认第一页，每页20条记录
            IPage<Reservation> reservationPage = reservationMapper.selectPageByStationId(page, stationId);

            return JsonResult.success(reservationPage.getRecords());
        } catch (Exception e) {
            logger.error("查询充电桩预约记录时发生异常", e);
            return JsonResult.fail("系统异常：" + e.getMessage());
        }
    }


//    通过时间范围获取充电桩的预约情况
    @Override
    public JsonResult getStationReservationsByTimeRange(Integer stationId, String startTime, String endTime) {
        try {
            // 参数验证
            if (stationId == null || startTime == null || endTime == null) {
                return JsonResult.fail("参数不能为空");
            }

            // 解析时间字符串
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime startDateTime;
            LocalDateTime endDateTime;

            try {
                startDateTime = LocalDateTime.parse(startTime, formatter);
                endDateTime = LocalDateTime.parse(endTime, formatter);
            } catch (Exception e) {
                return JsonResult.fail("时间格式错误，请使用 yyyy-MM-dd HH:mm:ss 格式");
            }

            // 检查时间有效性
            if (startDateTime.isAfter(endDateTime)) {
                return JsonResult.fail("开始时间不能晚于结束时间");
            }

            // 检查充电桩是否存在
            ChargingStation chargingStation = chargingStationsMapper.selectById(stationId);
            if (chargingStation == null) {
                return JsonResult.fail("充电桩不存在");
            }

            // 查询指定时间段内的预约记录
            List<Reservation> reservations = reservationMapper.selectByTimeRangeAndStationId(
                    stationId, startDateTime, endDateTime);

            return JsonResult.success(reservations);
        } catch (Exception e) {
            logger.error("查询时间段内充电桩预约记录时发生异常", e);
            return JsonResult.fail("系统异常：" + e.getMessage());
        }
    }
}
