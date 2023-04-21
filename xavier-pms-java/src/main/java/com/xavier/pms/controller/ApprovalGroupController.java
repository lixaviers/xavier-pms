package com.xavier.pms.controller;

import com.xavier.pms.dto.ApprovalGroupDto;
import com.xavier.pms.dto.ApprovalGroupQueryDto;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IApprovalGroupService;
import com.xavier.pms.vo.ApprovalGroupVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 请求处理层-审批分组管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@RequestMapping("approvalGroup")
@RestController
@Api(tags = "审批分组管理接口")
public class ApprovalGroupController extends CommonController {

    @Resource
    private IApprovalGroupService approvalGroupService;

    @ApiOperation(value = "新增审批分组", notes = "新增审批分组")
    @PutMapping("add")
    public Result add(@Validated @RequestBody ApprovalGroupDto approvalGroupDto) {
        approvalGroupService.createApprovalGroup(approvalGroupDto);
        return Result.ok();
    }

    @ApiOperation(value = "编辑审批分组", notes = "编辑审批分组")
    @PutMapping("update")
    public Result update(@Validated @RequestBody ApprovalGroupDto approvalGroupDto) {
        approvalGroupService.updateApprovalGroup(approvalGroupDto);
        return Result.ok();
    }

    @ApiOperation(value = "删除审批分组", notes = "删除审批分组")
    @DeleteMapping("delete/{id}")
    public Result<Boolean> delete(@ApiParam("id") @PathVariable Long id) {
        approvalGroupService.deleteApprovalGroup(id);
        return Result.ok();
    }

    @ApiOperation(value = "获取审批分组信息", notes = "根据审批分组id获取审批分组信息")
    @GetMapping("get/{id}")
    public Result<ApprovalGroupVo> get(@ApiParam("id") @PathVariable Long id) {
        return Result.ok(approvalGroupService.getApprovalGroup(id));
    }

    @ApiOperation(value = "分页获取审批分组列表信息", notes = "分页获取审批分组列表信息")
    @PostMapping("query")
    public Result<List<ApprovalGroupVo>> queryApprovalGroup(@Validated @RequestBody ApprovalGroupQueryDto approvalGroupQueryDto) {
        return Result.ok(approvalGroupService.queryApprovalGroup(approvalGroupQueryDto));
    }

}
