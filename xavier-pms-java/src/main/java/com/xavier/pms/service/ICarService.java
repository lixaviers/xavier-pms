package com.xavier.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xavier.pms.dto.CarDto;
import com.xavier.pms.dto.CarQueryDto;
import com.xavier.pms.vo.CarVo;
import com.xavier.pms.model.Car;
import com.xavier.pms.query.QueryResultVo;

import java.util.List;

/**
 * 业务逻辑层-车辆接口类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface ICarService extends IService<Car> {

    /**
     * 创建车辆
     *
     * @param carDto 车辆入参
     */
    void createCar(CarDto carDto);

    /**
     * 更新车辆
     *
     * @param carDto 车辆入参
     */
    void updateCar(CarDto carDto);

    /**
     * 删除车辆
     *
     * @param idList id列表
     */
    void deleteCar(List<Long> idList);

    /**
     * 根据ID获取车辆信息
     *
     * @param id
     * @return 车辆信息
     */
    CarVo getCar(Long id);

    /**
     * 根据ID获取车辆信息
     *
     * @param id
     * @return 车辆信息
     */
    Car getBaseCar(Long id);

    /**
     * 分页查询车辆信息
     *
     * @param carQueryDto 车辆查询入参
     * @return 车辆信息
     */
    QueryResultVo<CarVo> queryCar(CarQueryDto carQueryDto);



}
