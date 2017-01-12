package com.malcolm.oes.model;

import com.malcolm.oes.util.PropertyUtil;

public class PageBean {

    private final String KEY_PAGE_SIZE = "pageBean.pageSize";

    private int startRecord;
    private int nowPage;
    private int pageCount;
    private int pageSize;
    private int totalRecord;

    public PageBean() {
    }

    public PageBean(int nowPage, int pageCount, int pageSize, int totalRecord) {
        this.nowPage = nowPage;
        this.pageCount = pageCount;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
    }

    public int getStartRecord() {
        startRecord = (getNowPage() - 1) * getPageSize();
        return startRecord;
    }

    public void setStartRecord(int startRecord) {
        this.startRecord = startRecord;
    }

    public int getNowPage() {
        if (nowPage < 1) {
            nowPage = 1;
        }
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getPageCount() {
        pageCount = (getTotalRecord() - 1) / getPageSize() + 1;
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        if (pageSize == 0) {
            String size = PropertyUtil.getProperty(KEY_PAGE_SIZE);
            pageSize = Integer.parseInt(size);
        }
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

}
