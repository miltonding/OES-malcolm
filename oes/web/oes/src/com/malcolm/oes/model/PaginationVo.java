package com.malcolm.oes.model;

public class PaginationVo {

    private Pagination pagination;
    private String keyword;
    private String currentOrder;
    private String orderParam;
    private String startDate;
    private String endDate;

    public PaginationVo() {
    }

    public PaginationVo(Pagination pagination, String keyword, String currentOrder, String orderParam) {
        this.pagination = pagination;
        this.keyword = keyword;
        this.currentOrder = currentOrder;
        this.orderParam = orderParam;
    }

    public PaginationVo(Pagination pagination, String keyword, String currentOrder, String orderParam,
            String startDate, String endDate) {
        super();
        this.pagination = pagination;
        this.keyword = keyword;
        this.currentOrder = currentOrder;
        this.orderParam = orderParam;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(String currentOrder) {
        this.currentOrder = currentOrder;
    }

    public String getOrderParam() {
        return orderParam;
    }

    public void setOrderParam(String orderParam) {
        this.orderParam = orderParam;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
