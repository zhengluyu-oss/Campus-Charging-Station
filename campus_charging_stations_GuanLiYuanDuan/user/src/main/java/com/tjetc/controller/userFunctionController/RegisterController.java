package com.tjetc.controller.userFunctionController;

import com.tjetc.enums.core.user.UserType;
import com.tjetc.service.service.userFunction.UserRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.tjetc.enums.core.user.UserType.student;
import static com.tjetc.enums.core.user.UserType.teacher;

@RestController
@Slf4j
public class RegisterController {
    @Autowired
    private UserRegisterService userService;

//    @RequestMapping("/register")
//    public String register() {
//        log.info("开始注册用户");
//        userService.register("admin", "123456", "123456", "123456", "17330591344",student);
//        return "成功注册充电桩";
//    }
// 支持表单数据格式的注册请求
@PostMapping(value = "/register", consumes = "application/x-www-form-urlencoded")
public Object registerWithFormData(
        @RequestParam(required = false) String username,
        @RequestParam(required = false) String password,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) String phone,
        @RequestParam(required = false) String userType) {
    return handleRegistration(username, password, email, phone, userType);
}

    // 支持JSON格式的注册请求
    @PostMapping(value = "/register", consumes = "application/json")
    public Object registerWithJson(@RequestBody Map<String, String> requestData) {
        String username = requestData.get("username");
        String password = requestData.get("password");
        String email = requestData.get("email");
        String phone = requestData.get("phone");
        String userType = requestData.get("userType");

        return handleRegistration(username, password, email, phone, userType);
    }

    // 处理注册的核心逻辑
    private Object handleRegistration(String username, String password, String email, String phone, String userType) {
        log.info("开始注册用户: username={}, password={}, email={}, phone={}, userType={}",
                username, password != null ? "***" : null, email, phone, userType);

        // 检查必需参数是否为空或只包含空白字符
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            log.error("缺少必需参数或参数为空: username={}, password={}", username, password != null ? "***" : null);
            return "{\"state\": 1, \"message\": \"用户名和密码不能为空\"}";
        }

        // 清理用户名和密码的空白字符
        username = username.trim();
        password = password.trim();

        // 设置默认用户类型为 student，如果 userType 为空或无效
        UserType userTypeEnum = UserType.student; // 默认值

        if (userType != null && !userType.trim().isEmpty()) {
            try {
                userTypeEnum = UserType.valueOf(userType.trim());
            } catch (IllegalArgumentException e) {
                log.error("无效的用户类型: " + userType, e);
                return "{\"state\": 1, \"message\": \"无效的用户类型\"}";
            }
        }

        return userService.register(username, password, null, email, phone, userTypeEnum);
    }

}
