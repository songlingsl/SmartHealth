package com.smarthealth.diningroom.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class IntakeVO implements Serializable {
    private static final long serialVersionUID = 3639417896408455905L;
    private Integer weight=0;
    private Integer energy=0;
    private Integer fats=0;
    private Integer proteins=0;
    private Integer carbohydrates=0;
    private Integer calcium=0;
    private String dishName;//语音识别后使用

}
