package com.xavier.pms.controller;

import com.xavier.pms.dto.PositionChangeAddDto;
import com.xavier.pms.dto.PositionChangeQueryDto;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IPositionChangeService;
import com.xavier.pms.vo.PositionChangeVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

/**
 * 请求处理层-员工职位/职称变动管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@RequestMapping("positionChange")
@RestController
@Tag(name = "员工职位/职称变动管理接口")
public class PositionChangeController extends CommonController {

    @Resource
    private IPositionChangeService positionChangeService;

    @Operation(summary = "员工职位/职称变动", description = "办理员工职位或职称变动，同时更新员工信息")
    @PutMapping("add")
    public Result add(@Validated @RequestBody PositionChangeAddDto dto) {
        positionChangeService.change(dto);
        return Result.ok();
    }

    @Operation(summary = "分页查询变动记录", description = "分页查询员工职位/职称变动记录列表")
    @PostMapping("query")
    public Result<QueryResultVo<PositionChangeVo>> query(@Validated @RequestBody PositionChangeQueryDto dto) {
        return Result.ok(positionChangeService.queryPositionChange(dto));
    }

    @Operation(summary = "查询变动详情", description = "根据id查询变动记录详情")
    @GetMapping("get/{id}")
    public Result<PositionChangeVo> get(@Parameter(description = "id") @PathVariable Long id) {
        return Result.ok(positionChangeService.getPositionChangeDetail(id));
    }

}
