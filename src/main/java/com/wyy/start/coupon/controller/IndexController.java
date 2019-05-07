package com.wyy.start.coupon.controller;

import com.wyy.start.coupon.utils.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * All rights Reserved,Designed By www.freemud.cn
 *
 * @Title: IndexController
 * @Package com.wyy.start.coupon.controller;
 * @Description:
 * @Author: Administrator
 * @Date: 2019/5/5 22:08
 * @Version V1.0
 * @Copyright:2018www.freemud.cn Inc.All rights reserved.
 * 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "文件是空的";
        }
        return FileUtils.saveFile(file, null, UUID.randomUUID().toString() + ".xls");
    }
}
