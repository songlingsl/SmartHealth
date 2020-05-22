package com.smarthealth.diningroom.mapper;

import com.smarthealth.diningroom.entity.Meal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smarthealth.diningroom.entity.PlateFood;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 每餐 Mapper 接口
 * </p>
 *
 * @author songling
 * @since 2020-04-29
 */
public interface MealMapper extends BaseMapper<Meal> {

    List<PlateFood> getTodayMeal(String userId, LocalDate mealDay);
}
