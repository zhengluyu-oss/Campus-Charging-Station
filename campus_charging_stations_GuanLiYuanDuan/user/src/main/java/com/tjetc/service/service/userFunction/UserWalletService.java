package com.tjetc.service.service.userFunction;

import com.tjetc.common.JsonResult;

public interface UserWalletService {
    JsonResult getMyPoints(Integer userId);
    JsonResult getMyCoupons(Integer userId);
    JsonResult getMyRedPackets(Integer userId);
}