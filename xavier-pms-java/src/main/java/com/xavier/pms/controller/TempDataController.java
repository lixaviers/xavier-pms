package com.xavier.pms.controller;

import cn.hutool.core.collection.CollUtil;
import com.xavier.pms.model.TempData;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.ITempDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("temp")
@RestController
@Api(tags = "Temp接口")
public class TempDataController extends CommonController {

    @Resource
    private ITempDataService tempDataService;

    @ApiOperation(value = "add", notes = "add")
    @PutMapping("add")
    public Result add() {
        List<TempData> tempList = new ArrayList<>();
        List<TempData> list = tempDataService.list();
        Map<String, List<TempData>> map = list.stream().collect(Collectors.groupingBy(TempData::getA));
        map.forEach((k, v) -> {
            /*Map<String, List<TempData>> listMap = v.stream().collect(Collectors.groupingBy(TempData::getE));
            for (String s : listMap.keySet()) {
                List<TempData> tempData = listMap.get(s);
                if (tempData.size() > 1) {
                    BigDecimal total = BigDecimal.ZERO;
                    for (TempData tempDatum : tempData) {
                        total = total.add(tempDatum.getG());
                    }
                    TempData e = tempData.get(0);
                    e.setG(total);
                    tempList.add(e);
                } else {
                    tempList.add(tempData.get(0));
                }
            }*/

            BigDecimal total = BigDecimal.ZERO;
            for (TempData tempData : v) {
                total = total.add(tempData.getG());
            }
            BigDecimal sub = new BigDecimal(100);
            for (int i = 0; i < v.size(); i++) {
                TempData tempData = v.get(i);
                if (i != v.size() - 1) {
                    tempData.setJ(tempData.getG().divide(total, 4, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100)));
                    sub = sub.subtract(tempData.getJ());
                } else {
                    tempData.setJ(sub);
                }
            }
            tempDataService.updateBatchById(v);
        });

       /* List<List<TempData>> split = CollUtil.split(tempList, 200);
        for (List<TempData> tempData : split) {
            tempDataService.saveBatch(tempData);
        }*/

        return Result.ok();
    }


}
