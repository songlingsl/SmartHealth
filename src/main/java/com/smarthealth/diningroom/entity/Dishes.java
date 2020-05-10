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
 * 菜品成分表
 * </p>
 *
 * @author songling
 * @since 2020-05-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Dishes implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜名
     */
    private String name;

    /**
     * 能量（千卡）
     */
    private BigDecimal energyCal;

    /**
     * 蛋白质(克）
     */
    private BigDecimal protein;

    /**
     * 脂肪（克）
     */
    private BigDecimal fat;

    /**
     * 碳水化合物（克）
     */
    private BigDecimal carbohy;

    /**
     * 膳食纤维（克）
     */
    private BigDecimal dietaryFibre;

    /**
     * 钙（毫克）
     */
    private BigDecimal elementCa;


}
