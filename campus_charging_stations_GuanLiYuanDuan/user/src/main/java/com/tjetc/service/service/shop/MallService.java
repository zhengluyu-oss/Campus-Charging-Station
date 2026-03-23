package com.tjetc.service.service.shop;

import com.tjetc.common.JsonResult;

public interface MallService {

    /**
     * 商城下单
     * 注意：由于数据库无商品表，需由前端传入商品名称、价格、图片等快照信息
     *
     * @param userId      用户ID
     * @param productId   商品ID (逻辑ID，用于前端标识)
     * @param productName 商品名称
     * @param productPic  商品图片URL
     * @param price       单价 (单位：分)
     * @param quantity    数量
     * @param address     收货地址详情
     * @return JsonResult
     */
    JsonResult createOrder(Long userId, Long productId, String productName, String productPic, Integer price, Integer quantity, String address);

    /**
     * 获取用户的所有订单
     * @param userId 用户ID
     */
    JsonResult getUserOrders(Long userId);

    /**
     * 获取订单详情（包含商品明细和地址）
     * @param orderId 订单ID
     */
    JsonResult getOrderDetail(Long orderId);

    /**
     * 取消订单（仅限待支付）
     * @param orderId 订单ID
     */
    JsonResult cancelOrder(Long orderId);
}