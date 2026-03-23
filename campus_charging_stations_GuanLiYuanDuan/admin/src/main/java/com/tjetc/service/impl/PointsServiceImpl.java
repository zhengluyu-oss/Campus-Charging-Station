package com.tjetc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.common.JsonResult;
import com.tjetc.dao.PointsRecordMapper;
import com.tjetc.dao.UserPointsMapper;


import com.tjetc.entity.points.PointsRecord;
import com.tjetc.entity.points.UserPoints;
import com.tjetc.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PointsServiceImpl implements PointsService {
    @Autowired private UserPointsMapper userPointsMapper;
    @Autowired private PointsRecordMapper pointsRecordMapper;

    @Override
    public JsonResult findRecordsPage(Integer pageNum, Integer pageSize, Long userId) {
        Page<PointsRecord> page = new Page<>(pageNum, pageSize);
        QueryWrapper<PointsRecord> qw = new QueryWrapper<>();
        if (userId != null) qw.eq("user_id", userId);
        qw.orderByDesc("created_at");
        return new JsonResult<>(1, "查询成功", pointsRecordMapper.selectPage(page, qw));
    }

    @Override
    @Transactional
    public JsonResult adjustPoints(Long userId, Integer points, String reason) {
        UserPoints userPoints = userPointsMapper.selectOne(new QueryWrapper<UserPoints>().eq("user_id", userId));
        if (userPoints == null) {
            userPoints = new UserPoints();
            userPoints.setUserId(userId.intValue());
            userPoints.setPoints(0);
            userPointsMapper.insert(userPoints);
        }
        int balanceAfter = userPoints.getPoints() + points;
        if (balanceAfter < 0) return new JsonResult<>(0, "积分不足");

        userPoints.setPoints(balanceAfter);
        userPointsMapper.updateById(userPoints);

        PointsRecord record = new PointsRecord();
        record.setUserId(userId);
        record.setPointsChange(points);
        record.setBalanceAfter(balanceAfter);
        record.setReason(reason);
        record.setReferenceId("ADMIN_MANUAL");
        pointsRecordMapper.insert(record);

        return new JsonResult<>(1, "调整成功");
    }
}