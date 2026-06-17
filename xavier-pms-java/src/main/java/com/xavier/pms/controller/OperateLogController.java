package com.xavier.pms.controller;

import com.xavier.pms.dto.OperateLogQueryDto;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IOperateLogService;
import com.xavier.pms.vo.OperateLogVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RequestMapping("operateLog")
@RestController
@Api(tags = "操作日志接口")
public class OperateLogController extends CommonController {

    @Resource
    private IOperateLogService operateLogService;

    @ApiOperation(value = "分页获取操作日志列表", notes = "分页获取操作日志列表")
    @PostMapping("query")
    public Result<QueryResultVo<OperateLogVo>> query(@Validated @RequestBody OperateLogQueryDto queryDTO) {
        return Result.ok(operateLogService.queryOperateLog(queryDTO));
    }

}
