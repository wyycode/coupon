package com.wyy.start.coupon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

/**
 * All rights Reserved,Designed By www.freemud.cn
 *
 * @Title: FeignConfiguration
 * @Package com.wyy.start.coupon.config;
 * @Description:
 * @Author: Administrator
 * @Date: 2019/5/11 18:15
 * @Version V1.0
 * @Copyright:2018www.freemud.cn Inc.All rights reserved. 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
@Configuration
public class FeignConfiguration {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.NONE;
    }
}
