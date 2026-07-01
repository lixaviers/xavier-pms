package com.xavier.pms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xavier.pms.model.ReportComment;
import com.xavier.pms.vo.WorkReportVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 汇报评论 Mapper
 */
@Mapper
public interface ReportCommentMapper extends BaseMapper<ReportComment> {

    List<WorkReportVo.CommentItem> queryByReportId(@Param("reportId") Long reportId);
}
