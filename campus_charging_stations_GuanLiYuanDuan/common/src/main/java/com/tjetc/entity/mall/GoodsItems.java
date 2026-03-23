package com.tjetc.entity.mall;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 12. 商品表 (订单明细)
 */
@Data
@TableName("goods_items")
public class GoodsItems {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long orderId;
    private Long productId;
    private String productName;
    private String productPic;
    private Integer price; // 分
    private Integer quantity;
    private Integer totalPrice; // 分
}