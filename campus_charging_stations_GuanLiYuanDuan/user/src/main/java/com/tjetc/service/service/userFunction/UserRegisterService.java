package com.tjetc.service.service.userFunction;

import com.tjetc.common.JsonResult;
import com.tjetc.enums.core.user.UserType;

public interface UserRegisterService {
    JsonResult register(String username, String password, String avatarPath, String email, String phone, UserType userType);
}
