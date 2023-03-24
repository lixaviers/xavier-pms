package com.xavier.pms.convertor;

import com.alibaba.fastjson2.JSON;
import com.xavier.pms.dto.PostDto;
import com.xavier.pms.model.Post;
import com.xavier.pms.vo.PostVo;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 职位对象相互转换
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public abstract class PostConvertor {

    private static final BeanCopier beanCopierForPostVo = BeanCopier.create(Post.class, PostVo.class, false);
    private static final BeanCopier beanCopierForPost = BeanCopier.create(PostDto.class, Post.class, false);

    public static PostVo toPostVo(Post post) {
        if (Objects.isNull(post)) {
            return null;
        }
        PostVo postVo = new PostVo();
        beanCopierForPostVo.copy(post, postVo, null);
        postVo.setRoleIdList(JSON.parseArray(post.getRole(), Long.class));
        return postVo;
    }

    public static Post toPost(PostDto postDto) {
        Post post = new Post();
        beanCopierForPost.copy(postDto, post, null);
        post.setRole(JSON.toJSONString(postDto.getRoleIdList()));
        return post;
    }

    public static List<PostVo> toPostVoList(List<Post> postList) {
        if (Objects.isNull(postList) || postList.isEmpty()) {
            return new ArrayList<>();
        }
        List<PostVo> postInfoList = new ArrayList<PostVo>(postList.size());
        for (Post post : postList) {
            postInfoList.add(toPostVo(post));
        }
        return postInfoList;
    }

}
