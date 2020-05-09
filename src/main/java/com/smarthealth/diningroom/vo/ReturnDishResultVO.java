package com.smarthealth.diningroom.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class ReturnDishResultVO implements Serializable {
    private static final long serialVersionUID = 7467879599652621366L;
    private String msg="success";
    private Integer code=0;
    private IntakeVO data;
}
