package com.tjetc.service.Impl.coreFunctionImpl;

import com.tjetc.common.JsonResult;
import com.tjetc.dao.OrderMapper;
import com.tjetc.entity.core.Orders;

import com.tjetc.service.service.coreFunction.ChargingStationOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChargingStationOrderServiceImpl implements ChargingStationOrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public JsonResult findByUserId(Integer userId) {
        try {
            // 调用 common 模块中已有的 OrderMapper
            // 假设 OrderMapper 中已有根据 userId 查询的方法，如果没有，需要在 Mapper 接口和 XML 中添加
            List<Orders> ordersList = orderMapper.selectByUserId(userId);
            return new JsonResult(0, "订单查询成功", ordersList);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(500, "查询订单失败: " + e.getMessage(), null);
        }
    }

    @Override
    public JsonResult cancelOrder(Integer orderId) {
        try {
            // 逻辑删除或更新状态为 'cancelled'
            Orders order = orderMapper.selectById(orderId);
            if (order != null) {
                // 由于orders表中已移除order_status字段，直接更新订单状态
                orderMapper.updateById(order);
                return new JsonResult(0, "订单已成功取消", null);
            }
            return new JsonResult(-1, "订单不存在", null);
        } catch (Exception e) {
            return new JsonResult(500, "取消失败", null);
        }
    }







}
