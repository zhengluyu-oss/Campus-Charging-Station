package com.tjetc.service.Impl.shopImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tjetc.common.JsonResult;
import com.tjetc.dao.GoodsItemsMapper;
import com.tjetc.dao.GoodsOrderAddressMapper;
import com.tjetc.dao.GoodsOrdersMapper;
import com.tjetc.entity.mall.GoodsItems;
import com.tjetc.entity.mall.GoodsOrderAddress;
import com.tjetc.entity.mall.GoodsOrders;
import com.tjetc.service.service.shop.MallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class MallServiceImpl implements MallService {

    @Autowired
    private GoodsOrdersMapper goodsOrdersMapper;
    @Autowired
    private GoodsItemsMapper goodsItemsMapper;
    @Autowired
    private GoodsOrderAddressMapper addressMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult createOrder(Long userId, Long productId, String productName, String productPic, Integer price, Integer quantity, String address) {
        // 1. 计算总金额
        int totalAmount = price * quantity;

        // 2. 创建订单主表 (GoodsOrders)
        GoodsOrders order = new GoodsOrders();
        order.setUserId(userId);
        order.setOrderNo(generateOrderNo()); // 生成唯一订单号
        order.setOrderStatus(0); // 0-待支付
        order.setOrderType(1);   // 1-普通商品订单
        order.setTotalAmount(totalAmount);
        order.setPayAmount(totalAmount); // 暂无优惠
        order.setDiscountAmount(0);
        order.setFreightAmount(0);
        order.setSource(1);      // 1-APP/小程序
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());

        // 插入并回填ID
        goodsOrdersMapper.insert(order);

        // 3. 创建订单商品明细 (GoodsItems)
        GoodsItems item = new GoodsItems();
        item.setOrderId(order.getId());
        item.setProductId(productId);
        item.setProductName(productName);
        item.setProductPic(productPic);
        item.setPrice(price);
        item.setQuantity(quantity);
        item.setTotalPrice(totalAmount);

        goodsItemsMapper.insert(item);

        // 4. 创建订单收货地址快照 (GoodsOrderAddress)
        GoodsOrderAddress orderAddress = new GoodsOrderAddress();
        orderAddress.setOrderId(order.getId());
        orderAddress.setDetailAddress(address);
        orderAddress.setReceiverName("默认收货人"); // 实际业务应从前端传入
        orderAddress.setReceiverPhone("13800000000"); // 实际业务应从前端传入
        orderAddress.setBingLocation("默认位置");

        addressMapper.insert(orderAddress);

        return new JsonResult(1, "下单成功", order.getOrderNo());
    }

    @Override
    public JsonResult getUserOrders(Long userId) {
        QueryWrapper<GoodsOrders> qw = new QueryWrapper<>();
        qw.eq("user_id", userId).orderByDesc("create_time");
        List<GoodsOrders> list = goodsOrdersMapper.selectList(qw);
        return new JsonResult(1, "查询成功", list);
    }

    @Override
    public JsonResult getOrderDetail(Long orderId) {
        GoodsOrders order = goodsOrdersMapper.selectById(orderId);
        if (order == null) return new JsonResult(0, "订单不存在");

        List<GoodsItems> items = goodsItemsMapper.selectList(new QueryWrapper<GoodsItems>().eq("order_id", orderId));
        GoodsOrderAddress address = addressMapper.selectById(orderId);

        Map<String, Object> result = new HashMap<>();
        result.put("order", order);
        result.put("items", items);
        result.put("address", address);

        return new JsonResult(1, "查询成功", result);
    }

    @Override
    public JsonResult cancelOrder(Long orderId) {
        GoodsOrders order = goodsOrdersMapper.selectById(orderId);
        if (order != null && order.getOrderStatus() == 0) { // 仅待支付可取消
            order.setOrderStatus(4); // 4-已取消/已关闭
            goodsOrdersMapper.updateById(order);
            return new JsonResult(1, "订单已取消");
        }
        return new JsonResult(0, "订单状态不支持取消");
    }

    // 辅助方法：生成订单号
    private String generateOrderNo() {
        return "MALL" + System.currentTimeMillis() + String.format("%04d", new Random().nextInt(10000));
    }
}