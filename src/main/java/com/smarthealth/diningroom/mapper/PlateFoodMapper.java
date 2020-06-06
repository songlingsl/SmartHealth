package com.smarthealth.diningroom.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smarthealth.diningroom.entity.PlateFood;

import java.util.List;

/**
 * <p>
 * 餐盘食物表 Mapper 接口
 * </p>
 *
 * @author songling
 * @since 2020-04-29
 */
public interface PlateFoodMapper extends BaseMapper<PlateFood> {

    List<PlateFood> getTodayMeal(String userId, String mealDay);

    List<PlateFood> getSevenDayMeal(String userId, String preDay, String mealDay);
}
