package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.core.Payments;
import com.tjetc.service.PaymentsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * 支付记录控制器
 * 处理支付记录相关的HTTP请求
 */
@RestController
@RequestMapping("/payments")
@Slf4j
public class PaymentsController {

    @Autowired
    private PaymentsService paymentsService;

    /**
     * 查询所有支付记录
     * GET /payments/all
     * @return JsonResult 所有支付记录列表
     */
    @GetMapping("/all")
    public JsonResult<Payments> findAll() {
        log.info("接收查询所有支付记录请求");
        return paymentsService.findAll();
    }

    /**
     * 新增支付记录
     * POST /payments/add
     * @param payments 支付记录实体（JSON格式）
     * @return JsonResult 新增结果
     */
    @PostMapping("/add")
    public JsonResult add(@RequestBody Payments payments) {
        log.info("接收新增支付记录请求，参数：{}", payments);
        return paymentsService.add(payments);
    }

    /**
     * 按订单ID查询支付记录
     * GET /payments/order/{orderId}
     * @param orderId 订单ID（路径参数）
     * @return JsonResult 该订单下的支付记录列表
     */
    @GetMapping("/order/{orderId}")
    public JsonResult findByOrderId(@PathVariable Integer orderId) {
        log.info("接收按订单ID查询支付记录请求，orderId：{}", orderId);
        return paymentsService.findByOrderId(orderId);
    }

    /**
     * 按支付状态分页查询支付记录
     * GET /payments/page/status?pageNum=1&pageSize=10&paymentStatus=success
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param paymentStatus 支付状态（success/failed/refunded）
     * @return JsonResult 分页后的支付记录列表
     */
    @GetMapping("/page/status")
    public JsonResult findPageByPaymentStatus(
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize,
            @RequestParam String paymentStatus) {
        log.info("接收按支付状态分页查询请求，pageNum：{}，pageSize：{}，paymentStatus：{}",
                pageNum, pageSize, paymentStatus);
        return paymentsService.findPageByPaymentStatus(pageNum, pageSize, paymentStatus);
    }

    /**
     * 按支付方式分页查询支付记录
     * GET /payments/page/method?pageNum=1&pageSize=10&paymentMethod=alipay
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param paymentMethod 支付方式（cash/creditCard/alipay/wechatPay）
     * @return JsonResult 分页后的支付记录列表
     */
    @GetMapping("/page/method")
    public JsonResult findPageByPaymentMethod(
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize,
            @RequestParam String paymentMethod) {
        log.info("接收按支付方式分页查询请求，pageNum：{}，pageSize：{}，paymentMethod：{}",
                pageNum, pageSize, paymentMethod);
        return paymentsService.findPageByPaymentMethod(pageNum, pageSize, paymentMethod);
    }

    /**
     * 按ID查询支付记录
     * GET /payments/{id}
     * @param id 支付记录ID（路径参数）
     * @return JsonResult 对应的支付记录
     */
    @GetMapping("/{id}")
    public JsonResult findById(@PathVariable Integer id) {
        log.info("接收按ID查询支付记录请求，id：{}", id);
        return paymentsService.findById(id);
    }

    /**
     * 更新支付状态和支付时间
     * PUT /payments/update/status-time
     * @param paymentId 支付记录ID
     * @param paymentStatus 新的支付状态（success/failed/refunded）
     * @param paidTime 实际支付时间（格式：yyyy-MM-dd HH:mm:ss）
     * @return JsonResult 更新结果
     */
    @PutMapping("/update/status-time")
    public JsonResult updatePaymentStatusAndTime(
            @RequestParam Integer paymentId,
            @RequestParam String paymentStatus,
            @RequestParam LocalDateTime paidTime) {
        log.info("接收更新支付状态和时间请求，paymentId：{}，paymentStatus：{}，paidTime：{}",
                paymentId, paymentStatus, paidTime);
        return paymentsService.updatePaymentStatusAndTime(paymentId, paymentStatus, paidTime);
    }

    /**
     * 按ID删除支付记录
     * DELETE /payments/{id}
     * @param id 支付记录ID（路径参数）
     * @return JsonResult 删除结果
     */
    @DeleteMapping("/{id}")
    public JsonResult deleteById(@PathVariable Integer id) {
        log.info("接收按ID删除支付记录请求，id：{}", id);
        return paymentsService.deleteById(id);
    }
}