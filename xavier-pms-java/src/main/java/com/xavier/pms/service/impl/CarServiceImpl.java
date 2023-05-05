package com.xavier.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.convertor.CarConvertor;
import com.xavier.pms.dto.CarQueryDto;
import com.xavier.pms.model.Car;
import com.xavier.pms.dto.CarDto;
import com.xavier.pms.vo.CarVo;
import com.xavier.pms.dao.CarMapper;
import com.xavier.pms.service.ICarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


/**
 * 业务逻辑层-车辆实现类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@Service("carService")
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements ICarService {

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void createCar(CarDto carDto) {
        Car car = CarConvertor.toCar(carDto);
        car.setId(null);
        super.save(car);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void updateCar(CarDto carDto) {
        getBaseCar(carDto.getId());
        Car car  = CarConvertor.toCar(carDto);
        super.updateById(car);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void deleteCar(List<Long> idList) {
        super.removeBatchByIds(idList);
    }

    @Override
    public CarVo getCar(Long id) {
        return CarConvertor.toCarVo(getBaseCar(id));
    }


    @Override
    public Car getBaseCar(Long id) {
        Car car = super.getById(id);
        if(Objects.isNull(car)) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "车辆");
        }
        return car;
    }

    @Override
    public QueryResultVo<CarVo> queryCar(CarQueryDto queryDTO) {
        Page<Car> page = new Page<>();
        page.setCurrent(queryDTO.getPageNo());
        page.setSize(queryDTO.getPageSize());
        LambdaQueryWrapper<Car> wrapper = Car.gw();
        if (Objects.nonNull(queryDTO.getId())) {
            // id不为空
            wrapper.eq(Car::getId, queryDTO.getId());
        }
        if (StrUtil.isNotBlank(queryDTO.getCarName())) {
            // 车辆名称不为空
            wrapper.like(Car::getCarName, queryDTO.getCarName());
        }
        if (StrUtil.isNotBlank(queryDTO.getCarNumber())) {
            // 车牌号码不为空
            wrapper.like(Car::getCarNumber, queryDTO.getCarNumber());
        }
        if (Objects.nonNull(queryDTO.getIsEnable())) {
            // 是否启用不为空
            wrapper.eq(Car::getIsEnable, queryDTO.getIsEnable());
        }
        if (StrUtil.isNotBlank(queryDTO.getRemarks())) {
            // 备注不为空
            wrapper.like(Car::getRemarks, queryDTO.getRemarks());
        }
        if (Objects.nonNull(queryDTO.getCreateTimeFrom())) {
            // 创建时间大于等于
            wrapper.ge(Car::getCreateTime, queryDTO.getCreateTimeFrom());
        }
        if (Objects.nonNull(queryDTO.getCreateTimeTo())) {
            // 创建时间小于等于
            wrapper.le(Car::getCreateTime, queryDTO.getCreateTimeTo());
        }
        wrapper.orderByDesc(Car::getId);
        Page<Car> result = super.page(page, wrapper);
        QueryResultVo<CarVo> queryResultVo = BeanUtil.pageToQueryResultVo(result, CarVo.class);
        queryResultVo.setRecords(CarConvertor.toCarVoList(result.getRecords()));
        return queryResultVo;
    }

}
