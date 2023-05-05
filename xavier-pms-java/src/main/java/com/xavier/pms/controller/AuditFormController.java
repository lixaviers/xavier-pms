package com.xavier.pms.controller;

import com.xavier.pms.dto.AuditFormDto;
import com.xavier.pms.dto.AuditFormQueryDto;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IAuditFormFlowService;
import com.xavier.pms.service.IAuditFormService;
import com.xavier.pms.vo.AuditFormFlowVo;
import com.xavier.pms.vo.AuditFormVo;
import com.xavier.pms.vo.UserInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 请求处理层-审批单管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@RequestMapping("auditForm")
@RestController
@Api(tags = "审批单管理接口")
public class AuditFormController extends CommonController {

    @Resource
    private IAuditFormService auditFormService;
    @Resource
    private IAuditFormFlowService auditFormFlowService;

    @ApiOperation(value = "新增审批单", notes = "新增审批单")
    @PutMapping("add")
    public Result add(@Validated @RequestBody AuditFormDto auditFormDto) {
        UserInfoVo loginUser = getLoginUser();
        auditFormService.createAuditForm(loginUser, auditFormDto);
        return Result.ok();
    }

    @ApiOperation(value = "获取审批单信息", notes = "根据审批单ID获取审批单信息")
    @GetMapping("get/{id}")
    public Result<AuditFormVo> get(@ApiParam("id") @PathVariable Long id) {
        return Result.ok(auditFormService.getAuditForm(id));
    }

    @ApiOperation(value = "根据审批单id获取审批单流程信息", notes = "根据审批单id获取审批单流程信息")
    @GetMapping("getProcess/{id}")
    public Result<List<AuditFormFlowVo>> getProcess(@ApiParam("id") @PathVariable Long id) {
        return Result.ok(auditFormFlowService.getProcessByAuditFormId(id));
    }

    @ApiOperation(value = "分页获取审批单列表信息", notes = "分页获取审批单列表信息")
    @PostMapping("query")
    public Result<QueryResultVo<AuditFormVo>> queryAuditForm(@Validated @RequestBody AuditFormQueryDto auditFormQueryDto) {
        auditFormQueryDto.setUserId(getLoginUser().getId());
        return Result.ok(auditFormService.queryAuditForm(auditFormQueryDto));
    }

}
