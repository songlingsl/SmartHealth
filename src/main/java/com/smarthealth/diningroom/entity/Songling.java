package com.smarthealth.diningroom.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.io.Serializable;

@Data
public class Songling implements Serializable {
    private static final long serialVersionUID = -3100715851080733340L;
    //@TableId(type = IdType.ASSIGN_ID)  全局已经配置雪花算法
    private Long id=123456L;
    private Long pId=456789L;
    private String name;
    private Integer number;
    @TableField(exist = false)//没有对应字段标识
    private String addr;
}