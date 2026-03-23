package com.tjetc.service;
import com.tjetc.common.JsonResult;

public interface PointsService {
    // 分页查询用户的积分变动记录
    JsonResult findRecordsPage(Integer pageNum, Integer pageSize, Long userId);
    // 管理员给用户调整积分
    JsonResult adjustPoints(Long userId, Integer points, String reason);
}