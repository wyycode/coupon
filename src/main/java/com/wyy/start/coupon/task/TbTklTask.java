package com.wyy.start.coupon.task;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.wyy.start.coupon.dto.PageData;
import com.wyy.start.coupon.mapper.CouponMapper;
import com.wyy.start.coupon.mapper.entity.Coupon;
import com.wyy.start.coupon.service.TklService;

import lombok.extern.slf4j.Slf4j;

/**
 * All rights Reserved,Designed By www.freemud.cn
 *
 * @Title: TbTklTask
 * @Package com.wyy.start.coupon.task;
 * @Description:
 * @Author: Administrator
 * @Date: 2019/5/10 23:41
 * @Version V1.0
 * @Copyright:2018www.freemud.cn Inc.All rights reserved. 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
@Component
@Slf4j
public class TbTklTask {

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private TklService   tklService;

    @Scheduled(fixedRate = 1000 * 60 * 2)
    public void initTkl() {
        log.info("initTkl begin");
        PageData<Coupon> pageData = new PageData<>(100);
        Wrapper<Coupon> query = new EntityWrapper<>();
        query.isNull("kouling");
        query.ne("state", 1);
        couponMapper.selectPage(pageData, query);
        initList(pageData.getRecords());
        int pageCount = pageData.getTotalPage();
        log.info("initTkl begin pageCount={}", pageCount);
        int index = 1;
        while (index++ < pageCount) {
            pageData.setCurrent(index);
            couponMapper.selectPage(pageData, query);
            initList(pageData.getRecords());
        }
    }

    private void initList(List<Coupon> list) {
        if (CollectionUtils.isEmpty(list))
            return;
        Wrapper<Coupon> query = new EntityWrapper<>();
        query.in("id", list.stream().map(Coupon::getId).collect(Collectors.toList()));
        Coupon update = new Coupon();
        update.setState(1);
        couponMapper.update(update, query);
    }

    @Scheduled(fixedRate = 1000 * 60 * 2)
    public void writeTkl() {
        PageData<Coupon> pageData = new PageData<>(60);
        Wrapper<Coupon> query = new EntityWrapper<>();
        query.isNull("kouling");
        query.eq("state", 1);
        couponMapper.selectPage(pageData, query);
        initTkl(pageData.getRecords());
    }

    private void initTkl(List<Coupon> list) {
        if (CollectionUtils.isEmpty(list))
            return;
        list.forEach(c -> {
            String tkl = tklService.queryTkl(c.getItemName(), c.getUurl());
            if (StringUtils.isNotEmpty(tkl))
                couponMapper.updateTkl(tkl, c.getId());
            else
                log.info("query_tkl_error");
        });
    }

}
