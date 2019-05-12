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
@FeignClient(value = "ds21", url = "https://api.open.21ds.cn")
public interface DsClient {


    String apikey = "f884aa1b-7e36-e22d-d752-1e8622a42ad0";


    @GetMapping(value = "/apiv1/createtkl", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String convertTkl(@RequestParam("apkey") String apkey, @RequestParam("kltext") String kltext,
                      @RequestParam("klurl") String klurl);
}
