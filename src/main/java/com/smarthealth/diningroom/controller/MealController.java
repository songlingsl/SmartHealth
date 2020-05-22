package com.smarthealth.diningroom.controller;


import com.smarthealth.diningroom.service.MealService;
import com.smarthealth.diningroom.util.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
     * 获取用户当天就餐数据总计，画雷达图
     * @param userId
     * @return
     */
    @GetMapping("/getTodayAllIntakeByUserId")
    public R getTodayAllIntakeByUserId(String userId){
        return R.ok(mealService.getTodayAllIntakeByUserId(userId));
    }

    @GetMapping("/getWeekIntakeByUserId")
    public R getWeekIntakeByUserId(String userId){
        return R.ok(mealService.getWeekIntakeByUserId(userId));
    }

}

