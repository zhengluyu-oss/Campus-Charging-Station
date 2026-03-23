package com.tjetc.entity.points;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 9. 积分变动表
 */
@Data
@TableName("points_record")
public class PointsRecord {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Integer pointsChange;
    private Integer balanceAfter;
    private String reason;
    private String referenceId; // 关联业务ID

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
}