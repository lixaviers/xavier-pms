package com.xavier.pms.controller;

import com.xavier.pms.dto.DepartmentDto;
import com.xavier.pms.dto.DepartmentQueryDto;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IDepartmentService;
import com.xavier.pms.vo.DepartmentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 请求处理层-部门管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@RequestMapping("department")
@RestController
@Api(tags = "部门管理接口")
public class DepartmentController extends CommonController {

    @Resource
    private IDepartmentService departmentService;

    @ApiOperation(value = "新增部门", notes = "新增部门")
    @PutMapping("add")
    public Result add(@Validated @RequestBody DepartmentDto departmentDto) {
        departmentService.createDepartment(departmentDto);
        return Result.ok();
    }

    @ApiOperation(value = "编辑部门", notes = "编辑部门")
    @PutMapping("update")
    public Result update(@Validated @RequestBody DepartmentDto departmentDto) {
        departmentService.updateDepartment(departmentDto);
        return Result.ok();
    }

    @ApiOperation(value = "删除部门", notes = "删除部门")
    @DeleteMapping("delete/{id}")
    public Result<Integer> delete(@ApiParam("id") @PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return Result.ok();
    }

    @ApiOperation(value = "获取部门信息", notes = "根据部门ID获取部门信息")
    @GetMapping("get/{id}")
    public Result<DepartmentVo> get(@ApiParam("id") @PathVariable Long id) {
        return Result.ok(departmentService.getDepartment(id));
    }

    @ApiOperation(value = "分页获取部门列表信息", notes = "分页获取部门列表信息")
    @PostMapping("query")
    public Result<QueryResultVo<DepartmentVo>> queryDepartment(@Validated @RequestBody DepartmentQueryDto departmentQueryDto) {
        return Result.ok(departmentService.queryDepartment(departmentQueryDto));
    }

}
