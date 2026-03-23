package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.userAndAdmin.User;
import com.tjetc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户模块控制器
 * 处理用户相关的HTTP请求，调用UserService完成业务逻辑
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     * GET /user/all
     * @return JsonResult - 所有用户列表（密码脱敏）
     */
    @GetMapping("/all")
    public JsonResult findAll() {
        log.info("接收查询所有用户请求");
        return userService.findAll();
    }

    /**
     * 新增用户
     * POST /user/add
     * @param user 待新增的用户信息（JSON格式）
     * @return JsonResult - 新增结果提示
     */
    @PostMapping("/add")
    public JsonResult add(@RequestBody User user) {
        log.info("接收新增用户请求，username：{}", user.getUsername());
        return userService.add(user);
    }

    /**
     * 按用户名模糊分页查询用户
     * GET /user/page
     * @param pageNum 页码（必填，>0）
     * @param pageSize 每页数量（必填，>0）
     * @param username 用户名（可选，模糊匹配）
     * @return JsonResult - 分页用户列表（密码脱敏）
     */
    @GetMapping("/page")
    public JsonResult findPage(
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize,
            @RequestParam(required = false) String username
    ) {
        log.info("接收分页查询用户请求，pageNum：{}，pageSize：{}，username：{}", pageNum, pageSize, username);
        return userService.findPage(pageNum, pageSize, username);
    }

    /**
     * 用户登录
     * POST /user/login
     * @param username 用户名（必填）
     * @param password 密码（必填）
     * @return JsonResult - 登录结果+脱敏用户信息
     */
    @PostMapping("/login")
    public JsonResult login(
            @RequestParam String username,
            @RequestParam String password
    ) {
        log.info("接收用户登录请求，username：{}", username);
        return userService.login(username, password);
    }

    /**
     * 检查用户名是否存在
     * GET /user/check-username
     * @param username 待检查的用户名（必填）
     * @return JsonResult - 存在返回true，不存在返回false
     */
    @GetMapping("/check-username")
    public JsonResult checkExistByUsername(@RequestParam String username) {
        log.info("接收检查用户名存在性请求，username：{}", username);
        return userService.checkExistByUsername(username);
    }

    /**
     * 按ID查询用户
     * GET /user/{id}
     * @param id 用户ID（路径变量，必填，>0）
     * @return JsonResult - 脱敏后的用户信息
     */
    @GetMapping("/{id}")
    public JsonResult findById(@PathVariable Integer id) {
        log.info("接收按ID查询用户请求，id：{}", id);
        return userService.findById(id);
    }

    /**
     * 按ID更新用户信息
     * PUT /user/update
     * @param user 待更新的用户信息（需包含ID，JSON格式）
     * @return JsonResult - 更新结果提示
     */
    @PutMapping("/update")
    public JsonResult updateById(@RequestBody User user) {
        log.info("接收更新用户信息请求，userId：{}", user.getId());
        return userService.updateById(user);
    }

    /**
     * 按ID删除用户
     * DELETE /user/delete/{id}
     * @param id 用户ID（路径变量，必填，>0）
     * @return JsonResult - 删除结果提示
     */
    @DeleteMapping("/delete/{id}")
    public JsonResult deleteById(@PathVariable Integer id) {
        log.info("接收删除用户请求，id：{}", id);
        return userService.deleteById(id);
    }
}