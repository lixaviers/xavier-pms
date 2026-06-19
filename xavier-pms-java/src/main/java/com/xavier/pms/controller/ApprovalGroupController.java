package com.xavier.pms.controller;

import com.xavier.pms.dto.ApprovalGroupDto;
import com.xavier.pms.dto.ApprovalGroupQueryDto;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IApprovalGroupService;
import com.xavier.pms.vo.ApprovalGroupVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
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
@Tag(name = "审批分组管理接口")
public class ApprovalGroupController extends CommonController {

    @Resource
    private IApprovalGroupService approvalGroupService;

    @Operation(summary = "获取生效的审批列表", description = "获取生效的审批列表，根据分组名称分组，应用于发起申请页面")
    @GetMapping("list")
    public Result<List<ApprovalGroupVo>> list() {
        return Result.ok(approvalGroupService.getList());
    }

    @Operation(summary = "新增审批分组", description = "新增审批分组")
    @PutMapping("add")
    public Result add(@Validated @RequestBody ApprovalGroupDto approvalGroupDto) {
        approvalGroupService.createApprovalGroup(approvalGroupDto);
        return Result.ok();
    }

    @Operation(summary = "编辑审批分组", description = "编辑审批分组")
    @PutMapping("update")
    public Result update(@Validated @RequestBody ApprovalGroupDto approvalGroupDto) {
        approvalGroupService.updateApprovalGroup(approvalGroupDto);
        return Result.ok();
    }

    @Operation(summary = "删除审批分组", description = "删除审批分组")
    @DeleteMapping("delete/{id}")
    public Result<Boolean> delete(@Parameter(description = "id") @PathVariable Long id) {
        approvalGroupService.deleteApprovalGroup(id);
        return Result.ok();
    }

    @Operation(summary = "获取审批分组信息", description = "根据审批分组id获取审批分组信息")
    @GetMapping("get/{id}")
    public Result<ApprovalGroupVo> get(@Parameter(description = "id") @PathVariable Long id) {
        return Result.ok(approvalGroupService.getApprovalGroup(id));
    }

    @Operation(summary = "分页获取审批分组列表信息", description = "分页获取审批分组列表信息")
    @PostMapping("query")
    public Result<List<ApprovalGroupVo>> queryApprovalGroup(@Validated @RequestBody ApprovalGroupQueryDto approvalGroupQueryDto) {
        return Result.ok(approvalGroupService.queryApprovalGroup(approvalGroupQueryDto));
    }

}
