package com.smarthealth.diningroom.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smarthealth.diningroom.entity.Meal;
import com.smarthealth.diningroom.vo.IntakeVO;

import java.util.Map;

/**
 * <p>
 * 每餐 服务类
 * </p>
 *
 * @author songling
 * @since 2020-04-29
 */
public interface MealService extends IService<Meal> {
    public void transactionalTest();
    public Map getTodayMeal(String userId);

   public  IntakeVO getTodayAllIntakeByUserId(String userId);

    Map<String,IntakeVO> getWeekIntakeByUserId(String userId);
}
