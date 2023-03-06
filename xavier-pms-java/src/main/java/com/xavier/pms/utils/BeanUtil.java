package com.xavier.pms.utils;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xavier.pms.query.QueryResultVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.*;

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

    public static <S, T> QueryResultVo<T> pageToQueryResultVo(Page<S> page, Class<T> targetClazz) {
        if (page == null) {
            return null;
        }
        QueryResultVo<T> queryResultVO = new QueryResultVo();
        queryResultVO.setPageNo(page.getCurrent());
        queryResultVO.setPageSize(page.getSize());
        queryResultVO.setTotalPages(page.getPages());
        queryResultVO.setTotalRecords(page.getTotal());
        return queryResultVO;
    }

    /**
     * 获取两个对象之间的变化(仅对比当前对象写的属性，不会对比父类写的属性)
     *
     * @param oldBean
     * @param newBean
     * @return
     */

    public static String getObjectDifferent(Object oldBean, Object newBean) {
        if (oldBean != null && newBean != null) {
            String modifyValue = "";
            try {
                List<Field> fields1 = Arrays.asList(oldBean.getClass().getDeclaredFields());
                List<Field> fields2 = Arrays.asList(newBean.getClass().getDeclaredFields());
                // 如果父类属性变化也记录
                // fields.addAll(new ArrayList<>(Arrays.asList(clazz.getSuperclass().getDeclaredFields())));
                for (Field field1 : fields1) {
                    if ("serialVersionUID".equals(field1.getName())) {
                        continue;
                    }
                    ApiModelProperty annotation = field1.getAnnotation(ApiModelProperty.class);
                    if (Objects.isNull(annotation)) {
                        continue;
                    }
                    Object o1 = new PropertyDescriptor(field1.getName(), oldBean.getClass()).getReadMethod().invoke(oldBean);
                    for (Field field2 : fields2) {
                        if (!Objects.equals(field2.getName(), field1.getName())) {
                            continue;
                        }
                        Object o2 = new PropertyDescriptor(field2.getName(), newBean.getClass()).getReadMethod().invoke(newBean);
                        if (Objects.isNull(o1) && Objects.isNull(o2)) {
                            break;
                        }
                        if (Objects.isNull(o2)) {
                            System.out.printf(annotation.value() + "设置为" + o1.toString());
                            break;
                        }
                        if (!Objects.equals(o1, o2)) {
                            System.out.printf(annotation.value() + "从" + o2.toString() + "改为" + o1.toString());
                            break;
                        }
                    }
//                    Object o2 = getMethod.invoke(newBean);
//                    if (o1 == null || o2 == null) {
//                        continue;
//                    }
//                    if (!o1.toString().equals(o2.toString())) {
//                        modifyValue += field1.getName() + "：" + o1 + "->" + o2 + "； ";
//                    }
                }
                return modifyValue;

            } catch (Exception e) {
                e.printStackTrace();
                return e.toString();
            }
        } else {
            log.info("传入对象为空，保存 “修改操作” 系统敏感操作记录失败");
            return "保存 “修改操作” 传入对象为空";
        }
    }


}
