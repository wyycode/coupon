package com.wyy.start.coupon.mapper.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;

/**
 * All rights Reserved,Designed By www.freemud.cn
 *
 * @Title: Coupon
 * @Package com.wyy.start.coupon.mapper.entity;
 * @Description:
 * @Author: Administrator
 * @Date: 2019/5/10 20:30
 * @Version V1.0
 * @Copyright:2018www.freemud.cn Inc.All rights reserved. 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */

@Data
@TableName("coupon")
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer           id;
    private String            itemId;
    private String            itemName;
    private String            pic;
    private String            detail;
    private String            topClass;
    private String            tbkUrl;
    private Integer           price;
    private Integer           saleCount;
    private Integer           rate;
    private Integer           income;
    private String            ww;
    private String            wwid;
    private String            store;
    private String            platform;
    private String            couponId;
    private Integer           couponCount;
    private Integer           leftCount;
    private String           couponPrice;
    private Date              startDate;
    private Date              endDate;
    private String            couponUrl;
    private String            uurl;
    private Integer           state;
    private String            kouling;
    private String            batchNo;
}
