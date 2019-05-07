package com.wyy.start.coupon;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class CouponApplicationTests {

    @Test
    public void contextLoads() throws Exception {


        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("E:\\1.xls"));
        ExcelListener excelListener = new ExcelListener();
        EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1), excelListener);
        inputStream.close();
        excelListener.getData().forEach(System.out::println);
    }

}

class ExcelListener extends AnalysisEventListener {
    private List<Object> data = new ArrayList<>();

    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        data.add(o);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("read end");
    }

    public List<Object> getData() {
        return data;
    }
}
