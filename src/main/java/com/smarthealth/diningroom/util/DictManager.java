package com.smarthealth.diningroom.util;

import com.smarthealth.diningroom.entity.Dishes;
import com.smarthealth.diningroom.entity.NutrientIntake;
import com.smarthealth.diningroom.entity.Plate;
import com.smarthealth.diningroom.mapper.DishesMapper;
import com.smarthealth.diningroom.mapper.NutrientIntakeMapper;
import com.smarthealth.diningroom.mapper.PlateMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.ansj.library.DicLibrary;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Slf4j
@Component
public class DictManager implements CommandLineRunner {
    private Map<Long, Dishes> dishesMap=new HashMap<>();
    private Map<String, Dishes> dishesNameMap=new HashMap<>();
    private Map<String,NutrientIntake> intakeMap=new HashMap<>();
    private Map<String, BigDecimal> numberMap=new HashMap<>();
    private Map<String,Long> plateMap=new HashMap<>();

    @Resource
    DishesMapper dishesMapper;
    @Resource
    PlateMapper plateMapper;
    @Resource
    NutrientIntakeMapper intakeMapper;
    @Override
    public void run(String... args) throws Exception {
        DicLibrary.insert(DicLibrary.DEFAULT, "两","unit",1);
        DicLibrary.insert(DicLibrary.DEFAULT, "克","unit",1);
        DicLibrary.insert(DicLibrary.DEFAULT, "斤","unit",1);
        log.info("重量相关初始化完毕");

        List<Dishes>  dishesList =dishesMapper.selectList(null);
        for(Dishes f:dishesList){
            //转每克
            f.setProtein(f.getProtein().divide(new BigDecimal(1000)));
            f.setFat(f.getFat().divide(new BigDecimal(1000)));
            f.setCarbohy(f.getCarbohy().divide(new BigDecimal(1000)));
            f.setElementCa(f.getElementCa().divide(new BigDecimal(1000)));
            f.setEnergyCal(f.getEnergyCal().divide(new BigDecimal(1000)));
            dishesMap.put(f.getId(),f);
            dishesNameMap.put(f.getName(),f);
            DicLibrary.insert(DicLibrary.DEFAULT, f.getName());
        }
        log.info("食物字典表初始化完毕");
        List<NutrientIntake> intakeList =intakeMapper.selectList(null);
        for(NutrientIntake intake:intakeList){
            String ageGroup=intake.getAgeGroup();
            String  gender= intake.getGender();
            String strength=intake.getStrength();
            String key=ageGroup+"_"+gender+"_"+strength;
            intakeMap.put(key,intake);
        }
        log.info("个人每天摄入量字典表初始化完毕");


        List<Plate> plateList =plateMapper.selectList(null);
        for(Plate p:plateList){
            plateMap.put(p.getPlateUrl(),p.getPlateId());
        }
        log.info("盘子url对应字典表初始化完毕");
    }
}
