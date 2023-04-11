package com.xavier.pms.convertor;

import com.xavier.pms.model.Car;
import com.xavier.pms.dto.CarDto;
import com.xavier.pms.vo.CarVo;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 车辆对象相互转换
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public abstract class CarConvertor {

    private static final BeanCopier beanCopierForCarVo = BeanCopier.create(Car.class, CarVo.class, false);
    private static final BeanCopier beanCopierForCar = BeanCopier.create(CarDto.class, Car.class, false);

    public static CarVo toCarVo(Car car) {
        if (Objects.isNull(car)) {
            return null;
        }
        CarVo carVo = new CarVo();
        beanCopierForCarVo.copy(car, carVo, null);
        return carVo;
    }

    public static Car toCar(CarDto carDto) {
        Car car = new Car();
        beanCopierForCar.copy(carDto, car, null);
        return car;
    }

    public static List<CarVo> toCarVoList(List<Car> carList) {
        if (Objects.isNull(carList) || carList.isEmpty()) {
            return new ArrayList<>();
        }
        List<CarVo> carInfoList = new ArrayList<CarVo>(carList.size());
        for (Car car : carList) {
            carInfoList.add(toCarVo(car));
        }
        return carInfoList;
    }

}
