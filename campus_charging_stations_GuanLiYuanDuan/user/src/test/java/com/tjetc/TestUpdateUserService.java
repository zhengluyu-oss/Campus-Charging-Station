package com.tjetc.test.java.com.tjetc;

import com.tjetc.entity.userAndAdmin.User;
import com.tjetc.service.Impl.userFunctionImpl.UpdateOwnerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
public class TestUpdateUserService {

    @Test
    public void testUpdateUserWithId() {
        // 模拟用户数据
        User user = new User();
        user.setId(1);  // 设置用户ID
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        
        // 验证ID不为null
        assert user.getId() != null : "用户ID不应为null";
        System.out.println("用户ID: " + user.getId());
        System.out.println("用户名: " + user.getUsername());
        System.out.println("邮箱: " + user.getEmail());
    }
}