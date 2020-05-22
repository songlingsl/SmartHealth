package com.smarthealth.diningroom.vo;

import com.smarthealth.diningroom.entity.PlateFood;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PerMealIntakeVO extends IntakeVO {
    private static final long serialVersionUID = 8026508900145568021L;
    List<PlateFood> foodList=new ArrayList<>();

}
