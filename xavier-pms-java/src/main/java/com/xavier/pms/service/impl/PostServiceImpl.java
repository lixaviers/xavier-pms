package com.xavier.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.convertor.PostConvertor;
import com.xavier.pms.dao.PostMapper;
import com.xavier.pms.dto.PostDto;
import com.xavier.pms.dto.PostQueryDto;
import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.model.Post;
import com.xavier.pms.model.User;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.service.IPostService;
import com.xavier.pms.service.IUserService;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.vo.PostVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


/**
 * 业务逻辑层-职位实现类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@Service("postService")
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

    @Resource
    private IUserService userService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Boolean deletePost(List<Long> idList) {
        // 判断是否有员工
        if (userService.count(User.gw().in(User::getPostId, idList)) > 0) {
            throw new ServiceException("职位下有员工，无法删除");
        }
        return super.removeBatchByIds(idList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Long createPost(PostDto postDto) {
        Post post = PostConvertor.toPost(postDto);
        post.setId(null);
        super.save(post);
        return post.getId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Boolean updatePost(PostDto postDto) {
        getBasePost(postDto.getId());
        Post post = PostConvertor.toPost(postDto);
        return super.updateById(post);
    }

    @Override
    public PostVo getPost(Long id) {
        return PostConvertor.toPostVo(getBasePost(id));
    }


    @Override
    public Post getBasePost(Long id) {
        Post post = super.getById(id);
        if (Objects.isNull(post) || post.getIsDeleted()) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "职位");
        }
        return post;
    }

    @Override
    public QueryResultVo<PostVo> queryPost(PostQueryDto queryDTO) {
        Page<Post> page = new Page<>();
        page.setCurrent(queryDTO.getPageNo());
        page.setSize(queryDTO.getPageSize());
        LambdaQueryWrapper<Post> wrapper = Post.gw();
        if (StrUtil.isNotBlank(queryDTO.getPostName())) {
            // 职位名称不为空
            wrapper.like(Post::getPostName, queryDTO.getPostName());
        }
        if (Objects.nonNull(queryDTO.getCreateTimeFrom())) {
            // 创建时间大于等于
            wrapper.ge(Post::getCreateTime, queryDTO.getCreateTimeFrom());
        }
        if (Objects.nonNull(queryDTO.getCreateTimeTo())) {
            // 创建时间小于等于
            wrapper.le(Post::getCreateTime, queryDTO.getCreateTimeTo());
        }
        wrapper.orderByAsc(Post::getId);
        Page<Post> result = super.page(page, wrapper);
        QueryResultVo<PostVo> queryResultVo = BeanUtil.pageToQueryResultVo(result, PostVo.class);
        queryResultVo.setRecords(PostConvertor.toPostVoList(result.getRecords()));
        return queryResultVo;
    }

}
