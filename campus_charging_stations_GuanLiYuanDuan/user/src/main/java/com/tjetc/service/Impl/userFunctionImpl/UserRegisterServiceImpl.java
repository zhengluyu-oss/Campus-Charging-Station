package com.tjetc.service.Impl.userFunctionImpl;

import com.tjetc.common.JsonResult;
import com.tjetc.dao.UserMapper;
import com.tjetc.entity.userAndAdmin.User;
import com.tjetc.enums.core.user.UserType;
import com.tjetc.service.service.userFunction.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public JsonResult register(String username, String password, String avatarPath, String email, String phone, UserType userType) {
        userMapper.insert(new User(username, password, avatarPath, email, phone, userType));

        return JsonResult.success("注册成功");
    }
}
