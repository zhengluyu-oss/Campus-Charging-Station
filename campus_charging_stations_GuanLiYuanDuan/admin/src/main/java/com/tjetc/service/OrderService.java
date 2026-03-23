package com.tjetc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tjetc.common.JsonResult;
import com.tjetc.entity.core.Orders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface OrderService {
    /**
     * 查询所有订单
     *
     * @return 统一响应结果（包含订单列表）
     */
    // 核心修正：返回值从 JsonResult<Orders> 改为 JsonResult<List<Orders>>
    JsonResult<List<Orders>> findAll();

    /**
     * 新增订单
     *
     * @param order 订单实体
     * @return 统一响应结果（包含新增的订单）
     */
    // 补充泛型：JsonResult → JsonResult<Orders>
    JsonResult<Orders> add(Orders order);

    /**
     * 按订单状态分页查询订单
     *
     * @param pageNum     页码
     * @param pageSize    每页数量
     * @param orderStatus 订单状态
     * @return 统一响应结果（包含分页订单数据）
     */
    // 补充泛型：JsonResult → JsonResult<IPage<Orders>>
    JsonResult<IPage<Orders>> findPageByOrderStatus(Integer pageNum, Integer pageSize, String orderStatus);

    /**
     * 按支付状态分页查询订单
     *
     * @param pageNum        页码
     * @param pageSize       每页数量
     * @param paymentStatus  支付状态
     * @return 统一响应结果（包含分页订单数据）
     */
    // 补充泛型：JsonResult → JsonResult<IPage<Orders>>
    JsonResult<IPage<Orders>> findPageByPaymentStatus(Integer pageNum, Integer pageSize, String paymentStatus);

    /**
     * 按用户ID分页查询订单
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @param userId   用户ID
     * @return 统一响应结果（包含分页订单数据）
     */
    // 补充泛型：JsonResult → JsonResult<IPage<Orders>>
    JsonResult<IPage<Orders>> findPageByUserId(Integer pageNum, Integer pageSize, Integer userId);

    /**
     * 按充电桩ID分页查询订单
     *
     * @param pageNum    页码
     * @param pageSize   每页数量
     * @param stationId  充电桩ID
     * @return 统一响应结果（包含分页订单数据）
     */
    // 补充泛型：JsonResult → JsonResult<IPage<Orders>>
    JsonResult<IPage<Orders>> findPageByStationId(Integer pageNum, Integer pageSize, Integer stationId);

    /**
     * 按时间范围统计订单数据（订单数、总金额）
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 统一响应结果（包含统计数据）
     */
    // 补充泛型：JsonResult → JsonResult<Map<String, Object>>
    JsonResult<Map<String, Object>> countOrderDataByTimeRange(LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 根据订单ID查询订单信息
     *
     * @param id 订单ID
     * @return 统一响应结果（包含单个订单）
     */
    // 补充泛型：JsonResult → JsonResult<Orders>
    JsonResult<Orders> findById(Integer id);

    /**
     * 更新订单状态和总金额
     *
     * @param orderId     订单ID
     * @param orderStatus 订单状态
     * @param totalAmount 订单总金额
     * @return 统一响应结果（包含更新后的订单）
     */
    // 补充泛型：JsonResult → JsonResult<Orders>
    JsonResult<Orders> updateOrderStatusAndAmount(Integer orderId, String orderStatus, BigDecimal totalAmount);

    /**
     * 根据订单ID删除订单
     *
     * @param id 订单ID
     * @return 统一响应结果（包含提示信息）
     */
    // 补充泛型：JsonResult → JsonResult<String>
    JsonResult<String> deleteById(Integer id);
}