package com.wyy.start.coupon.weixin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.wyy.start.coupon.mapper.CouponMapper;
import com.wyy.start.coupon.mapper.entity.Coupon;
import com.wyy.start.coupon.service.TulingService;
import com.wyy.start.coupon.utils.FileUtils;

import cn.zhouyafeng.itchat4j.api.MessageTools;
import cn.zhouyafeng.itchat4j.api.WechatTools;
import cn.zhouyafeng.itchat4j.beans.BaseMsg;
import cn.zhouyafeng.itchat4j.beans.RecommendInfo;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import cn.zhouyafeng.itchat4j.utils.enums.MsgTypeEnum;
import cn.zhouyafeng.itchat4j.utils.tools.DownloadTools;
import lombok.extern.slf4j.Slf4j;

/**
 * All rights Reserved,Designed By www.freemud.cn
 *
 * @Title: WxMsgHander
 * @Package com.wyy.start.coupon.weixin;
 * @Description:
 * @Author: Administrator
 * @Date: 2019/5/12 10:33
 * @Version V1.0
 * @Copyright:2018www.freemud.cn Inc.All rights reserved. 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
@Slf4j
@Component
public class WxMsgHandler implements IMsgHandlerFace {

    @Autowired
    private CouponMapper  couponMapper;

    @Autowired
    private TulingService tulingService;

    @Override
    public String textMsgHandle(BaseMsg msg) {
        if (!msg.isGroupMsg()) {
            String text = msg.getText();
            if ("wyylogout".equals(text)) {
                WechatTools.logout();
            }
            if (text.startsWith("买")) {
                String product = text.substring(1);
                Wrapper<Coupon> wrapper = new EntityWrapper<>();
                wrapper.like("itemName", product);
                List<Coupon> coupons = couponMapper.selectList(wrapper);
                if (coupons.isEmpty()) {
                    return "暂无优惠券信息";
                } else {
                    log.info("find coupon");
                    StringBuilder sb = new StringBuilder("商品列表：\n");
                    int index = 1;
                    for (Coupon c : coupons) {
                        sb.append(index++).append("----").append("【名称】 ").append(c.getItemName()).append("\n");
                        if (StringUtils.isNotEmpty(c.getKouling())) {
                            sb.append("【淘口令】 ").append(c.getKouling()).append("\n");
                        } else {
                            sb.append("【优惠券连接】 ").append(c.getUurl());
                        }

                    }
                    return sb.toString();
                }
            }
            return tulingService.chat(text);
        }
        return null;
    }

    @Override
    public String picMsgHandle(BaseMsg msg) {
        String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());// 这里使用收到图片的时间作为文件名
        String picPath = FileUtils.basePath + File.separator + fileName + ".jpg"; // 调用此方法来保存图片
        DownloadTools.getDownloadFn(msg, MsgTypeEnum.PIC.getType(), picPath); // 保存图片的路径
        return "图片保存成功:" + picPath;
    }

    @Override
    public String voiceMsgHandle(BaseMsg msg) {
        String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        String voicePath = FileUtils.basePath + File.separator + fileName + ".mp3";
        DownloadTools.getDownloadFn(msg, MsgTypeEnum.VOICE.getType(), voicePath);
        return "声音保存成功:" + voicePath;
    }

    @Override
    public String viedoMsgHandle(BaseMsg msg) {
        String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        String viedoPath = FileUtils.basePath + File.separator + fileName + ".mp4";
        DownloadTools.getDownloadFn(msg, MsgTypeEnum.VIEDO.getType(), viedoPath);
        return "视频保存成功:" + viedoPath;
    }

    @Override
    public String nameCardMsgHandle(BaseMsg msg) {
        return "收到名片消息";
    }

    @Override
    public void sysMsgHandle(BaseMsg msg) { // 收到系统消息
        String text = msg.getContent();
        log.info(text);
    }

    @Override
    public String verifyAddFriendMsgHandle(BaseMsg msg) {
        MessageTools.addFriend(msg, true);
        RecommendInfo recommendInfo = msg.getRecommendInfo();
        String nickName = recommendInfo.getNickName();
        String province = recommendInfo.getProvince();
        String city = recommendInfo.getCity();
        String text = "你好，来自" + province + city + "的" + nickName + "， 欢迎添加我为好友！\n" + "发送买XXX可查询有优惠商品";
        return text;
    }

    @Override
    public String mediaMsgHandle(BaseMsg msg) {
        return null;
    }
}
