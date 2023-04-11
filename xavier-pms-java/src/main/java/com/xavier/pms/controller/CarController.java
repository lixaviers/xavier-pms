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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.annotation.Resource;
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
@Api(tags = "车辆管理接口")
public class CarController extends CommonController {

    @Resource
    private ICarService carService;

    @ApiOperation(value = "新增车辆", notes = "新增车辆")
    @PutMapping("add")
    public Result add(@Validated @RequestBody CarDto carDto) {
        carService.createCar(carDto);
        return Result.ok();
    }

    @ApiOperation(value = "编辑车辆", notes = "编辑车辆")
    @PutMapping("update")
    public Result update(@Validated @RequestBody CarDto carDto) {
        carService.updateCar(carDto);
        return Result.ok();
    }

    @ApiOperation(value = "删除车辆", notes = "删除车辆")
    @DeleteMapping("delete")
    public Result<Boolean> delete(@RequestBody List<Long> idList) {
        carService.deleteCar(idList);
        return Result.ok();
    }

    @ApiOperation(value = "获取车辆信息", notes = "根据车辆ID获取车辆信息")
    @GetMapping("get/{id}")
    public Result<CarVo> get(@ApiParam("id") @PathVariable Long id) {
        return Result.ok(carService.getCar(id));
    }

    @ApiOperation(value = "分页获取车辆列表信息", notes = "分页获取车辆列表信息")
    @PostMapping("query")
    public Result<QueryResultVo<CarVo>> queryCar(@Validated @RequestBody CarQueryDto carQueryDto) {
        return Result.ok(carService.queryCar(carQueryDto));
    }

}
