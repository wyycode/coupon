package com.wyy.start.coupon.dto;

import java.util.List;

/**
 * All rights Reserved,Designed By www.freemud.cn
 *
 * @Title: PageData
 * @Package com.wyy.start.coupon.dto;
 * @Description:
 * @Author: Administrator
 * @Date: 2019/5/10 23:44
 * @Version V1.0
 * @Copyright:2018www.freemud.cn Inc.All rights reserved. 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
public class PageData<T> {
    private int     totalCount;
    private int     totalPage;
    private int     current = 1;
    private int     size    = 10;
    private List<T> records;

    public PageData(){}
    public PageData(int size){
        this.size = size;
    }

    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 调用过setTotalCount后，自动计算totalPage不需要再次调用setTotalPage
     *
     * @param totalCount
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.setTotalPage((totalCount + size - 1) / size);
    }

    public int getTotalPage() {
        return totalPage;
    }

    /**
     * 调用过setTotalCount后，自动计算totalPage不需要再次调用setTotalPage
     *
     * @param totalPage
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
