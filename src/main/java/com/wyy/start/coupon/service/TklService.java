package com.wyy.start.coupon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wyy.start.coupon.feign.DdxClient;
import com.wyy.start.coupon.feign.DsClient;

import lombok.extern.slf4j.Slf4j;

/**
 * All rights Reserved,Designed By www.freemud.cn
 *
 * @Title: TklService
 * @Package com.wyy.start.coupon.service;
 * @Description:
 * @Author: Administrator
 * @Date: 2019/5/11 17:49
 * @Version V1.0
 * @Copyright:2018www.freemud.cn Inc.All rights reserved. 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
@Service
@Slf4j
public class TklService {
    @Autowired
    private DdxClient ddxClient;
    @Autowired
    private DsClient  dsClient;

    private boolean   ddxSwitch  = true;

    private boolean   ds21Switch = true;

    public String queryTkl(String title, String url) {
        if (ddxSwitch) {
            String msg = ddxClient.convertTkl(DdxClient.apikey, title, url);
            JSONObject resp = JSON.parseObject(msg);
            log.info("ddxClient = " + resp.toJSONString());
            if (resp.containsKey("data") && resp.containsKey("code") && "200".equals(resp.getString("code"))) {
                JSONObject data = resp.getJSONObject("data");
                if (data.containsKey("data")) {
                    data = data.getJSONObject("data");
                    return data.getString("model");
                }
            } else {
                ddxSwitch = false;
            }
        }

        if (ds21Switch) {
            String msg = dsClient.convertTkl(DsClient.apikey, title, url);
            JSONObject resp = JSON.parseObject(msg);
            log.info("dsClient = " + resp.toJSONString());
            if (resp.containsKey("code") && "200".equals(resp.getString("code")) && resp.containsKey("data")) {
                return resp.getString("data");
            }
            else {
                ds21Switch = false;
            }
        }
        return null;
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void enableSwitch() {
        ddxSwitch = true;
        ds21Switch = true;
    }
}
