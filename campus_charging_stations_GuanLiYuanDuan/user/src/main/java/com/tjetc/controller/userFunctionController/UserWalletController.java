package com.tjetc.controller.userFunctionController;

import com.tjetc.common.JsonResult;
import com.tjetc.service.service.userFunction.UserWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/wallet")
public class UserWalletController {

    @Autowired
    private UserWalletService userWalletService;

    @GetMapping("/points")
    public JsonResult getPoints(@RequestParam Integer userId) {
        return userWalletService.getMyPoints(userId);
    }

    @GetMapping("/coupons")
    public JsonResult getCoupons(@RequestParam Integer userId) {
        return userWalletService.getMyCoupons(userId);
    }

    @GetMapping("/red-packets")
    public JsonResult getRedPackets(@RequestParam Integer userId) {
        return userWalletService.getMyRedPackets(userId);
    }
}