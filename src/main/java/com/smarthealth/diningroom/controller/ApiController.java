package com.smarthealth.diningroom.controller;

import com.smarthealth.diningroom.entity.PlateFood;
import com.smarthealth.diningroom.service.PlateFoodService;
import com.smarthealth.diningroom.util.CommonUtil;
import com.smarthealth.diningroom.util.DelayQueueManager;
import com.smarthealth.diningroom.vo.RecivedDishVO;
import com.smarthealth.diningroom.vo.ReturnDishResultVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDate;

/**
 * <p>
 * 接受其他服务调用
 * </p>
 *
 * @author songling
 * @since 2020-05-09
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    @Resource
    DelayQueueManager delayQueueManager;

    @Resource
    PlateFoodService plateFoodService;
    /**
     * 为一个用户增加菜肴
     * @param recivedDishVO 返回本餐盘所有摄入量
     * @return
     */
    @PostMapping("/addDishForUser")
    public ReturnDishResultVO addDishForUser(@Valid @RequestBody RecivedDishVO recivedDishVO ){
        Long plateId=123L;//recivedDishVO中有盘子id
        delayQueueManager.put(plateId);//发订阅通知

        PlateFood plateFood=new PlateFood();
        plateFood.setPlateId(plateId);
        plateFood.setFoodId(recivedDishVO.getSid());
        plateFood.setWeight(recivedDishVO.getWeight());
        plateFood.setMealDay(LocalDate.now());
        plateFood.setMealType(CommonUtil.getDealType());
        plateFoodService.save(plateFood);//保存本次菜品重量

        ReturnDishResultVO vo=new ReturnDishResultVO();//查询本餐盘所有菜品摄入量之和，并返回
        return vo;
    }

    @GetMapping("/testCommonError")
    public String testCommonError(){
        ReturnDishResultVO vo=null;
        vo.getCode();
        return "哈哈";

    }

}
