package com.wyy.start.coupon.mapper;

import com.wyy.start.coupon.component.CommonMapper;
import com.wyy.start.coupon.mapper.entity.Coupon;

/**
 * All rights Reserved,Designed By www.freemud.cn
 *
 * @Title: CouponMapper
 * @Package com.wyy.start.coupon.mapper;
 * @Description:
 * @Author: Administrator
 * @Date: 2019/5/10 20:42
 * @Version V1.0
 * @Copyright:2018www.freemud.cn Inc.All rights reserved. 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
public interface CouponMapper extends CommonMapper<Coupon> {

    default void updateTkl(String tkl, Integer id) {
        Coupon coupon = new Coupon();
        coupon.setKouling(tkl);
        coupon.setState(2);
        coupon.setId(id);
        this.updateById(coupon);
    }

}
