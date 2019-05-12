package com.wyy.start.coupon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyy.start.coupon.utils.FileUtils;
import com.wyy.start.coupon.weixin.WxMsgHandler;

import cn.zhouyafeng.itchat4j.Wechat;

/**
 * All rights Reserved,Designed By www.freemud.cn
 *
 * @Title: WxService
 * @Package com.wyy.start.coupon.service;
 * @Description:
 * @Author: Administrator
 * @Date: 2019/5/12 22:26
 * @Version V1.0
 * @Copyright:2018www.freemud.cn Inc.All rights reserved. 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
@Service
public class WxService {
    public static final String qrCodePath = FileUtils.basePath + "QR.jpg";

    @Autowired
    private WxMsgHandler       wxMsgHandler;

    public void login() {
        Wechat wechat = new Wechat(wxMsgHandler, FileUtils.basePath);
        wechat.start();
    }

}
