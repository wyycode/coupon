package com.wyy.start.coupon.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.ApplicationHome;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * All rights Reserved,Designed By www.freemud.cn
 *
 * @Title: FileUtils
 * @Package com.wyy.start.coupon.utils;
 * @Description:
 * @Author: Administrator
 * @Date: 2019/5/5 22:19
 * @Version V1.0
 * @Copyright:2018www.freemud.cn Inc.All rights reserved.
 * 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
public class FileUtils {
    public static final String basePath = new ApplicationHome(FileUtils.class).getSource().getParentFile().getPath() + "/files/";

    static {
        File baseDir = new File(basePath);
        if (!baseDir.exists()) {
            baseDir.mkdirs();
        }
    }

    public static String saveFile(MultipartFile file, String path, String filename) {
        try {
            String dir = basePath;
            if (StringUtils.isNotEmpty(path)) {
                File tmp = new File(basePath + path);
                if (!tmp.exists()) {
                    tmp.mkdirs();
                }
                dir = tmp.getAbsolutePath();
            }

            File dest = new File(dir + filename);
            if (!dest.exists()) {
                dest.createNewFile();
            }
            file.transferTo(dest);
            return dest.getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
