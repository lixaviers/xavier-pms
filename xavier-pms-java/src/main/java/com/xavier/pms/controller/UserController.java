package com.xavier.pms.controller;

import com.xavier.pms.dao.ChangePasswordDto;
import com.xavier.pms.dto.EmployeeAddDto;
import com.xavier.pms.dto.EmployeeQueryDto;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IUserService;
import com.xavier.pms.vo.EmployeeCardVo;
import com.xavier.pms.vo.EmployeeListVo;
import com.xavier.pms.vo.UserProfileVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
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
@Tag(name = "用户管理接口")
public class UserController extends CommonController {

    @Resource
    private IUserService userService;

    @Operation(summary = "员工录入", description = "员工录入")
    @PutMapping("add")
    public Result add(@Validated @RequestBody EmployeeAddDto userDto) {
        userService.createUser(userDto);
        return Result.ok();
    }

    @Operation(summary = "获取预计转正日期", description = "获取预计转正日期")
    @GetMapping("getEstimatedConversionDate")
    public Result<LocalDate> getEstimatedConversionDate(@RequestParam LocalDate entryDate, @RequestParam int probationPeriod) {
        return Result.ok(entryDate.plusMonths(probationPeriod));
    }

    @Operation(summary = "分页获取员工列表信息", description = "分页获取员工列表信息")
    @PostMapping("query")
    public Result<QueryResultVo<EmployeeListVo>> query(@Validated @RequestBody EmployeeQueryDto dto) {
        return Result.ok(userService.queryEmployee(dto));
    }

    @Operation(summary = "根据部门id查询员工列表信息", description = "根据部门id查询员工列表信息")
    @GetMapping("getByDepartmentId/{departmentId}")
    public Result<List<EmployeeListVo>> getByDepartmentId(@Parameter(description = "部门id") @PathVariable Long departmentId) {
        return Result.ok(userService.getByDepartmentId(departmentId));
    }

    @Operation(summary = "根据id查询员工卡片信息", description = "根据id查询员工卡片信息")
    @GetMapping("getCard/{id}")
    public Result<EmployeeCardVo> getCard(@Parameter(description = "id") @PathVariable Long id) {
        return Result.ok(userService.getCard(id));
    }

    @Operation(summary = "修改密码", description = "根据旧密码修改新密码")
    @PutMapping("changePassword")
    public Result<Void> changePassword(@Validated @RequestBody ChangePasswordDto dto) {
        Long userId = getLoginUser().getId();
        userService.changePassword(userId, dto.getOldPassword(), dto.getNewPassword());
        return Result.ok();
    }

    @Operation(summary = "获取个人详细信息", description = "获取当前登录用户的详细个人信息")
    @GetMapping("getProfile")
    public Result<UserProfileVo> getProfile() {
        Long userId = getLoginUser().getId();
        return Result.ok(userService.getUserProfile(userId));
    }


}
