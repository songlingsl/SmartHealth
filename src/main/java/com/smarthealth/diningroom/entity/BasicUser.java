package com.smarthealth.diningroom.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author songling
 * @since 2020-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BasicUser implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户id
     */
    @TableId
    private Long userId;

    /**
     * 用户名，先用微信名，可随时修改
     */
    private String nickName;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 头像url
     */
    private String avatarUrl;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 小程序对应用户的唯一id
     */
    private String openId;

    /**
     * 身高
     */
    private String height;

    /**
     * 体重
     */
    private String weight;

    /**
     * 出生年
     */
    private Integer birthday;
    /**
     * 年龄段
     */
    private String ageGroup;

    /**
     * 工作强度级别 - 极轻 轻 中 重 极重
     */
    private String strength;

    /**
     * 小程序用来换取openid的码
     */
    @TableField(exist = false)
    private String userCode;

    /**
     * 每个人的标准摄入
     */
    @TableField(exist = false)
    private NutrientIntake standardIntake;


}
