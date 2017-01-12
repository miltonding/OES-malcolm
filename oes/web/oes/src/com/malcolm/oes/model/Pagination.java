package com.malcolm.oes.model;

import com.malcolm.oes.util.PropertyUtil;

public class Pagination {

    private final String KEY_PAGE_SIZE = "pagination.pageSize";

    // The star record.
    private int startRecord;
    // The current page.
    private int nowPage;
    // The page count by query.
    private int pageCount;
    // The page size was defined in app.properties.
    private int pageSize;
    // The total record by query.
    private int totalRecord;

    public Pagination() {
    }

    public Pagination(int nowPage, int pageCount, int pageSize, int totalRecord) {
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
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        if (nowPage <= 0) {
            this.nowPage = 1;
        } else {
            this.nowPage = nowPage;
        }
    }

    public int getPageCount() {
        pageCount = (getTotalRecord() - 1) / getPageSize() + 1;
        if (pageCount == 0) {
            pageCount = 1;
        }
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        if (pageSize == 0) {
            // Read the app.properties to get the page size.
            String size = PropertyUtil.getProperty(KEY_PAGE_SIZE);
            pageSize = Integer.parseInt(size);
        }
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 20) {
            this.pageSize = 20;
        } else if (pageSize < 0) {
            this.pageSize = 10;
        } else {
            this.pageSize = pageSize;
        }
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
        if (nowPage > getPageCount() && getPageCount() != 0) {
            this.nowPage = getPageCount();
        }
    }

}
