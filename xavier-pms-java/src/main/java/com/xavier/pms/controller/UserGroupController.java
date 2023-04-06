package com.xavier.pms.controller;

import com.xavier.pms.dto.UserGroupDto;
import com.xavier.pms.dto.UserGroupQueryDto;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IUserGroupService;
import com.xavier.pms.vo.UserGroupVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@Api(tags = "员工组管理接口")
public class UserGroupController extends CommonController {

    @Resource
    private IUserGroupService userGroupService;

    @ApiOperation(value = "新增员工组", notes = "新增员工组")
    @PutMapping("add")
    public Result add(@Validated @RequestBody UserGroupDto userGroupDto) {
        userGroupDto.setUserId(getLoginUser().getId());
        userGroupService.createUserGroup(userGroupDto);
        return Result.ok();
    }

    @ApiOperation(value = "编辑员工组", notes = "编辑员工组")
    @PutMapping("update")
    public Result update(@Validated @RequestBody UserGroupDto userGroupDto) {
        userGroupService.updateUserGroup(userGroupDto);
        return Result.ok();
    }

    @ApiOperation(value = "删除员工组", notes = "删除员工组")
    @DeleteMapping("delete")
    public Result delete(@RequestBody List<Long> idList) {
        userGroupService.deleteUserGroup(idList);
        return Result.ok();
    }

    @ApiOperation(value = "获取员工组信息", notes = "根据员工组ID获取员工组信息")
    @GetMapping("get/{id}")
    public Result<UserGroupVo> get(@ApiParam("id") @PathVariable Long id) {
        return Result.ok(userGroupService.getUserGroup(id));
    }

    @ApiOperation(value = "分页获取员工组列表信息", notes = "分页获取员工组列表信息")
    @PostMapping("query")
    public Result<QueryResultVo<UserGroupVo>> queryUserGroup(@Validated @RequestBody UserGroupQueryDto userGroupQueryDto) {
        userGroupQueryDto.setUserId(getLoginUser().getId());
        return Result.ok(userGroupService.queryUserGroup(userGroupQueryDto));
    }

}
