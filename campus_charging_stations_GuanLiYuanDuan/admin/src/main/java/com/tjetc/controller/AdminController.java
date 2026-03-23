package com.tjetc.controller;

import com.tjetc.common.JsonResult;

import com.tjetc.entity.userAndAdmin.Admin;
import com.tjetc.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@Slf4j
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 管理员登录
     * 接口路径: /admin/admin/login (加上 context-path 后为 /admin/admin/admin/login，具体取决于您的 context-path 配置)
     * 如果 context-path 是 /admin，AdminController 是 /admin，方法是 /login，则访问路径为 /admin/admin/login
     */
    @PostMapping("login")
    public JsonResult login(@RequestParam("username") String username,
                            @RequestParam("password") String password) {
        log.info("管理员登录请求: username={}", username);
        return adminService.login(username, password);
    }


    /**
     * 查询管理员列表（无分页）
     * HTTP 请求方式：GET
     */
    @GetMapping("list") // 取代 @RequestMapping，明确 GET 请求
    public JsonResult list() {
        return adminService.findAll();
    }

    /**
     * 新增管理员
     * HTTP 请求方式：POST（新增操作推荐使用 POST）
     */
    @PostMapping("add") // 取代 @RequestMapping，明确 POST 请求
    public JsonResult add(Admin admin) {
        return adminService.add(admin);
    }

    /**
     * 管理员分页查询（带用户名模糊查询）
     * HTTP 请求方式：GET（查询操作推荐使用 GET）
     */
    @GetMapping("page") // 取代 @RequestMapping，明确 GET 请求
    public JsonResult page(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                           @RequestParam(value = "username", required = false, defaultValue = "") String username) {
        return adminService.findPage(pageNum, pageSize, username);
    }

    /**
     * 校验管理员用户名是否存在
     * HTTP 请求方式：GET（查询操作推荐使用 GET）
     */
    @GetMapping("check-exist") // 取代 @RequestMapping，明确 GET 请求
    public JsonResult checkExist(@RequestParam("username") String username) {
        return adminService.checkExistByUsername(username);
    }

    /**
     * 查询管理员详情（根据 ID）
     * HTTP 请求方式：GET（查询操作推荐使用 GET）
     */
    @GetMapping("detail/{id}") // 取代 @RequestMapping，明确 GET 请求
    public JsonResult detail(@PathVariable("id") Integer id) {
        return adminService.findById(id);
    }

    /**
     * 修改管理员信息
     * HTTP 请求方式：PUT（全量更新操作推荐使用 PUT）
     */
    @PutMapping("update") // 取代 @RequestMapping，明确 PUT 请求（配合 @RequestBody 接收 JSON）
    public JsonResult update(@RequestBody Admin admin) {
        return adminService.updateById(admin);
    }

    /**
     * 删除管理员（根据 ID）
     * HTTP 请求方式：DELETE（删除操作推荐使用 DELETE）
     */
    @DeleteMapping("delete/{id}") // 保留路径变量，明确 DELETE 请求（原 @GetMapping 替换为 @DeleteMapping）
    public JsonResult delete(@PathVariable("id") Integer id, HttpServletRequest request) {
        //request.getHeader("token")
        return adminService.deleteById(id);
    }
}