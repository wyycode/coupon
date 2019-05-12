package com.wyy.start.coupon.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * All rights Reserved,Designed By www.freemud.cn
 *
 * @Title: DdxClient
 * @Package com.wyy.start.coupon.feign;
 * @Description:
 * @Author: Administrator
 * @Date: 2019/5/10 23:20
 * @Version V1.0
 * @Copyright:2018www.freemud.cn Inc.All rights reserved. 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
@FeignClient(value = "diandanxia", url = "http://api.tbk.dingdanxia.com")
public interface DdxClient {

    String apikey = "EkNGRKFJMSxeF5wOhQLLMrUZCshmHQ9g";

    @GetMapping(value = "/tkl/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String convertTkl(@RequestParam("apikey") String apikey, @RequestParam("title") String title,
            @RequestParam("url") String url);
}
