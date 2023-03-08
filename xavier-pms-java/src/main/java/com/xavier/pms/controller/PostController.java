package com.xavier.pms.controller;

import com.xavier.pms.dto.PostDto;
import com.xavier.pms.dto.PostQueryDto;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IPostService;
import com.xavier.pms.vo.PostVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 请求处理层-职位管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@RequestMapping("post")
@RestController
@Api(tags = "职位管理接口")
public class PostController extends CommonController {

    @Resource
    private IPostService postService;

    @ApiOperation(value = "新增职位", notes = "新增职位")
    @PutMapping("add")
    public Result add(@Validated @RequestBody PostDto postDto) {
        postService.createPost(postDto);
        return Result.ok();
    }

    @ApiOperation(value = "编辑职位", notes = "编辑职位")
    @PutMapping("update")
    public Result update(@Validated @RequestBody PostDto postDto) {
        postService.updatePost(postDto);
        return Result.ok();
    }

    @ApiOperation(value = "删除职位", notes = "删除职位")
    @DeleteMapping("delete")
    public Result<Integer> delete(@RequestBody List<Long> idList) {
        postService.deletePost(idList);
        return Result.ok();
    }

    @ApiOperation(value = "获取职位信息", notes = "根据职位ID获取职位信息")
    @GetMapping("get/{id}")
    public Result<PostVo> get(@ApiParam("id") @PathVariable Long id) {
        return Result.ok(postService.getPost(id));
    }

    @ApiOperation(value = "分页获取职位列表信息", notes = "分页获取职位列表信息")
    @PostMapping("query")
    public Result<QueryResultVo<PostVo>> queryPost(@Validated @RequestBody PostQueryDto postQueryDto) {
        return Result.ok(postService.queryPost(postQueryDto));
    }

}
