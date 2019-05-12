package com.wyy.start.coupon.component;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

/**
 * All rights Reserved,Designed By www.freemud.cn
 *
 * @Title: ExcelListener
 * @Package com.wyy.start.coupon.component;
 * @Description:
 * @Author: Administrator
 * @Date: 2019/5/10 20:57
 * @Version V1.0
 * @Copyright:2018www.freemud.cn Inc.All rights reserved. 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
public class ExcelListener extends AnalysisEventListener {
    private List<List<String>> data = new ArrayList<>();

    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        data.add((List<String>) o);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("read end");
    }

    public List<List<String>> getData() {
        return data;
    }
}
