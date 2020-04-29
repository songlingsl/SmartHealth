package com.smarthealth.diningroom.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author songling
 * @since 2020-04-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class House implements Serializable {

    private static final long serialVersionUID=1L;
    @TableId   //可能直接命名id 不用标识主键
    private Long houseId;

    private String houseName;

    private Long userId;


}
