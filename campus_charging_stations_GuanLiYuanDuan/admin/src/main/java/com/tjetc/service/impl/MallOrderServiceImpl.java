package com.tjetc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.common.JsonResult;
import com.tjetc.dao.GoodsItemsMapper;
import com.tjetc.dao.GoodsOrderAddressMapper;
import com.tjetc.dao.GoodsOrdersMapper;


import com.tjetc.entity.mall.GoodsItems;
import com.tjetc.entity.mall.GoodsOrderAddress;
import com.tjetc.entity.mall.GoodsOrders;
import com.tjetc.service.MallOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MallOrderServiceImpl implements MallOrderService {
    @Autowired private GoodsOrdersMapper ordersMapper;
    @Autowired private GoodsItemsMapper itemsMapper;
    @Autowired private GoodsOrderAddressMapper addressMapper;

    @Override
    public JsonResult findPage(Integer pageNum, Integer pageSize, String orderNo, Integer status) {
        Page<GoodsOrders> page = new Page<>(pageNum, pageSize);
        QueryWrapper<GoodsOrders> qw = new QueryWrapper<>();
        if (orderNo != null && !orderNo.isEmpty()) qw.eq("order_no", orderNo);
        if (status != null) qw.eq("order_status", status);
        qw.orderByDesc("create_time");
        return new JsonResult(1, "查询成功", ordersMapper.selectPage(page, qw));
    }

    @Override
    public JsonResult getDetail(Long orderId) {
        GoodsOrders order = ordersMapper.selectById(orderId);
        if (order == null) return new JsonResult(0, "订单不存在");

        List<GoodsItems> items = itemsMapper.selectList(new QueryWrapper<GoodsItems>().eq("order_id", orderId));
        GoodsOrderAddress address = addressMapper.selectById(orderId);

        Map<String, Object> result = new HashMap<>();
        result.put("order", order);
        result.put("items", items);
        result.put("address", address);
        return new JsonResult(1, "查询成功", result);
    }

    @Override
    public JsonResult deliverOrder(Long orderId) {
        GoodsOrders order = new GoodsOrders();
        order.setId(orderId);
        order.setOrderStatus(2); // 假设 2 是已发货
        ordersMapper.updateById(order);
        return new JsonResult(1, "发货成功");
    }
}