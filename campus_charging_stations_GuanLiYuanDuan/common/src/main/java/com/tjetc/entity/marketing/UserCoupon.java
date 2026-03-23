package com.tjetc.entity.marketing;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 11. 用户卡券实例表
 */
@Data
@TableName("user_coupon")
public class UserCoupon {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private Long userId;
    private Long couponId;
    private Integer status; // 0-未使用, 1-已使用, 2-已过期
    private LocalDateTime receivedAt;
    private LocalDateTime usedAt;
    private LocalDateTime expiredAt;
    private String orderId;
}