package com.smarthealth.diningroom.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 每餐
 * </p>
 *
 * @author songling
 * @since 2020-05-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Meal implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 餐id
     */
    @TableId(value = "meal_id", type = IdType.AUTO)
    private Long mealId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 餐盘id
     */
    private Long plateId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 餐类别？早中晚
     */
    private Integer mealType;

    /**
     * 就餐日（与餐盘id，就餐类型一起与用户关联）
     */
    private LocalDate mealDay;


}
