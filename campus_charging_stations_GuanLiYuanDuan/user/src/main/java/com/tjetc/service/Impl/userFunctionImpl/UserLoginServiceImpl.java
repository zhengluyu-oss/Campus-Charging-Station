package com.tjetc.service.Impl.userFunctionImpl;

import com.tjetc.common.JsonResult;
import com.tjetc.common.JwtTokenUtil;
import com.tjetc.dao.UserMapper;
import com.tjetc.entity.userAndAdmin.User;
import com.tjetc.service.service.userFunction.UserLoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    //token过期有效期
    @Value("${jwt.token.expired}")
    private int expired;

    @Autowired
    private UserMapper userMapper;

    @Override
    public JsonResult login(String username, String password) {
        //校验数据是否为null 空字符串 甚至长度都要校验
        JsonResult jsonResult = checkUsernameAndPassword(username, password);
        if (jsonResult.getState() != 0) {
            return jsonResult;
        }
        //查询
        User user = userMapper.selectByUsernameAndPassword(username.trim(), password.trim());
        if (user == null) {
            return JsonResult.fail("用户名或者密码错误");
        }
        user.setPassword(null);
        //生成token并且返回给前端
        Map<String, Object> info = new HashMap<>();
        //用户信息 和 用户权限字符串信息
        info.put("id", user.getId());
        info.put("username", user.getUsername());
        String token = JwtTokenUtil.generateToken(info, "user", expired);

        return JsonResult.success(token, user);
    }

    /**
     * 校验用户名和密码
     *
     * @param username
     * @param password
     * @return
     */
    private JsonResult checkUsernameAndPassword(String username, String password) {
        if (StringUtils.isAnyBlank(username, password)) {
            return JsonResult.fail("用户名或者密码不能为空");
        }
        //校验长度
        if (StringUtils.length(username) < 3 || StringUtils.length(username) > 20) {
            return JsonResult.fail("用户名长度是3~20字符");
        }
        if (StringUtils.length(password) < 3 || StringUtils.length(password) > 30) {
            return JsonResult.fail("密码长度是3~30字符");
        }
        return JsonResult.success("");
    }
}
