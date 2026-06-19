package com.xavier.pms.controller;

import com.xavier.pms.dto.MenuDto;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IMenuService;
import com.xavier.pms.vo.MenuVo;
import com.xavier.pms.vo.RouterVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 请求处理层-菜单管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@RequestMapping("menu")
@RestController
@Tag(name = "菜单管理接口")
public class MenuController extends CommonController {

    @Resource
    private IMenuService menuService;

    @Operation(summary = "获取当前登录用户的菜单", description = "获取当前登录用户的菜单")
    @GetMapping("getUserMenu")
    public Result<List<RouterVo>> getUserMenu() {
        return Result.ok(menuService.getUserMenu(getLoginUser()));
    }

    @Operation(summary = "获取所有菜单树", description = "获取所有菜单树")
    @GetMapping("getTree")
    public Result<List<MenuVo>> getTree() {
        return Result.ok(menuService.getTree(getLoginUser()));
    }

    @Operation(summary = "新增菜单", description = "新增菜单")
    @PutMapping("add")
    public Result add(@Validated @RequestBody MenuDto menuDto) {
        menuService.createMenu(menuDto);
        return Result.ok();
    }

    @Operation(summary = "编辑菜单", description = "编辑菜单")
    @PutMapping("update")
    public Result update(@Validated @RequestBody MenuDto menuDto) {
        menuService.updateMenu(menuDto);
        return Result.ok();
    }

    @Operation(summary = "删除菜单", description = "删除菜单")
    @DeleteMapping("delete/{id}")
    public Result<Integer> delete(@Parameter(description = "id") @PathVariable Long id) {
        menuService.deleteMenu(id);
        return Result.ok();
    }

    @Operation(summary = "获取菜单信息", description = "根据菜单ID获取菜单信息")
    @GetMapping("get/{id}")
    public Result<MenuVo> get(@Parameter(description = "id") @PathVariable Long id) {
        return Result.ok(menuService.getMenu(id));
    }


}
