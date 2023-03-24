package com.xavier.pms.controller;

import com.xavier.pms.dto.RoleDto;
import com.xavier.pms.dto.RoleQueryDto;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IRoleService;
import com.xavier.pms.vo.RoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 请求处理层-角色管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@RequestMapping("role")
@RestController
@Api(tags = "角色管理接口")
public class RoleController extends CommonController {

    @Resource
    private IRoleService roleService;

    @ApiOperation(value = "新增角色", notes = "新增角色")
    @PutMapping("add")
    public Result add(@Validated @RequestBody RoleDto roleDto) {
        roleService.createRole(roleDto);
        return Result.ok();
    }

    @ApiOperation(value = "编辑角色", notes = "编辑角色")
    @PutMapping("update")
    public Result update(@Validated @RequestBody RoleDto roleDto) {
        roleService.updateRole(roleDto);
        return Result.ok();
    }

    @ApiOperation(value = "删除角色", notes = "删除角色")
    @DeleteMapping("delete")
    public Result<Integer> delete(@RequestBody List<Long> idList) {
        roleService.deleteRole(idList);
        return Result.ok();
    }

    @ApiOperation(value = "获取角色信息", notes = "根据角色ID获取角色信息")
    @GetMapping("get/{id}")
    public Result<RoleVo> get(@ApiParam("id") @PathVariable Long id) {
        return Result.ok(roleService.getRole(id));
    }

    @ApiOperation(value = "分页获取角色列表信息", notes = "分页获取角色列表信息")
    @PostMapping("query")
    public Result<QueryResultVo<RoleVo>> queryRole(@Validated @RequestBody RoleQueryDto roleQueryDto) {
        return Result.ok(roleService.queryRole(roleQueryDto));
    }

}
