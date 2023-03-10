package com.xavier.pms.controller;

import com.xavier.pms.dto.EmployeeAddDto;
import com.xavier.pms.dto.QueryApprovalDto;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IUserService;
import com.xavier.pms.vo.ApprovalEmployeeVo;
import com.xavier.pms.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @ApiOperation(value = "分页获取待审核员工列表信息", notes = "分页获取待审核员工列表信息")
    @PostMapping("queryApproval")
    public Result<QueryResultVo<ApprovalEmployeeVo>> queryApproval(@Validated @RequestBody QueryApprovalDto dto) {
        return Result.ok(userService.queryApproval(dto));
    }

    @ApiOperation(value = "审批通过", notes = "审批通过")
    @PostMapping("approval")
    public Result approval(@RequestBody List<Long> idList) {
        userService.approval(idList);
        return Result.ok();
    }

    @ApiOperation(value = "获取用户信息", notes = "根据用户ID获取用户信息")
    @GetMapping("get/{id}")
    public Result<UserVo> get(@ApiParam("id") @PathVariable Long id) {
        return Result.ok(userService.getUser(id));
    }


}
