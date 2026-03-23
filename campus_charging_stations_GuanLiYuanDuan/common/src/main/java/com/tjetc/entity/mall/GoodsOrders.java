package com.tjetc.entity.mall;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 13. 商城订单表
 */
@Data
@TableName("goods_orders")
public class GoodsOrders {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String username;
    private String orderNo;
    private Integer orderStatus; // 0-待支付...
    private Integer orderType;
    private Integer totalAmount; // 单位：分
    private Integer payAmount;
    private Integer discountAmount;
    private Integer freightAmount;
    private Integer payType;
    private LocalDateTime payTime;
    private String transactionId;
    private String remark;
    private Integer source;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    private LocalDateTime confirmTime;
}