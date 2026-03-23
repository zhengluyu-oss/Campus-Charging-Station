package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.core.ChargingStation;
import com.tjetc.service.ChargingStationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 充电桩控制层
 * 处理充电桩相关的HTTP请求，转发至Service层处理业务逻辑
 */
@RestController
@RequestMapping("/chargingStation") // 接口统一前缀
@Slf4j
public class ChargingStationController {

    // 注入充电桩业务层
    private final ChargingStationService chargingStationService;

    // 构造器注入（替代@Resource/@Autowired，更符合Spring规范）
    public ChargingStationController(ChargingStationService chargingStationService) {
        this.chargingStationService = chargingStationService;
    }

    /**
     * 查询所有充电桩
     * 请求方式：GET
     * 请求路径：/chargingStation/all
     */
    @GetMapping("/all")
    public JsonResult findAll() {
        log.info("接收【查询所有充电桩】请求");
        return chargingStationService.findAll();
    }

    /**
     * 分页查询充电桩（支持模糊查询）
     * 请求方式：GET
     * 请求路径：/chargingStation/page
     *
     * @param pageNum     当前页码，默认为1
     * @param pageSize    每页条数，默认为10
     * @param stationName 充电桩名称（模糊匹配关键词），可选
     */
    @GetMapping("/page")
    public JsonResult page(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, defaultValue = "") String stationName
    ) {
        log.info("接收【分页查询充电桩】请求，页码：{}，每页条数：{}，名称关键词：{}", pageNum, pageSize, stationName);
        return chargingStationService.findPage(pageNum, pageSize, stationName);
    }

    /**
     * 新增充电桩
     * 请求方式：POST
     * 请求路径：/chargingStation/add
     * 注意：去掉了 @RequestBody，允许接收表单格式数据
     *
     * @param chargingStation 充电桩实体
     */
    @PostMapping("/add")
    public JsonResult add(ChargingStation chargingStation) {
        log.info("接收【新增充电桩】请求：{}", chargingStation);
        return chargingStationService.add(chargingStation);
    }

    /**
     * 更新充电桩信息
     * 请求方式：PUT
     * 请求路径：/chargingStation/update
     * 注意：去掉了 @RequestBody，允许接收表单格式数据
     *
     * @param chargingStation 充电桩实体（需包含stationId）
     */
    @PutMapping("/update")
    public JsonResult updateById(ChargingStation chargingStation) {
        log.info("接收【更新充电桩】请求，ID：{}", chargingStation.getStationId());
        return chargingStationService.updateById(chargingStation);
    }

    /**
     * 删除充电桩
     * 请求方式：DELETE
     * 请求路径：/chargingStation/delete/{stationId}
     *
     * @param stationId 待删除的充电桩ID
     */
    @DeleteMapping("/delete/{stationId}")
    public JsonResult deleteById(@PathVariable("stationId") Integer stationId) {
        log.info("接收【删除充电桩】请求，ID：{}", stationId);
        return chargingStationService.deleteById(stationId);
    }

    /**
     * 查询充电桩详情
     * 请求方式：GET
     * 请求路径：/chargingStation/{stationId}
     *
     * @param stationId 充电桩主键ID
     */
    @GetMapping("/{stationId}")
    public JsonResult findById(@PathVariable("stationId") Integer stationId) {
        log.info("接收【查询充电桩详情】请求，ID：{}", stationId);
        return chargingStationService.findById(stationId);
    }
}