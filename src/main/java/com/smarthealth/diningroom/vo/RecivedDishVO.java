package com.smarthealth.diningroom.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class RecivedDishVO implements Serializable {
    private static final long serialVersionUID = -862353316783580537L;
    private String sid;//菜品ID（电子秤）
    private Integer weight; //重量克
    private  String code;//二维码解析出的url，包含盘子id

}
