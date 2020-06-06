package com.smarthealth.diningroom.controller;


import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smarthealth.diningroom.entity.Meal;
import com.smarthealth.diningroom.entity.PlateFood;
import com.smarthealth.diningroom.service.MealService;
import com.smarthealth.diningroom.service.PlateFoodService;
import com.smarthealth.diningroom.util.R;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 每餐 前端控制器
 * </p>
 *
 * @author songling
 * @since 2020-04-29
 */
@RestController
@RequestMapping("/meal")
public class MealController {
    @Resource
    MealService mealService;
    @Resource
    PlateFoodService plateFoodService;
    /**
     * 获取用户当天就餐数据，早中晚
     * @param userId
     * @return
     */
    @GetMapping("/getTodayMealByUserId")
    public R getTodayMeal(String userId){
        return R.ok(mealService.getTodayMeal(userId));
    }
    /**
     * 获取用户当天就餐数据总计
     * @param userId
     * @return
     */
    @GetMapping("/getTodayAllIntakeByUserId")
    public R getTodayAllIntakeByUserId(String userId){
        return R.ok(mealService.getTodayAllIntakeByUserId(userId));
    }
    /**
     * 获取7天就餐数据总计
     * @param userId
     * @return
     */
    @GetMapping("/getWeekIntakeByUserId")
    public R getWeekIntakeByUserId(String userId){
        return R.ok(mealService.getSevenDayIntakeByUserId(userId));
    }

//    public R getWeekIntakeByUserId(String userId){此方法是获取七天数据的折线图
//        return R.ok(mealService.getWeekIntakeByUserId(userId));
//    }

    @SneakyThrows
    @PostMapping("/injectTodayIntake")
    public R injectTodayIntake(Long userId, int mealType, String plateFoodList)
    {
        ObjectMapper mapper = new ObjectMapper();
        List<PlateFood> list= mapper.readValue(plateFoodList,  new TypeReference<List<PlateFood>>() { });
        System.out.println(list.size());
        Meal meal=new Meal();
        meal.setUserId(userId);
        meal.setMealDay(LocalDate.now());
        meal.setMealType(mealType);
        QueryWrapper query = new QueryWrapper(meal);
        Meal origin= mealService.getOne(query);
        Long PlateId=0L;
        if(origin==null){//没有餐
            PlateId=IdUtil.getSnowflake(0,0).nextId();
            meal.setPlateId(PlateId);
            mealService.save(meal);
        }else{
            meal=origin;
        }
        for(PlateFood pf:list){
            pf.setPlateId(meal.getPlateId());
            pf.setMealDay(LocalDate.now());
            pf.setMealType(mealType);
            plateFoodService.save(pf);
        }
        return R.ok();
    }

}

