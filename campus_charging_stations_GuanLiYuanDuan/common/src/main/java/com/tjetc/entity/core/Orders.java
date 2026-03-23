package com.tjetc.entity.core;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tjetc.enums.core.order.OrderStatus;
import com.tjetc.enums.core.order.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("orders")
public class Orders {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String userId;
    private String stationId;
    private String startTime;
    private String endTime;
    private String durationMinutes;
    private double totalAmount;
    private PaymentStatus paymentStatus;
    private OrderStatus orderStatus;
    private String createTime;
}
