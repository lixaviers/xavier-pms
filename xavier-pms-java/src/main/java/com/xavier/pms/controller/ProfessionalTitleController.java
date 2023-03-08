package com.xavier.pms.controller;

import com.xavier.pms.dto.ProfessionalTitleDto;
import com.xavier.pms.dto.ProfessionalTitleQueryDto;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IProfessionalTitleService;
import com.xavier.pms.vo.ProfessionalTitleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@Api(tags = "职称管理接口")
public class ProfessionalTitleController extends CommonController {

    @Resource
    private IProfessionalTitleService professionalTitleService;

    @ApiOperation(value = "新增职称", notes = "新增职称")
    @PutMapping("add")
    public Result add(@Validated @RequestBody ProfessionalTitleDto professionalTitleDto) {
        professionalTitleService.createProfessionalTitle(professionalTitleDto);
        return Result.ok();
    }

    @ApiOperation(value = "编辑职称", notes = "编辑职称")
    @PutMapping("update")
    public Result update(@Validated @RequestBody ProfessionalTitleDto professionalTitleDto) {
        professionalTitleService.updateProfessionalTitle(professionalTitleDto);
        return Result.ok();
    }

    @ApiOperation(value = "删除职称", notes = "删除职称")
    @DeleteMapping("delete")
    public Result<Integer> delete(@RequestBody List<Long> idList) {
        professionalTitleService.deleteProfessionalTitle(idList);
        return Result.ok();
    }

    @ApiOperation(value = "获取职称信息", notes = "根据职称ID获取职称信息")
    @GetMapping("get/{id}")
    public Result<ProfessionalTitleVo> get(@ApiParam("id") @PathVariable Long id) {
        return Result.ok(professionalTitleService.getProfessionalTitle(id));
    }

    @ApiOperation(value = "分页获取职称列表信息", notes = "分页获取职称列表信息")
    @PostMapping("query")
    public Result<QueryResultVo<ProfessionalTitleVo>> queryProfessionalTitle(@Validated @RequestBody ProfessionalTitleQueryDto professionalTitleQueryDto) {
        return Result.ok(professionalTitleService.queryProfessionalTitle(professionalTitleQueryDto));
    }

}
