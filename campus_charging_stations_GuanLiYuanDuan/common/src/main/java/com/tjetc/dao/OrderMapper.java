package com.tjetc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.entity.core.Orders; // 只保留自己的订单实体类导入
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

// 核心修改1：BaseMapper泛型改为自己的Orders（带s）
public interface OrderMapper extends BaseMapper<Orders> {
    /**
     * 1. 按订单状态分页查询（active/completed/cancelled）
     */
    // 核心修改2：所有Page/IPage泛型改为Orders（带s）
    IPage<Orders> selectPageByOrderStatus(Page<Orders> page, @Param("orderStatus") String orderStatus);

    /**
     * 2. 按支付状态分页查询（pending/paid/failed）
     */
    IPage<Orders> selectPageByPaymentStatus(Page<Orders> page, @Param("paymentStatus") String paymentStatus);

    /**
     * 3. 按用户ID分页查询（关联 users 表外键 user_id）
     */
    IPage<Orders> selectPageByUserId(Page<Orders> page, @Param("userId") Integer userId);

    /**
     * 4. 按充电桩ID分页查询（关联 charging_stations 表外键 station_id）
     */
    IPage<Orders> selectPageByStationId(Page<Orders> page, @Param("stationId") Integer stationId);

    /**
     * 5. 按时间范围统计订单数据（订单数、总金额）
     */
    Map<String, Object> countOrderDataByTimeRange(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    /**
     * 6. 按用户ID查询所有订单
     */
    // 核心修改3：List泛型改为Orders（带s）
    List<Orders> selectByUserId(@Param("userId") Integer userId);

    /**
     * 7. 更新订单状态和总金额
     */
    int updateOrderStatusAndAmount(@Param("orderId") Integer orderId, @Param("orderStatus") String orderStatus, @Param("totalAmount") BigDecimal totalAmount);

    // 核心修改4：删掉手动定义的insert方法（BaseMapper已经自带，留着会冲突）
}