package com.tjetc.service.service.userFunction;

import com.tjetc.common.JsonResult;

public interface UserLoginService {
    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    JsonResult login(String username, String password);


}
