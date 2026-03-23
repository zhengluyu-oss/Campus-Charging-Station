package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/points")
public class PointsController {
    @Autowired private PointsService pointsService;

    @GetMapping("/records")
    public JsonResult getRecords(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam(required = false) Long userId) {
        return pointsService.findRecordsPage(pageNum, pageSize, userId);
    }

    @PostMapping("/adjust")
    public JsonResult adjust(@RequestParam Long userId, @RequestParam Integer points, @RequestParam String reason) {
        return pointsService.adjustPoints(userId, points, reason);
    }
}