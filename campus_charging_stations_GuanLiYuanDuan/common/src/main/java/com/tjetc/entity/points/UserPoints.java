package com.tjetc.entity.points;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 8. 积分表格
 */
@Data
@TableName("user_points")
public class UserPoints {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer points;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;
}
