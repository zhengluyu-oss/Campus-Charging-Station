package com.tjetc.service.Impl.coreFunctionImpl;

import com.tjetc.common.JsonResult;
import com.tjetc.dao.ChargingStationsMapper;
import com.tjetc.dao.OrderMapper;
import com.tjetc.entity.core.ChargingStation;
import com.tjetc.entity.core.Orders;
import com.tjetc.enums.core.chargingStation.ChargingStationStatus;
import com.tjetc.enums.core.order.OrderStatus;
import com.tjetc.enums.core.order.PaymentStatus;
import com.tjetc.service.service.coreFunction.ChargingStationUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ChargingStationUseServiceImpl implements ChargingStationUseService {

    @Autowired
    private ChargingStationsMapper chargingStationsMapper;

    @Autowired
    private OrderMapper orderMapper;

    // 定义时间格式化器，因为你的实体类里时间是 String 类型
    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult useChargingStation(Integer stationId, Integer userId, double durationMinutes) {
        // 1. 检查充电桩
        ChargingStation station = chargingStationsMapper.selectById(stationId);
        if (station == null) {
            return new JsonResult(0, "充电桩不存在");
        }

        // 修复1: 枚举常量大小写问题 (根据你的文件，枚举是 available 小写)
        if (station.getStatus() != ChargingStationStatus.available) {
            return new JsonResult(0, "充电桩当前不可用");
        }

        // 2. 更新充电桩状态
        // 修复2: 使用小写枚举 occupied
        station.setStatus(ChargingStationStatus.occupied);
        chargingStationsMapper.updateById(station);

        // 3. 创建订单
        Orders order = new Orders();

        // 修复3: 类型转换问题 (Orders实体类中 userId 是 String，这里转成 String)
        order.setUserId(String.valueOf(userId));

        // 修复4: 类型转换问题 (Orders实体类中 stationId 是 String)
        order.setStationId(String.valueOf(stationId));

        // 修复5: 时间类型问题 (Orders实体类中 startTime 是 String)
        order.setStartTime(LocalDateTime.now().format(df));

        // 修复6: 时间类型问题
        order.setEndTime(LocalDateTime.now().plusMinutes((long) durationMinutes).format(df));

        order.setDurationMinutes(String.valueOf(durationMinutes));

        // 修复7: 枚举大小写问题 (active, pending)
        order.setOrderStatus(OrderStatus.active);
        order.setPaymentStatus(PaymentStatus.pending);

        order.setCreateTime(LocalDateTime.now().format(df));

        // 插入订单
        orderMapper.insert(order);

        return new JsonResult(1, "充电开启成功", order.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult stopCharging(Integer orderId) {
        Orders order = orderMapper.selectById(orderId);
        if(order == null) return new JsonResult(0, "订单不存在");

        // 1. 更新订单
        // 修复8: 时间转 String
        order.setEndTime(LocalDateTime.now().format(df));

        // 修复9: 枚举大小写 (completed)
        order.setOrderStatus(OrderStatus.completed);

        orderMapper.updateById(order);

        // 2. 释放充电桩
        // 修复10: Order里 stationId 是 String，Mapper需要 Integer，必须强转
        if (order.getStationId() != null) {
            Integer stationId = Integer.parseInt(order.getStationId());
            ChargingStation station = chargingStationsMapper.selectById(stationId);

            if (station != null) {
                // 修复11: 枚举大小写 (available)
                station.setStatus(ChargingStationStatus.available);
                chargingStationsMapper.updateById(station);
            }
        }

        return new JsonResult(1, "充电结束，请支付");
    }
}