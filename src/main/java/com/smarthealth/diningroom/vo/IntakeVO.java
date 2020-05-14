package com.smarthealth.diningroom.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class IntakeVO implements Serializable {
    private static final long serialVersionUID = 3639417896408455905L;
    private Integer weight;
    private Integer energy;
    private Integer fats;
    private Integer proteins;
    private Integer carbohydrates;
    private Integer calcium;
    private String dishName;//语音识别后使用

}
