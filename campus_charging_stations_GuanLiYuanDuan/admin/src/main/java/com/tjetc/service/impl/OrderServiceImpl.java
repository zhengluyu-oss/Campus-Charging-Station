package com.tjetc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.common.JsonResult;
import com.tjetc.dao.OrderMapper;
// 核心：导入带s的订单实体类 Orders
import com.tjetc.entity.core.Orders;
import com.tjetc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 订单服务实现类
 * 不依赖MyBatis-Plus ServiceImpl封装，手动注入OrderMapper完成数据库操作
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    // 手动注入OrderMapper，替代ServiceImpl提供的baseMapper
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 查询所有订单
     */
    @Override
    // 泛型用带s的 Orders
    public JsonResult<List<Orders>> findAll() {
        try {
            // List泛型用带s的 Orders
            List<Orders> orderList = orderMapper.selectList(null);
            return JsonResult.success("查询所有订单成功", orderList);
        } catch (Exception e) {
            log.error("查询所有订单失败", e);
            return JsonResult.fail("查询所有订单失败：" + e.getMessage());
        }
    }

    /**
     * 新增订单
     */
    @Override
    // 返回值泛型和方法参数都用带s的 Orders
    public JsonResult<Orders> add(Orders order) {
        // 参数校验
        if (order == null) {
            return JsonResult.fail("新增订单失败：订单实体不能为空");
        }
        if (!StringUtils.hasText(order.getUserId()) || !StringUtils.hasText(order.getStationId())) {
            return JsonResult.fail("新增订单失败：用户ID/充电桩ID不能为空");
        }

        try {
            // 自动填充创建时间（如果未手动设置）
            if (!StringUtils.hasText(order.getCreateTime())) {
                order.setCreateTime(String.valueOf(LocalDateTime.now()));
            }
            int insertCount = orderMapper.insert(order);
            return insertCount > 0 ? JsonResult.success("新增订单成功", order) : JsonResult.fail("新增订单失败");
        } catch (Exception e) {
            log.error("新增订单异常", e);
            return JsonResult.fail("新增订单失败：" + e.getMessage());
        }
    }

    /**
     * 按订单状态分页查询订单
     */
    @Override
    // 返回值泛型用带s的 IPage<Orders>
    public JsonResult<IPage<Orders>> findPageByOrderStatus(Integer pageNum, Integer pageSize, String orderStatus) {
        // 分页参数校验
        if (pageNum == null || pageNum < 1 || pageSize == null || pageSize < 1) {
            return JsonResult.fail("分页参数异常：页码/每页数量必须大于0");
        }
        if (!StringUtils.hasText(orderStatus)) {
            return JsonResult.fail("订单状态不能为空（active/completed/cancelled）");
        }

        try {
            // Page泛型用带s的 Orders
            Page<Orders> page = new Page<>(pageNum, pageSize);
            // IPage泛型用带s的 Orders
            IPage<Orders> orderPage = orderMapper.selectPageByOrderStatus(page, orderStatus);
            return JsonResult.success("按订单状态分页查询成功", orderPage);
        } catch (Exception e) {
            log.error("按订单状态分页查询异常", e);
            return JsonResult.fail("按订单状态分页查询失败：" + e.getMessage());
        }
    }

    /**
     * 按支付状态分页查询订单
     */
    @Override
    public JsonResult<IPage<Orders>> findPageByPaymentStatus(Integer pageNum, Integer pageSize, String paymentStatus) {
        // 分页参数校验
        if (pageNum == null || pageNum < 1 || pageSize == null || pageSize < 1) {
            return JsonResult.fail("分页参数异常：页码/每页数量必须大于0");
        }
        if (!StringUtils.hasText(paymentStatus)) {
            return JsonResult.fail("支付状态不能为空（pending/paid/failed）");
        }

        try {
            Page<Orders> page = new Page<>(pageNum, pageSize);
            IPage<Orders> orderPage = orderMapper.selectPageByPaymentStatus(page, paymentStatus);
            return JsonResult.success("按支付状态分页查询成功", orderPage);
        } catch (Exception e) {
            log.error("按支付状态分页查询异常", e);
            return JsonResult.fail("按支付状态分页查询失败：" + e.getMessage());
        }
    }

    /**
     * 按用户ID分页查询订单
     */
    @Override
    public JsonResult<IPage<Orders>> findPageByUserId(Integer pageNum, Integer pageSize, Integer userId) {
        if (pageNum == null || pageNum < 1 || pageSize == null || pageSize < 1) {
            return JsonResult.fail("分页参数异常：页码/每页数量必须大于0");
        }
        if (userId == null || userId < 1) {
            return JsonResult.fail("用户ID不能为空且必须大于0");
        }

        try {
            Page<Orders> page = new Page<>(pageNum, pageSize);
            IPage<Orders> orderPage = orderMapper.selectPageByUserId(page, userId);
            return JsonResult.success("按用户ID分页查询成功", orderPage);
        } catch (Exception e) {
            log.error("按用户ID分页查询异常", e);
            return JsonResult.fail("按用户ID分页查询失败：" + e.getMessage());
        }
    }

    /**
     * 按充电桩ID分页查询订单
     */
    @Override
    public JsonResult<IPage<Orders>> findPageByStationId(Integer pageNum, Integer pageSize, Integer stationId) {
        if (pageNum == null || pageNum < 1 || pageSize == null || pageSize < 1) {
            return JsonResult.fail("分页参数异常：页码/每页数量必须大于0");
        }
        if (stationId == null || stationId < 1) {
            return JsonResult.fail("充电桩ID不能为空且必须大于0");
        }

        try {
            Page<Orders> page = new Page<>(pageNum, pageSize);
            IPage<Orders> orderPage = orderMapper.selectPageByStationId(page, stationId);
            return JsonResult.success("按充电桩ID分页查询成功", orderPage);
        } catch (Exception e) {
            log.error("按充电桩ID分页查询异常", e);
            return JsonResult.fail("按充电桩ID分页查询失败：" + e.getMessage());
        }
    }

    /**
     * 按时间范围统计订单数据（订单数、总金额）
     */
    @Override
    public JsonResult<Map<String, Object>> countOrderDataByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        // 时间参数校验
        if (startTime == null || endTime == null) {
            return JsonResult.fail("时间范围不能为空：开始时间/结束时间必须传入");
        }
        if (startTime.isAfter(endTime)) {
            return JsonResult.fail("时间范围异常：开始时间不能晚于结束时间");
        }

        try {
            Map<String, Object> countMap = orderMapper.countOrderDataByTimeRange(startTime, endTime);
            // 封装统计结果（兼容空数据）
            Long orderCount = (Long) countMap.getOrDefault("orderCount", 0L);
            BigDecimal totalAmount = (BigDecimal) countMap.getOrDefault("totalAmount", BigDecimal.ZERO);
            return JsonResult.success("时间范围订单统计成功", Map.of(
                    "orderCount", orderCount,
                    "totalAmount", totalAmount.doubleValue() // 兼容实体类的double类型
            ));
        } catch (Exception e) {
            log.error("时间范围订单统计异常", e);
            return JsonResult.fail("时间范围订单统计失败：" + e.getMessage());
        }
    }

    /**
     * 根据订单ID查询订单信息
     */
    @Override
    public JsonResult<Orders> findById(Integer id) {
        if (id == null || id < 1) {
            return JsonResult.fail("订单ID不能为空且必须大于0");
        }

        try {
            // 变量类型用带s的 Orders
            Orders order = orderMapper.selectById(id);
            if (order == null) {
                return JsonResult.fail("查询订单失败：未找到ID为" + id + "的订单");
            }
            return JsonResult.success("查询订单成功", order);
        } catch (Exception e) {
            log.error("按ID查询订单异常", e);
            return JsonResult.fail("按ID查询订单失败：" + e.getMessage());
        }
    }

    /**
     * 更新订单状态和总金额
     */
    @Override
    public JsonResult<Orders> updateOrderStatusAndAmount(Integer orderId, String orderStatus, BigDecimal totalAmount) {
        // 参数校验
        if (orderId == null || orderId < 1) {
            return JsonResult.fail("订单ID不能为空且必须大于0");
        }
        if (!StringUtils.hasText(orderStatus)) {
            return JsonResult.fail("订单状态不能为空（active/completed/cancelled）");
        }
        if (totalAmount == null || totalAmount.compareTo(BigDecimal.ZERO) < 0) {
            return JsonResult.fail("订单金额异常：金额不能为负数");
        }

        try {
            // 变量类型用带s的 Orders
            Orders existOrder = orderMapper.selectById(orderId);
            if (existOrder == null) {
                return JsonResult.fail("更新订单失败：未找到ID为" + orderId + "的订单");
            }

            // 执行更新
            int updateCount = orderMapper.updateOrderStatusAndAmount(orderId, orderStatus, totalAmount);
            if (updateCount > 0) {
                // 更新后查询最新数据返回，类型用带s的 Orders
                Orders updatedOrder = orderMapper.selectById(orderId);
                return JsonResult.success("更新订单状态和金额成功", updatedOrder);
            } else {
                return JsonResult.fail("更新订单状态和金额失败：无数据被修改");
            }
        } catch (Exception e) {
            log.error("更新订单状态和金额异常", e);
            return JsonResult.fail("更新订单状态和金额失败：" + e.getMessage());
        }
    }

    /**
     * 根据订单ID删除订单
     */
    @Override
    public JsonResult<String> deleteById(Integer id) {
        if (id == null || id < 1) {
            return JsonResult.fail("订单ID不能为空且必须大于0");
        }

        try {
            // 变量类型用带s的 Orders
            Orders existOrder = orderMapper.selectById(id);
            if (existOrder == null) {
                return JsonResult.fail("删除订单失败：未找到ID为" + id + "的订单");
            }

            int deleteCount = orderMapper.deleteById(id);
            return deleteCount > 0 ? JsonResult.success("删除订单成功") : JsonResult.fail("删除订单失败");
        } catch (Exception e) {
            log.error("删除订单异常", e);
            return JsonResult.fail("删除订单失败：" + e.getMessage());
        }
    }
}