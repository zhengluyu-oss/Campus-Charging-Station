package com.tjetc.entity.marketing;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 10. 卡卷表格
 */
@Data
@TableName("coupon_template")
public class CouponTemplate {
    @TableId(type = IdType.ASSIGN_ID) // 数据库是BIGINT，建议使用雪花算法ID
    private Long id;
    private String name;
    private Integer type; // 1-满减券, 2-折扣券, 3-代金券
    private BigDecimal discountValue;
    private BigDecimal minOrderAmount;
    private Integer validDays;
    private Integer totalCount;
    private Integer perUserLimit;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
}