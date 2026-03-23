package com.tjetc.entity.marketing;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 16. 用户红包表
 */
@Data
@TableName("user_red_packet")
public class UserRedPacket {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer packetId;
    private Integer status; // 0-未使用，1-已使用，2-已过期
    private LocalDateTime receivedTime;
    private LocalDateTime usedTime;
    private LocalDateTime expiredTime;
}