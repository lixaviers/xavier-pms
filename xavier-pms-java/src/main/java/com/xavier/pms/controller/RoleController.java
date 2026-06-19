package com.xavier.pms.controller;

import com.xavier.pms.dto.RoleDto;
import com.xavier.pms.dto.RoleQueryDto;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IRoleService;
import com.xavier.pms.vo.RoleVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
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
@Tag(name = "角色管理接口")
public class RoleController extends CommonController {

    @Resource
    private IRoleService roleService;

    @Operation(summary = "新增角色", description = "新增角色")
    @PutMapping("add")
    public Result add(@Validated @RequestBody RoleDto roleDto) {
        roleService.createRole(roleDto);
        return Result.ok();
    }

    @Operation(summary = "编辑角色", description = "编辑角色")
    @PutMapping("update")
    public Result update(@Validated @RequestBody RoleDto roleDto) {
        roleService.updateRole(roleDto);
        return Result.ok();
    }

    @Operation(summary = "删除角色", description = "删除角色")
    @DeleteMapping("delete")
    public Result<Integer> delete(@RequestBody List<Long> idList) {
        roleService.deleteRole(idList);
        return Result.ok();
    }

    @Operation(summary = "获取角色信息", description = "根据角色ID获取角色信息")
    @GetMapping("get/{id}")
    public Result<RoleVo> get(@Parameter(description = "id") @PathVariable Long id) {
        return Result.ok(roleService.getRole(id));
    }

    @Operation(summary = "分页获取角色列表信息", description = "分页获取角色列表信息")
    @PostMapping("query")
    public Result<QueryResultVo<RoleVo>> queryRole(@Validated @RequestBody RoleQueryDto roleQueryDto) {
        return Result.ok(roleService.queryRole(roleQueryDto));
    }

}
