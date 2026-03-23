package com.tjetc.service.Impl.userFunctionImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tjetc.common.JsonResult;
import com.tjetc.dao.UserCouponMapper;
import com.tjetc.dao.UserPointsMapper;
import com.tjetc.dao.UserRedPacketMapper;
import com.tjetc.entity.marketing.UserCoupon;
import com.tjetc.entity.marketing.UserRedPacket;
import com.tjetc.entity.points.UserPoints;
import com.tjetc.service.service.userFunction.UserWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserWalletServiceImpl implements UserWalletService {

    @Autowired private UserPointsMapper userPointsMapper;
    @Autowired private UserCouponMapper userCouponMapper;
    @Autowired private UserRedPacketMapper userRedPacketMapper;

    @Override
    public JsonResult getMyPoints(Integer userId) {
        UserPoints points = userPointsMapper.selectOne(new QueryWrapper<UserPoints>().eq("user_id", userId));
        return new JsonResult(1, "查询成功", points == null ? 0 : points.getPoints());
    }

    @Override
    public JsonResult getMyCoupons(Integer userId) {
        List<UserCoupon> list = userCouponMapper.selectList(
                new QueryWrapper<UserCoupon>().eq("user_id", userId).eq("status", 0) // 0-未使用
        );
        return new JsonResult(1, "查询成功", list);
    }

    @Override
    public JsonResult getMyRedPackets(Integer userId) {
        List<UserRedPacket> list = userRedPacketMapper.selectList(
                new QueryWrapper<UserRedPacket>().eq("user_id", userId).eq("status", 0)
        );
        return new JsonResult(1, "查询成功", list);
    }
}