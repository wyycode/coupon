package com.wyy.start.coupon.component;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.wyy.start.coupon.dto.PageData;

/**
 * All rights Reserved,Designed By www.freemud.cn
 *
 * @Title: CommonMapper
 * @Package com.wyy.start.coupon.component;
 * @Description:
 * @Author: Administrator
 * @Date: 2019/5/10 23:43
 * @Version V1.0
 * @Copyright:2018www.freemud.cn Inc.All rights reserved. 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
public interface CommonMapper<T> extends BaseMapper<T> {
    default PageData<T> selectPage(PageData page, Wrapper<T> wrapper) {
        int totalCount = this.selectCount(wrapper);
        int offset = (page.getCurrent() - 1) * page.getSize();
        RowBounds rowBounds = new RowBounds(offset, page.getSize());
        List<T> records = this.selectPage(rowBounds, wrapper);

        page.setRecords(records);
        page.setTotalCount(totalCount);

        return page;
    }

    default T selectOne(T t) {
        RowBounds rowBounds = new RowBounds(0, 1);
        Wrapper<T> w = new EntityWrapper<>(t);
        List<T> records = this.selectPage(rowBounds, w);
        if (CollectionUtils.isEmpty(records)) {
            return null;
        }
        return records.get(0);
    }

    default T selectOne(Wrapper<T> wrapper) {
        RowBounds rowBounds = new RowBounds(0, 1);
        List<T> records = this.selectPage(rowBounds, wrapper);
        if (CollectionUtils.isEmpty(records)) {
            return null;
        }
        return records.get(0);
    }
}
