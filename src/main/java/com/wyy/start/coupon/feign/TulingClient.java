package com.wyy.start.coupon.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * All rights Reserved,Designed By www.freemud.cn
 *
 * @Title: TulingClient
 * @Package com.wyy.start.coupon.feign;
 * @Description:
 * @Author: Administrator
 * @Date: 2019/5/12 21:49
 * @Version V1.0
 * @Copyright:2018www.freemud.cn Inc.All rights reserved. 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */

@FeignClient(value = "tuling123", url = "http://openapi.tuling123.com/openapi/api")
public interface TulingClient {

    @PostMapping(value = "/v2", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String chat(String object);
}
