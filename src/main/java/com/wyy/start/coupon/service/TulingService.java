package com.wyy.start.coupon.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyy.start.coupon.feign.TulingClient;
import com.wyy.start.coupon.weixin.TulingResult;

/**
 * All rights Reserved,Designed By www.freemud.cn
 *
 * @Title: TulingService
 * @Package com.wyy.start.coupon.weixin;
 * @Description:
 * @Author: Administrator
 * @Date: 2019/5/12 21:59
 * @Version V1.0
 * @Copyright:2018www.freemud.cn Inc.All rights reserved.
 * 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
@Service
public class TulingService {

    @Autowired
    private TulingClient tulingClient;

    public static final String KEY = "4ee4247a15824f01aa9bd3c4d93701ef";

    private String reqTpl = "{\n" +
            "\t\"reqType\":0,\n" +
            "    \"perception\": {\n" +
            "        \"inputText\": {\n" +
            "            \"text\": \"%s\"\n" +
            "        }\n" +
            "    },\n" +
            "    \"userInfo\": {\n" +
            "        \"apiKey\": \"4ee4247a15824f01aa9bd3c4d93701ef\",\n" +
            "        \"userId\": \"b50e75774d2d910b\"\n" +
            "    }\n" +
            "}";

    public String chat(String msg) {
        String resp = tulingClient.chat(String.format(reqTpl, msg));
        TulingResult result = JSON.parseObject(resp, TulingResult.class);
        return result.getResults().get(0).getValues().getText();
    }
}
