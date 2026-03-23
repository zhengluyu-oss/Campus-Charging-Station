package com.tjetc.service;
import com.tjetc.common.JsonResult;
import com.tjetc.entity.marketing.RedPacket;


public interface RedPacketService {
    JsonResult add(RedPacket redPacket);
    JsonResult update(RedPacket redPacket);
    JsonResult delete(Integer id);
    JsonResult findPage(Integer pageNum, Integer pageSize, String name);
}