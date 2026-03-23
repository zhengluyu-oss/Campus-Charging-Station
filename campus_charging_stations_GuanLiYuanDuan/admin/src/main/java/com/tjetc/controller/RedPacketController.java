package com.tjetc.controller;

import com.tjetc.common.JsonResult;

import com.tjetc.entity.marketing.RedPacket;
import com.tjetc.service.RedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/red-packet")
public class RedPacketController {
    @Autowired private RedPacketService redPacketService;

    @PostMapping("/add")
    public JsonResult add(@RequestBody RedPacket redPacket) {
        return redPacketService.add(redPacket);
    }

    @PutMapping("/update")
    public JsonResult update(@RequestBody RedPacket redPacket) {
        return redPacketService.update(redPacket);
    }

    @DeleteMapping("/delete/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return redPacketService.delete(id);
    }

    @GetMapping("/page")
    public JsonResult page(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam(required = false) String name) {
        return redPacketService.findPage(pageNum, pageSize, name);
    }
}