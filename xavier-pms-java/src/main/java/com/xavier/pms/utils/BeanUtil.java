package com.xavier.pms.utils;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xavier.pms.query.QueryResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class BeanUtil {

    static final Map<String, BeanCopier> BEAN_COPIERS = new HashMap<>();

    /**
     * 类拷贝
     *
     * @param source 原类
     * @param target 目标类
     */
    public static void beanCopy(Object source, Object target) {
        if (source == null || target == null) {
            return;
        }
        String key = genKey(source, target);
        BeanCopier copier = null;
        if (!BEAN_COPIERS.containsKey(key)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            BEAN_COPIERS.put(key, copier);
        } else {
            copier = BEAN_COPIERS.get(key);
        }
        copier.copy(source, target, null);
    }

    public static <S, T> T beanCopy(S source, Class<T> targetClazz) {
        if (source == null) {
            return null;
        }
        T target = null;
        try {
            target = targetClazz.newInstance();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        beanCopy(source, target);
        return target;
    }

    public static <S, T> List<T> beanCopy(List<S> origLst, Class<T> destClz) {
        ArrayList<T> destLst = new ArrayList<>();
        if (CollUtil.isEmpty(origLst)) {
            return destLst;
        }
        for (S orig : origLst) {
            T dest = beanCopy(orig, destClz);
            destLst.add(dest);
        }
        return destLst;
    }

    private static String genKey(Object source, Object target) {
        return source.getClass().getName() + target.getClass().getName();
    }

    public static <S, T> QueryResultVo<T> pageToQueryResultVo(Page<S> page, Class<T> tClass) {
        if (page == null) {
            return null;
        }
        QueryResultVo<T> queryResultVO = new QueryResultVo();
        queryResultVO.setPageNo(page.getCurrent());
        queryResultVO.setPageSize(page.getSize());
        queryResultVO.setPages(page.getPages());
        queryResultVO.setTotal(page.getTotal());
        return queryResultVO;
    }


}
