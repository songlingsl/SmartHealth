package com.smarthealth.diningroom.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 餐盘食物表
 * </p>
 *
 * @author songling
 * @since 2020-04-29
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
     * 这个ID是后台触发后，更新进去的
     */
    private Long mealId;


}
