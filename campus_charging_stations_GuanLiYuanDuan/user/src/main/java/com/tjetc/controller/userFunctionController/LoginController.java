package com.tjetc.controller.userFunctionController;

import com.tjetc.common.JsonResult;
import com.tjetc.service.service.userFunction.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@Slf4j
public class LoginController {
    @Autowired
    private UserLoginService userService;

    //    用户登录
    @RequestMapping("/login")
    public JsonResult login(@RequestParam("username") String username,
                            @RequestParam("password") String password) {
        return userService.login(username, password);
    }

//    用户注册
    //接收json的数据格式
//    @PostMapping("register")
//    public JsonResult register(@RequestBody User user) {
//        log.info(user.toString());
//        return JsonResult.success("注册成功");
//    }
}
