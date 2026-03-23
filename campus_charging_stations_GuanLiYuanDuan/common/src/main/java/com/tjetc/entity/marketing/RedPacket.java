package com.tjetc.entity.marketing;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 15. 红包类型表
 */
@Data
@TableName("red_packet")
public class RedPacket {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private BigDecimal amount;
    private BigDecimal minCharge;
    private Integer totalCount;
    private Integer perUserLimit;
    private Integer validDays;
    private Integer status; // 1-启用，2-停用
}