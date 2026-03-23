package com.tjetc.controller.userFunctionController;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.userAndAdmin.User;
import com.tjetc.service.Impl.userFunctionImpl.UpdateOwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UpdateOwnMessageController {
    @Autowired
    private UpdateOwnerService updateOwnerService;

    @PostMapping(value = "user/updateOwnMessage", consumes = "application/x-www-form-urlencoded")
    public JsonResult updateOwnMessage(User user) {
        log.info("接收到更新请求，用户ID: {}", user.getId());
        return updateOwnerService.updateOwner(user);
    }
}
