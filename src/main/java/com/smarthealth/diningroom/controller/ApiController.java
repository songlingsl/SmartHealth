package com.smarthealth.diningroom.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smarthealth.diningroom.entity.*;
import com.smarthealth.diningroom.service.BasicUserService;
import com.smarthealth.diningroom.service.MealService;
import com.smarthealth.diningroom.service.PlateFoodService;
import com.smarthealth.diningroom.util.CommonUtil;
import com.smarthealth.diningroom.util.DelayQueueManager;
import com.smarthealth.diningroom.util.DictManager;
import com.smarthealth.diningroom.vo.IntakeVO;
import com.smarthealth.diningroom.vo.ReturnDishResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    @Resource
    MealService mealService;
    @Resource
    private BasicUserService basicUserService;
    /**
     * 为一个用户增加菜肴
     *
     * @param
     * @return
     */
    @GetMapping("/addDishForUser")
    public ReturnDishResultVO addDishForUser(String sid,String weight,String code) {
        //String code=recivedDishVO.getCode();//code中包含plateurl
        String plateUrl= code.split("/a/")[1];
        Map<String, Long> plateMap = dictManager.getPlateMap();
        Long plateId=plateMap.get(plateUrl);
        delayQueueManager.put(plateId);//发订阅通知

        PlateFood plateFood = new PlateFood();
        plateFood.setPlateId(plateId);
        plateFood.setFoodId(Long.valueOf(sid));
        plateFood.setWeight(Integer.valueOf(weight));
        plateFood.setMealDay(LocalDate.now());
        plateFood.setMealType(CommonUtil.getDealType());
        plateFoodService.save(plateFood);//保存本次菜品重量

        PlateFood conditions = new PlateFood();
        conditions.setMealType(CommonUtil.getDealType());
        conditions.setMealDay(LocalDate.now());
        conditions.setPlateId(plateId);
        QueryWrapper query = new QueryWrapper(conditions);
        List<PlateFood> PlateFoodList = plateFoodService.list(query);//本餐盘摄入量
         ReturnDishResultVO vo=getIntake(PlateFoodList);
        return invertPercentage(vo,plateId);
    }

    private ReturnDishResultVO invertPercentage(ReturnDishResultVO vo,Long plateId){//转换成百分比
        Map<String, NutrientIntake> intakeMap=dictManager.getIntakeMap();
        Meal meal=new Meal();
        int mealType=CommonUtil.getDealType();
        meal.setMealDay(LocalDate.now());
        meal.setMealType(mealType);
        meal.setPlateId(plateId);
        QueryWrapper query = new QueryWrapper(meal);
        meal=mealService.getOne(query);
        String key="18-44_1_中";//默认成人男性key
        if(meal!=null){
            BasicUser user=basicUserService.getById(meal.getUserId());//之后redis
            key=user.getAgeGroup()+"_"+user.getGender()+"_"+user.getStrength();
        }
        NutrientIntake standerd=intakeMap.get(key);//每餐是标准的1/3
        BigDecimal  protein=  standerd.getProtein().divide(new BigDecimal(3),4, BigDecimal.ROUND_HALF_UP);
        BigDecimal  fat=standerd.getFatAvg().divide(new BigDecimal(3),4, BigDecimal.ROUND_HALF_UP);
        BigDecimal  energy=standerd.getEnergyCal().divide(new BigDecimal(3),4, BigDecimal.ROUND_HALF_UP);
        BigDecimal  ca=standerd.getElementCa().divide(new BigDecimal(3),4, BigDecimal.ROUND_HALF_UP);
        BigDecimal  carbohy=standerd.getCarbohyAvg().divide(new BigDecimal(3),4, BigDecimal.ROUND_HALF_UP);
        IntakeVO intakeVO=vo.getData();//每项得到百分比
        protein=BigDecimal.valueOf(intakeVO.getProteins()).divide(protein,4, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100));
        fat=BigDecimal.valueOf(intakeVO.getFats()).divide(fat,4, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100));
        energy=BigDecimal.valueOf(intakeVO.getEnergy()).divide(energy,4, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100));
        ca=BigDecimal.valueOf(intakeVO.getCalcium()).divide(ca,4, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100));
        carbohy=BigDecimal.valueOf(intakeVO.getCarbohydrates()).divide(carbohy,4, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100));

        intakeVO.setProteins(protein.intValue());
        intakeVO.setFats(fat.intValue());
        intakeVO.setEnergy(energy.intValue());
        intakeVO.setCalcium(ca.intValue());
        intakeVO.setCarbohydrates(carbohy.intValue());
        return vo;
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
