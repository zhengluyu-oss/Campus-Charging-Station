package com.tjetc.service.Impl.userFunctionImpl;

import com.tjetc.common.JsonResult;
import com.tjetc.dao.UserMapper;
import com.tjetc.entity.userAndAdmin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SelectOwnMessageService implements com.tjetc.service.service.userFunction.SelectOwnMessageService {
@Autowired
private UserMapper userMapper;
    @Override
    public JsonResult selectOwnService(String username) {
        User user = userMapper.selectByUsername(username);
        if (user == null){
            return JsonResult.fail("用户不存在");
        }
        return JsonResult.success(user);
    }
}
