package com.xavier.pms.controller;

import com.xavier.pms.dto.ApprovalDto;
import com.xavier.pms.dto.ApprovalStatusDto;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IApprovalService;
import com.xavier.pms.vo.ApprovalDetailVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

/**
 * 请求处理层-审批管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@RequestMapping("approval")
@RestController
@Tag(name = "审批管理接口")
public class ApprovalController extends CommonController {

    @Resource
    private IApprovalService approvalService;

    @Operation(summary = "新增审批", description = "新增审批")
    @PutMapping("add")
    public Result add(@Validated @RequestBody ApprovalDto dto) {
        approvalService.createApproval(dto);
        return Result.ok();
    }

    @Operation(summary = "编辑审批", description = "编辑审批")
    @PutMapping("update")
    public Result update(@Validated @RequestBody ApprovalDto dto) {
        approvalService.updateApproval(dto);
        return Result.ok();
    }

    @Operation(summary = "删除审批", description = "删除审批")
    @DeleteMapping("delete/{id}")
    public Result<Boolean> delete(@Parameter(description = "id") @PathVariable Long id) {
        approvalService.deleteApproval(id);
        return Result.ok();
    }

    @Operation(summary = "修改状态", description = "修改状态")
    @PostMapping("updateStatus")
    public Result<Boolean> updateStatus(@Validated @RequestBody ApprovalStatusDto dto) {
        approvalService.updateStatus(dto);
        return Result.ok();
    }

    @Operation(summary = "获取审批信息", description = "根据审批ID获取审批信息")
    @GetMapping("get/{id}")
    public Result<ApprovalDetailVo> get(@Parameter(description = "id") @PathVariable Long id) {
        return Result.ok(approvalService.getApproval(id));
    }

//    @Operation(summary = "获取审批流程信息", description = "根据审批ID获取审批流程信息")
//    @GetMapping("getApprovalProcess/{approvalId}")
//    public Result<UserApprovalVo> getApprovalProcess(@Parameter(description = "审批id") @PathVariable Long approvalId) {
//        return Result.ok(userApprovalFlowService.getApprovalProcess(approvalId));
//    }

}
