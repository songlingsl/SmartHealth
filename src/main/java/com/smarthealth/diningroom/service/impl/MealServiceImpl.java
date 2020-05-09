package com.smarthealth.diningroom.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smarthealth.diningroom.entity.Meal;
import com.smarthealth.diningroom.entity.Plate;
import com.smarthealth.diningroom.mapper.MealMapper;
import com.smarthealth.diningroom.mapper.PlateMapper;
import com.smarthealth.diningroom.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 每餐 服务实现类
 * </p>
 *
 * @author songling
 * @since 2020-04-29
 */
@Service
public class MealServiceImpl extends ServiceImpl<MealMapper, Meal> implements MealService {
    @Autowired
    private PlateMapper plateMapper;//

    @Transactional
    @Override
    public void transactionalTest() {
        Meal meal=new Meal();
       // meal.setType(2);
        this.save(meal);
        Plate plate=new Plate();
        plate.setStatus(377777777);//上面的保存也不会入库
        plateMapper.insert(plate);
    }
}
