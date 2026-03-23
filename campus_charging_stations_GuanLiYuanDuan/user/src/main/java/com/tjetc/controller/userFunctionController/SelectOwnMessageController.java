package com.tjetc.controller.userFunctionController;

import com.tjetc.common.JsonResult;
import com.tjetc.service.Impl.userFunctionImpl.SelectOwnMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class SelectOwnMessageController {
    @Autowired
    private SelectOwnMessageService selectOwnMessageService;

    @RequestMapping("/selectOwnMessage")
    public JsonResult selectOwnMessage(@RequestParam("username") String  ownUsername) {
        return selectOwnMessageService.selectOwnService(ownUsername);
    }
}
