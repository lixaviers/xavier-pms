package com.xavier.pms.controller;

import com.xavier.pms.dto.EmployeeAddDto;
import com.xavier.pms.dto.EmployeeQueryDto;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IUserService;
import com.xavier.pms.vo.EmployeeCardVo;
import com.xavier.pms.vo.EmployeeListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * 请求处理层-用户管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@RequestMapping("user")
@RestController
@Api(tags = "用户管理接口")
public class UserController extends CommonController {

    @Resource
    private IUserService userService;

    @ApiOperation(value = "员工录入", notes = "员工录入")
    @PutMapping("add")
    public Result add(@Validated @RequestBody EmployeeAddDto userDto) {
        userService.createUser(userDto);
        return Result.ok();
    }

    @ApiOperation(value = "获取预计转正日期", notes = "获取预计转正日期")
    @GetMapping("getEstimatedConversionDate")
    public Result<LocalDate> getEstimatedConversionDate(@RequestParam LocalDate entryDate, @RequestParam int probationPeriod) {
        return Result.ok(entryDate.plusMonths(probationPeriod));
    }

    @ApiOperation(value = "分页获取员工列表信息", notes = "分页获取员工列表信息")
    @PostMapping("query")
    public Result<QueryResultVo<EmployeeListVo>> query(@Validated @RequestBody EmployeeQueryDto dto) {
        return Result.ok(userService.queryEmployee(dto));
    }

    @ApiOperation(value = "根据部门id查询员工列表信息", notes = "根据部门id查询员工列表信息")
    @GetMapping("getByDepartmentId/{departmentId}")
    public Result<List<EmployeeListVo>> getByDepartmentId(@ApiParam("部门id") @PathVariable Long departmentId) {
        return Result.ok(userService.getByDepartmentId(departmentId));
    }

    @ApiOperation(value = "根据id查询员工卡片信息", notes = "根据id查询员工卡片信息")
    @GetMapping("getCard/{id}")
    public Result<EmployeeCardVo> getCard(@ApiParam("id") @PathVariable Long id) {
        return Result.ok(userService.getCard(id));
    }


}
