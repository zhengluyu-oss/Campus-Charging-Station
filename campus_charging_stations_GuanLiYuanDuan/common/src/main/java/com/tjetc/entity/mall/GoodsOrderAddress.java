package com.tjetc.entity.mall;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 14. 收货地址快照表
 */
@Data
@TableName("goods_order__addresses") // 注意数据库表名有两个下划线
public class GoodsOrderAddress {
    @TableId(type = IdType.INPUT) // order_id 是主键也是外键
    private Long orderId;
    private String receiverName;
    private String receiverPhone;
    private String bingLocation;
    private String detailAddress;
}