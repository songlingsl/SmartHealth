package com.smarthealth.diningroom.util;

import com.smarthealth.diningroom.entity.Dishes;
import com.smarthealth.diningroom.entity.NutrientIntake;
import com.smarthealth.diningroom.mapper.DishesMapper;
import com.smarthealth.diningroom.mapper.NutrientIntakeMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Slf4j
@Component
public class DictManager implements CommandLineRunner {
    private Map<Long, Dishes> dishesMap=new HashMap<>();
    private Map<String,NutrientIntake> intakeMap=new HashMap<>();
    @Resource
    DishesMapper dishesMapper;

    @Resource
    NutrientIntakeMapper intakeMapper;
    @Override
    public void run(String... args) throws Exception {
        List<Dishes>  dishesList =dishesMapper.selectList(null);
        for(Dishes f:dishesList){
            dishesMap.put(f.getId(),f);
        }
        log.info("食物字典表初始化完毕"+dishesMap.get(164));
        List<NutrientIntake> intakeList =intakeMapper.selectList(null);
        for(NutrientIntake intake:intakeList){
            String ageGroup=intake.getAgeGroup();
            String  gender= intake.getGender();
            String strength=intake.getStrength();
            String key=ageGroup+"_"+gender+"_"+strength;
            intakeMap.put(key,intake);
        }
        log.info("摄入量字典表初始化完毕");

    }
}
