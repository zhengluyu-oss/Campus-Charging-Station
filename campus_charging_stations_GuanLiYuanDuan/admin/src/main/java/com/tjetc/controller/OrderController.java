package com.tjetc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tjetc.common.JsonResult;
import com.tjetc.entity.core.Orders;
import com.tjetc.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 查询所有订单（无分页）
     * HTTP 请求方式：GET
     */
    @GetMapping("list")
    public JsonResult<List<Orders>> list() {
        return orderService.findAll();
    }

    /**
     * 新增订单
     * HTTP 请求方式：POST
     */
    @PostMapping("add")
    public JsonResult<Orders> add(@RequestBody Orders order) {
        return orderService.add(order);
    }

    /**
     * 按订单状态分页查询订单
     * HTTP 请求方式：GET
     */
    @GetMapping("page/order-status")
    public JsonResult<IPage<Orders>> pageByOrderStatus(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(value = "orderStatus", required = false, defaultValue = "") String orderStatus) {
        return orderService.findPageByOrderStatus(pageNum, pageSize, orderStatus);
    }

    /**
     * 按支付状态分页查询订单
     * HTTP 请求方式：GET
     */
    @GetMapping("page/payment-status")
    public JsonResult<IPage<Orders>> pageByPaymentStatus(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(value = "paymentStatus", required = false, defaultValue = "") String paymentStatus) {
        return orderService.findPageByPaymentStatus(pageNum, pageSize, paymentStatus);
    }

    /**
     * 按用户ID分页查询订单
     * HTTP 请求方式：GET
     */
    @GetMapping("page/user")
    public JsonResult<IPage<Orders>> pageByUserId(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam("userId") Integer userId) {
        return orderService.findPageByUserId(pageNum, pageSize, userId);
    }

    /**
     * 按充电桩ID分页查询订单
     * HTTP 请求方式：GET
     */
    @GetMapping("page/station")
    public JsonResult<IPage<Orders>> pageByStationId(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam("stationId") Integer stationId) {
        return orderService.findPageByStationId(pageNum, pageSize, stationId);
    }

    /**
     * 按时间范围统计订单数据（订单数、总金额）
     * HTTP 请求方式：GET
     */
    @GetMapping("count/time-range")
    public JsonResult<Map<String, Object>> countOrderDataByTimeRange(
            @RequestParam("startTime") LocalDateTime startTime,
            @RequestParam("endTime") LocalDateTime endTime) {
        return orderService.countOrderDataByTimeRange(startTime, endTime);
    }

    /**
     * 查询订单详情（根据 ID）
     * HTTP 请求方式：GET
     */
    @GetMapping("detail/{id}")
    public JsonResult<Orders> detail(@PathVariable("id") Integer id) {
        return orderService.findById(id);
    }

    /**
     * 更新订单状态和总金额
     * HTTP 请求方式：PUT
     */
    @PutMapping("update/status-amount")
    public JsonResult<Orders> updateOrderStatusAndAmount(
            @RequestParam("orderId") Integer orderId,
            @RequestParam("orderStatus") String orderStatus,
            @RequestParam("totalAmount") BigDecimal totalAmount) {
        return orderService.updateOrderStatusAndAmount(orderId, orderStatus, totalAmount);
    }

    /**
     * 删除订单（根据 ID）
     * HTTP 请求方式：DELETE
     */
    @DeleteMapping("delete/{id}")
    public JsonResult<String> delete(@PathVariable("id") Integer id, HttpServletRequest request) {
        // 可按需获取请求头（如token）做权限校验：request.getHeader("token")
        return orderService.deleteById(id);
    }
}