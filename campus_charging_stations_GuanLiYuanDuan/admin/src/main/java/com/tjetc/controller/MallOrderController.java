package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.service.MallOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/mall/order")
public class MallOrderController {
    @Autowired private MallOrderService mallOrderService;

    @GetMapping("/page")
    public JsonResult page(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                           @RequestParam(required = false) String orderNo,
                           @RequestParam(required = false) Integer status) {
        return mallOrderService.findPage(pageNum, pageSize, orderNo, status);
    }

    @GetMapping("/detail/{id}")
    public JsonResult detail(@PathVariable Long id) {
        return mallOrderService.getDetail(id);
    }

    @PutMapping("/deliver/{id}")
    public JsonResult deliver(@PathVariable Long id) {
        return mallOrderService.deliverOrder(id);
    }
}