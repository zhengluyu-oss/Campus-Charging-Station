package com.tjetc.entity.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 充电桩实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChargingStation implements Serializable {

    private Integer stationId;

    private String stationName;

    // 【修改点1】 将枚举改为 String，直接接收前端的地址字符串
    private String location;

    // 【修改点2】 将枚举改为 Integer，直接接收前端的 0 或 1
    private Integer status;

    private Double powerRating;

    private Double pricePerHour;

    private String createdTime;

    private String updatedTime;
}