package com.wyy.start.coupon.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.wyy.start.coupon.component.ExcelListener;
import com.wyy.start.coupon.mapper.CouponMapper;
import com.wyy.start.coupon.mapper.entity.Coupon;
import com.wyy.start.coupon.service.WxService;
import com.wyy.start.coupon.utils.FileUtils;

/**
 * All rights Reserved,Designed By www.freemud.cn
 *
 * @Title: IndexController
 * @Package com.wyy.start.coupon.controller;
 * @Description:
 * @Author: Administrator
 * @Date: 2019/5/5 22:08
 * @Version V1.0
 * @Copyright:2018www.freemud.cn Inc.All rights reserved. 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
@Controller
public class IndexController {

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private WxService    wxService;

    @RequestMapping("/")
    public String index() {
        int count = couponMapper.selectCount(null);
        System.out.println(count);
        return "index";
    }

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return "文件是空的";
        }

        Wrapper<Coupon> wrapper = new EntityWrapper<>();
        wrapper.eq("batchNo", file.getOriginalFilename());
        int count = couponMapper.selectCount(wrapper);
        if (count > 0) {
            return "exists " + count;
        }

        File couponFile = FileUtils.saveFile(file, null, UUID.randomUUID().toString() + ".xls");

        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(couponFile));
        ExcelListener excelListener = new ExcelListener();
        EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1), excelListener);
        inputStream.close();

        excelListener.getData().stream().map(l -> {
            Coupon c = new Coupon();
            c.setItemId(l.get(0));
            c.setItemName(l.get(1));
            c.setPic(l.get(2));
            c.setDetail(l.get(3));
            c.setTopClass(l.get(4));
            c.setTbkUrl(l.get(5));
            c.setPrice(multi100(l.get(6)));
            c.setSaleCount(Integer.parseInt(l.get(7)));
            c.setRate(multi100(l.get(8)));
            c.setIncome(multi100(l.get(9)));
            c.setWw(l.get(10));
            c.setWwid(l.get(11));
            c.setStore(l.get(12));
            c.setPlatform(l.get(13));
            c.setCouponId(l.get(14));
            c.setCouponCount(Integer.parseInt(l.get(15)));
            c.setLeftCount(Integer.parseInt(l.get(16)));
            c.setCouponPrice(l.get(17));
            try {
                c.setStartDate(DateUtils.parseDate(l.get(18), "yyyy-MM-dd"));
                c.setEndDate(DateUtils.parseDate(l.get(19), "yyyy-MM-dd"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.setCouponUrl(l.get(20));
            c.setUurl(l.get(21));
            c.setState(1);
            c.setBatchNo(file.getOriginalFilename());
            return c;
        }).forEach(couponMapper::insert);
        return "ok " + couponMapper.selectCount(wrapper);
    }

    private Integer multi100(String price) {
        try {
            return (int) (Float.parseFloat(price) * 100);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @GetMapping(value = "/wxlogin", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public String wxLogin() {
        wxService.login();
        return "login ok";
    }

    @GetMapping(value = "/qrcode", produces = MediaType.IMAGE_JPEG_VALUE)
    public void qrcode(HttpServletResponse response) throws Exception {
        Files.copy(Paths.get(WxService.qrCodePath), response.getOutputStream());
    }
}
