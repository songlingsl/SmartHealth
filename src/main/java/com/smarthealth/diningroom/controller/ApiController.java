package com.smarthealth.diningroom.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smarthealth.diningroom.entity.Dishes;
import com.smarthealth.diningroom.entity.PlateFood;
import com.smarthealth.diningroom.service.PlateFoodService;
import com.smarthealth.diningroom.util.CommonUtil;
import com.smarthealth.diningroom.util.DelayQueueManager;
import com.smarthealth.diningroom.util.DictManager;
import com.smarthealth.diningroom.vo.IntakeVO;
import com.smarthealth.diningroom.vo.RecivedDishVO;
import com.smarthealth.diningroom.vo.ReturnDishResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
    @Resource
    DictManager dictManager;

    /**
     * 为一个用户增加菜肴
     *
     * @param recivedDishVO 返回本餐盘所有摄入量
     * @return
     */
    @PostMapping("/addDishForUser")
    public ReturnDishResultVO addDishForUser(@Valid @RequestBody RecivedDishVO recivedDishVO) {
        Long plateId = 123L;//recivedDishVO中有盘子id
        delayQueueManager.put(plateId);//发订阅通知

        PlateFood plateFood = new PlateFood();
        plateFood.setPlateId(plateId);
        plateFood.setFoodId(recivedDishVO.getSid());
        plateFood.setWeight(recivedDishVO.getWeight());
        plateFood.setMealDay(LocalDate.now());
        plateFood.setMealType(CommonUtil.getDealType());
        plateFoodService.save(plateFood);//保存本次菜品重量

        PlateFood conditions = new PlateFood();
        conditions.setMealType(CommonUtil.getDealType());
        conditions.setMealDay(LocalDate.now());
        conditions.setPlateId(plateId);
        QueryWrapper query = new QueryWrapper(conditions);
        List<PlateFood> PlateFoodList = plateFoodService.list(query);
        return getIntake(PlateFoodList);
    }

    private ReturnDishResultVO getIntake(List<PlateFood> PlateFoodList) {
        Integer weight=0;
        BigDecimal energy=new BigDecimal(0);
        BigDecimal fats=new BigDecimal(0);
        BigDecimal proteins=new BigDecimal(0);
        BigDecimal carbohydrates=new BigDecimal(0);
        BigDecimal calcium=new BigDecimal(0);
        Map<Long, Dishes> dishesMap = dictManager.getDishesMap();
        for (PlateFood pf : PlateFoodList) {
            Dishes dishes = dishesMap.get(pf.getFoodId());
            Integer perWeight=pf.getWeight();
            weight+=perWeight;

            BigDecimal per=new BigDecimal(perWeight);
            energy=energy.add(dishes.getEnergyCal().multiply(per));
            fats=fats.add(dishes.getFat().multiply(per));
            proteins= proteins.add(dishes.getProtein().multiply(per));
            carbohydrates=carbohydrates.add(dishes.getCarbohy().multiply(per));
            calcium=calcium.add(dishes.getElementCa().multiply(per));
        }
        ReturnDishResultVO vo = new ReturnDishResultVO();
        IntakeVO data=new IntakeVO();
        data.setWeight(weight);
        data.setEnergy(energy.intValue());
        data.setFats(fats.intValue());
        data.setProteins(proteins.intValue());
        data.setCarbohydrates(carbohydrates.intValue());
        data.setCalcium(calcium.intValue());
        vo.setData(data);
        return vo;
    }

//    @GetMapping("/testCommonError")
//    public String testCommonError(){
//        ReturnDishResultVO vo=null;
//        vo.getCode();
//        return "哈哈";
//
//    }

}
