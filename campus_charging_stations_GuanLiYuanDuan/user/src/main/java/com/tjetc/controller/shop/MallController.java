package com.tjetc.controller.shop;

import com.tjetc.common.JsonResult;
import com.tjetc.service.service.shop.MallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/mall")
public class MallController {

    @Autowired
    private MallService mallService;

    /**
     * 下单接口
     * 前端需传：userId, productId, productName, price, quantity, address
     */
    @PostMapping("/buy")
    public JsonResult buy(@RequestParam Long userId,
                          @RequestParam Long productId,
                          @RequestParam String productName,
                          @RequestParam(required = false) String productPic,
                          @RequestParam Integer price,
                          @RequestParam Integer quantity,
                          @RequestParam String address) {

        return mallService.createOrder(userId, productId, productName, productPic, price, quantity, address);
    }

    @GetMapping("/orders")
    public JsonResult myOrders(@RequestParam Long userId) {
        return mallService.getUserOrders(userId);
    }

    @GetMapping("/detail/{orderId}")
    public JsonResult detail(@PathVariable Long orderId) {
        return mallService.getOrderDetail(orderId);
    }

    @PostMapping("/cancel")
    public JsonResult cancel(@RequestParam Long orderId) {
        return mallService.cancelOrder(orderId);
    }
}