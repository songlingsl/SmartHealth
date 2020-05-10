package com.smarthealth.diningroom.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 摄入量参考表
 * </p>
 *
 * @author songling
 * @since 2020-05-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class NutrientIntake implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 人群
     */
    private String people;

    /**
     * 年龄（岁）/生理阶段
     */
    private String ageGroup;

    /**
     * 年龄, 原表有0.7岁的情况
     */
    private BigDecimal age;

    /**
     * 性别
     */
    private String gender;

    /**
     * 体重/kg
     */
    private BigDecimal weight;

    /**
     * 体力活动水平
     */
    private String strength;

    /**
     * 能量kcal
     */
    private BigDecimal energyCal;

    /**
     * 蛋白质量/g
     */
    private BigDecimal protein;

    /**
     * 脂肪(脂肪能量占总能量的百分比)/%小值
     */
    private BigDecimal fatMin;

    /**
     * 脂肪(脂肪能量占总能量的百分比)/%大值
     */
    private BigDecimal fatMax;

    /**
     * 脂肪(脂肪能量占总能量的百分比)/%平均值
     */
    private BigDecimal fatAvg;

    /**
     * 碳水化合物占能量百分数/%小值
     */
    private BigDecimal carbohyMin;

    /**
     * 碳水化合物占能量百分数/%大值
     */
    private BigDecimal carbohyMax;

    /**
     * 碳水化合物占能量百分数/%平均值
     */
    private BigDecimal carbohyAvg;

    /**
     * 钙量/mg
     */
    private BigDecimal elementCa;


}
