package com.tjetc.entity.core;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tjetc.enums.core.payments.PaymentsMethod;
import com.tjetc.enums.core.payments.PaymentsStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("payments")
public class Payments {
    @TableId(value = "payment_id", type = IdType.AUTO)
    private Integer paymentId;
    private Integer orderId;
    private double amount;
    private PaymentsMethod paymentMethod;
    private PaymentsStatus paymentStatus;
    private String paymentTime;

}
