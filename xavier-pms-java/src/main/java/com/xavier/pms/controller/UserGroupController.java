package com.xavier.pms.controller;

import com.xavier.pms.dto.UserGroupDto;
import com.xavier.pms.dto.UserGroupQueryDto;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IUserGroupService;
import com.xavier.pms.vo.UserGroupVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 请求处理层-员工组管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@RequestMapping("userGroup")
@RestController
@Tag(name = "员工组管理接口")
public class UserGroupController extends CommonController {

    @Resource
    private IUserGroupService userGroupService;

    @Operation(summary = "新增员工组", description = "新增员工组")
    @PutMapping("add")
    public Result add(@Validated @RequestBody UserGroupDto userGroupDto) {
        userGroupDto.setUserId(getLoginUser().getId());
        userGroupService.createUserGroup(userGroupDto);
        return Result.ok();
    }

    @Operation(summary = "编辑员工组", description = "编辑员工组")
    @PutMapping("update")
    public Result update(@Validated @RequestBody UserGroupDto userGroupDto) {
        userGroupService.updateUserGroup(userGroupDto);
        return Result.ok();
    }

    @Operation(summary = "删除员工组", description = "删除员工组")
    @DeleteMapping("delete")
    public Result delete(@RequestBody List<Long> idList) {
        userGroupService.deleteUserGroup(idList);
        return Result.ok();
    }

    @Operation(summary = "获取员工组信息", description = "根据员工组ID获取员工组信息")
    @GetMapping("get/{id}")
    public Result<UserGroupVo> get(@Parameter(description = "id") @PathVariable Long id) {
        return Result.ok(userGroupService.getUserGroup(id));
    }

    @Operation(summary = "分页获取员工组列表信息", description = "分页获取员工组列表信息")
    @PostMapping("query")
    public Result<QueryResultVo<UserGroupVo>> queryUserGroup(@Validated @RequestBody UserGroupQueryDto userGroupQueryDto) {
        userGroupQueryDto.setUserId(getLoginUser().getId());
        return Result.ok(userGroupService.queryUserGroup(userGroupQueryDto));
    }

}
