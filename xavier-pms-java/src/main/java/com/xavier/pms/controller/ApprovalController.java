package com.xavier.pms.controller;

import com.xavier.pms.dto.ApprovalDto;
import com.xavier.pms.dto.ApprovalStatusDto;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IApprovalService;
import com.xavier.pms.vo.ApprovalDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
@Api(tags = "审批管理接口")
public class ApprovalController extends CommonController {

    @Resource
    private IApprovalService approvalService;

    @ApiOperation(value = "新增审批", notes = "新增审批")
    @PutMapping("add")
    public Result add(@Validated @RequestBody ApprovalDto dto) {
        approvalService.createApproval(dto);
        return Result.ok();
    }

    @ApiOperation(value = "编辑审批", notes = "编辑审批")
    @PutMapping("update")
    public Result update(@Validated @RequestBody ApprovalDto dto) {
        approvalService.updateApproval(dto);
        return Result.ok();
    }

    @ApiOperation(value = "删除审批", notes = "删除审批")
    @DeleteMapping("delete/{id}")
    public Result<Boolean> delete(@ApiParam("id") @PathVariable Long id) {
        approvalService.deleteApproval(id);
        return Result.ok();
    }

    @ApiOperation(value = "修改状态", notes = "修改状态")
    @PostMapping("updateStatus")
    public Result<Boolean> updateStatus(@Validated @RequestBody ApprovalStatusDto dto) {
        approvalService.updateStatus(dto);
        return Result.ok();
    }

    @ApiOperation(value = "获取审批信息", notes = "根据审批ID获取审批信息")
    @GetMapping("get/{id}")
    public Result<ApprovalDetailVo> get(@ApiParam("id") @PathVariable Long id) {
        return Result.ok(approvalService.getApproval(id));
    }

}
