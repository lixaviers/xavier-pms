package com.xavier.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xavier.pms.dto.OperateLogDto;
import com.xavier.pms.dto.OperateLogQueryDto;
import com.xavier.pms.model.OperateLog;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.vo.OperateLogVo;

public interface IOperateLogService extends IService<OperateLog> {

    /**
     * 添加操作日志
     */
    void addOperateLog(OperateLogDto dto);

    QueryResultVo<OperateLogVo> queryOperateLog(OperateLogQueryDto queryDTO);

}
