package com.smarthealth.diningroom.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 餐盘食物表
 * </p>
 *
 * @author songling
 * @since 2020-05-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PlateFood implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 餐盘与食物关系id
     */
    @TableId
    private Long plateFoodId;

    /**
     * 餐盘id
     */
    private Long plateId;

    /**
     * 食物id
     */
    private Long foodId;

    /**
     * 取量
     */
    private Integer weight;

    /**
     * 取菜时间
     */
    private LocalDateTime createTime;

    /**
     * 早中晚餐
     */
    private Integer mealType;

    /**
     * 就餐日（与餐盘id，就餐类型一起与用户关联）
     */
    private LocalDate mealDay;


}
