package com.xavier.pms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.xavier.pms.dto.CarDto;
import com.xavier.pms.dto.CarQueryDto;
import com.xavier.pms.vo.CarVo;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.ICarService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 请求处理层-车辆管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@RequestMapping("car")
@RestController
@Tag(name = "车辆管理接口")
public class CarController extends CommonController {

    @Resource
    private ICarService carService;

    @Operation(summary = "新增车辆", description = "新增车辆")
    @PutMapping("add")
    public Result add(@Validated @RequestBody CarDto carDto) {
        carService.createCar(carDto);
        return Result.ok();
    }

    @Operation(summary = "编辑车辆", description = "编辑车辆")
    @PutMapping("update")
    public Result update(@Validated @RequestBody CarDto carDto) {
        carService.updateCar(carDto);
        return Result.ok();
    }

    @Operation(summary = "删除车辆", description = "删除车辆")
    @DeleteMapping("delete")
    public Result<Boolean> delete(@RequestBody List<Long> idList) {
        carService.deleteCar(idList);
        return Result.ok();
    }

    @Operation(summary = "获取车辆信息", description = "根据车辆ID获取车辆信息")
    @GetMapping("get/{id}")
    public Result<CarVo> get(@Parameter(description = "id") @PathVariable Long id) {
        return Result.ok(carService.getCar(id));
    }

    @Operation(summary = "分页获取车辆列表信息", description = "分页获取车辆列表信息")
    @PostMapping("query")
    public Result<QueryResultVo<CarVo>> queryCar(@Validated @RequestBody CarQueryDto carQueryDto) {
        return Result.ok(carService.queryCar(carQueryDto));
    }

}
