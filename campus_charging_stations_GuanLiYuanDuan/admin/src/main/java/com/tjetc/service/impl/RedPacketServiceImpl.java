package com.tjetc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.common.JsonResult;
import com.tjetc.dao.RedPacketMapper;

import com.tjetc.entity.marketing.RedPacket;
import com.tjetc.service.RedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedPacketServiceImpl implements RedPacketService {
    @Autowired private RedPacketMapper redPacketMapper;

    @Override
    public JsonResult add(RedPacket redPacket) {
        redPacketMapper.insert(redPacket);
        return new JsonResult(1, "创建成功");
    }

    @Override
    public JsonResult update(RedPacket redPacket) {
        redPacketMapper.updateById(redPacket);
        return new JsonResult(1, "更新成功");
    }

    @Override
    public JsonResult delete(Integer id) {
        redPacketMapper.deleteById(id);
        return new JsonResult(1, "删除成功");
    }

    @Override
    public JsonResult findPage(Integer pageNum, Integer pageSize, String name) {
        Page<RedPacket> page = new Page<>(pageNum, pageSize);
        QueryWrapper<RedPacket> qw = new QueryWrapper<>();
        if (name != null) qw.like("name", name);
        return new JsonResult(1, "查询成功", redPacketMapper.selectPage(page, qw));
    }
}