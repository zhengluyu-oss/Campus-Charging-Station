package com.tjetc.service;
import com.tjetc.common.JsonResult;

public interface MallOrderService {
    // 分页查询订单
    JsonResult findPage(Integer pageNum, Integer pageSize, String orderNo, Integer status);
    // 获取订单详情（含商品和地址）
    JsonResult getDetail(Long orderId);
    // 发货
    JsonResult deliverOrder(Long orderId);
}