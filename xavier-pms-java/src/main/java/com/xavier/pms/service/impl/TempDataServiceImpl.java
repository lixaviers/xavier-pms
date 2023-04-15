package com.xavier.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.dao.TempDataMapper;
import com.xavier.pms.model.TempData;
import com.xavier.pms.service.ITempDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * 业务逻辑层-职位实现类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@Service("tempDataService")
public class TempDataServiceImpl extends ServiceImpl<TempDataMapper, TempData> implements ITempDataService {


}
