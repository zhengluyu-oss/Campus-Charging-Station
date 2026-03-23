package com.tjetc.service.Impl.userFunctionImpl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.tjetc.common.JsonResult;
import com.tjetc.dao.UserMapper;
import com.tjetc.entity.userAndAdmin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateOwnerService implements com.tjetc.service.service.userFunction.UpdateOwnerService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public JsonResult updateOwner(User user) {
        // 检查用户ID是否为空
        if (user.getId() == null) {
            return JsonResult.fail("用户ID不能为空,对吧!");
        }

        // 使用条件更新，只更新非空字段
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId, user.getId());

        // 只对非空字段进行更新设置
        if (user.getUsername() != null) {
            updateWrapper.set(User::getUsername, user.getUsername());
        }
        if (user.getPassword() != null) {
            updateWrapper.set(User::getPassword, user.getPassword());
        }
        if (user.getEmail() != null) {
            updateWrapper.set(User::getEmail, user.getEmail());
        }
        if (user.getPhone() != null) {
            updateWrapper.set(User::getPhone, user.getPhone());
        }
        if (user.getUserType() != null) {
            updateWrapper.set(User::getUserType, user.getUserType());
        }
        if (user.getAvatarPath() != null) {
            updateWrapper.set(User::getAvatarPath, user.getAvatarPath());
        }

        // 更新时间戳
        updateWrapper.set(User::getUpdatedTime, java.time.LocalDateTime.now());

        int result = userMapper.update(null, updateWrapper);
        return JsonResult.success(result);
    }
}
