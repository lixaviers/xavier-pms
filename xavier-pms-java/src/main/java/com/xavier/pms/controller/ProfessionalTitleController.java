package com.xavier.pms.controller;

import com.xavier.pms.dto.ProfessionalTitleDto;
import com.xavier.pms.dto.ProfessionalTitleQueryDto;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IProfessionalTitleService;
import com.xavier.pms.vo.ProfessionalTitleVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 请求处理层-职称管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@RequestMapping("professionalTitle")
@RestController
@Tag(name = "职称管理接口")
public class ProfessionalTitleController extends CommonController {

    @Resource
    private IProfessionalTitleService professionalTitleService;

    @Operation(summary = "新增职称", description = "新增职称")
    @PutMapping("add")
    public Result add(@Validated @RequestBody ProfessionalTitleDto professionalTitleDto) {
        professionalTitleService.createProfessionalTitle(professionalTitleDto);
        return Result.ok();
    }

    @Operation(summary = "编辑职称", description = "编辑职称")
    @PutMapping("update")
    public Result update(@Validated @RequestBody ProfessionalTitleDto professionalTitleDto) {
        professionalTitleService.updateProfessionalTitle(professionalTitleDto);
        return Result.ok();
    }

    @Operation(summary = "删除职称", description = "删除职称")
    @DeleteMapping("delete")
    public Result<Integer> delete(@RequestBody List<Long> idList) {
        professionalTitleService.deleteProfessionalTitle(idList);
        return Result.ok();
    }

    @Operation(summary = "获取职称信息", description = "根据职称ID获取职称信息")
    @GetMapping("get/{id}")
    public Result<ProfessionalTitleVo> get(@Parameter(description = "id") @PathVariable Long id) {
        return Result.ok(professionalTitleService.getProfessionalTitle(id));
    }

    @Operation(summary = "分页获取职称列表信息", description = "分页获取职称列表信息")
    @PostMapping("query")
    public Result<QueryResultVo<ProfessionalTitleVo>> queryProfessionalTitle(@Validated @RequestBody ProfessionalTitleQueryDto professionalTitleQueryDto) {
        return Result.ok(professionalTitleService.queryProfessionalTitle(professionalTitleQueryDto));
    }

}
