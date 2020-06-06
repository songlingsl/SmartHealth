package com.smarthealth.diningroom.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smarthealth.diningroom.entity.Dishes;
import com.smarthealth.diningroom.entity.Meal;
import com.smarthealth.diningroom.entity.Plate;
import com.smarthealth.diningroom.entity.PlateFood;
import com.smarthealth.diningroom.mapper.MealMapper;
import com.smarthealth.diningroom.mapper.PlateFoodMapper;
import com.smarthealth.diningroom.mapper.PlateMapper;
import com.smarthealth.diningroom.service.MealService;
import com.smarthealth.diningroom.util.DictManager;
import com.smarthealth.diningroom.vo.IntakeVO;
import com.smarthealth.diningroom.vo.PerMealIntakeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    DictManager dictManager;

    @Autowired
    private PlateFoodMapper plateFoodMapper;//
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

    @Override
    public Map getTodayMeal(String userId) {
        Map<Long, Dishes> dishesMap=dictManager.getDishesMap();
        Map<Integer, PerMealIntakeVO> rmap=new HashMap();
        String mealDay= LocalDate.now().toString();
        List<PlateFood> list= plateFoodMapper.getTodayMeal(userId,mealDay);
        for (PlateFood p:list) {
            PerMealIntakeVO per=rmap.get(p.getMealType());
             if(per==null){
                 per= new PerMealIntakeVO();
                 rmap.put(p.getMealType(),per);

             }
            Dishes d=dishesMap.get( p.getFoodId());
            if(d!=null){
                p.setFoodName(d.getName());
                addPerFoodIntake(per,p,d);
            }

            per.getFoodList().add(p);
        }
        return rmap;
    }

    @Override
    public IntakeVO getTodayAllIntakeByUserId(String userId) {
        String mealDay= LocalDate.now().toString();
        List<PlateFood> list= plateFoodMapper.getTodayMeal(userId,mealDay);
        IntakeVO vo=dealDailyIntake(list);
        return  vo;
    }



    @Override
    public IntakeVO getSevenDayIntakeByUserId(String userId) {
        String mealDay= LocalDate.now().toString();
        DateTime dateTime= DateUtil.offsetDay(DateUtil.date(),-6);//六天前
        String preDay=DateUtil.format(dateTime, "yyyy-MM-dd");
        List<PlateFood> list= plateFoodMapper.getSevenDayMeal(userId,preDay,mealDay);
        IntakeVO vo=dealDailyIntake(list);
        return  vo;
    }

    private IntakeVO dealDailyIntake(List<PlateFood> list) {
        Map<Long, Dishes> dishesMap=dictManager.getDishesMap();
        IntakeVO vo=new IntakeVO();
        for (PlateFood p:list) {
            Dishes dishes=dishesMap.get( p.getFoodId());
            if(dishes!=null){
                BigDecimal weight= new BigDecimal(p.getWeight());
                vo.setEnergy(vo.getEnergy()+dishes.getEnergyCal().multiply(weight).intValue());
                vo.setFats(vo.getFats()+dishes.getFat().multiply(weight).intValue());
                vo.setProteins(vo.getProteins()+dishes.getProtein().multiply(weight).intValue());
                vo.setCarbohydrates(vo.getCarbohydrates()+dishes.getCarbohy().multiply(weight).intValue());
                vo.setCalcium(vo.getCalcium()+dishes.getElementCa().multiply(weight).intValue());
            }
        }
        return vo;
    }

    /**
     * 每种菜的摄入量相加到每餐
     * @param per
     * @param p
     */
    private void addPerFoodIntake(PerMealIntakeVO per, PlateFood p, Dishes dishes) {
        BigDecimal weight= new BigDecimal(p.getWeight());
        per.setEnergy(per.getEnergy()+dishes.getEnergyCal().multiply(weight).intValue());
        per.setFats(per.getFats()+dishes.getFat().multiply(weight).intValue());
        per.setProteins(per.getProteins()+dishes.getProtein().multiply(weight).intValue());
        per.setCarbohydrates(per.getCarbohydrates()+dishes.getCarbohy().multiply(weight).intValue());
        per.setCalcium(per.getCalcium()+dishes.getElementCa().multiply(weight).intValue());
    }



    @Override
    public Map<String, IntakeVO> getWeekIntakeByUserId(String userId) {
        Map<String, IntakeVO> rmap=new LinkedHashMap<>();
        for(int i=-6;i<=0;i++){
            DateTime dateTime= DateUtil.offsetDay(DateUtil.date(),i);
            String mealDay=DateUtil.format(dateTime, "yyyy-MM-dd");
            String keyDay=DateUtil.format(dateTime, "MM-dd");
            //String week=dateTime.dayOfWeek()-1+"";
            List<PlateFood> list= plateFoodMapper.getTodayMeal(userId,mealDay);
            IntakeVO vo=dealDailyIntake(list);
            rmap.put(keyDay,vo);
        }
        return rmap;
    }
}
