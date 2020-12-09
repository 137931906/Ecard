package com.cykj.auc.bean;

import java.util.List;

public class PageBean<T> {
    private int curPage;//当前页
    private int prePage;//上一页
    private int nextPage;//下一页
    private int totalPages;//总页
    private int totalRecords;//总记录
    private int pageSize;//当前页数据
    private List<T> list;//存放数据的集合

    public PageBean() {
    }

    public PageBean(int curPage, int totalRecords, int pageSize) {
        this.curPage = curPage;
        this.totalRecords = totalRecords;
        this.pageSize = pageSize;
        //计算总页数
        totalPages =totalRecords%pageSize==0?totalRecords/pageSize:totalRecords/pageSize+1;
        //当总页数为0时没有数据时，单前页设置为0
        if (totalPages==0){
            this.curPage=0;
        }

    }

    public PageBean(int curPage, int prePage, int nextPage, int totalPage, int totalRecords, int pageSize, List<T> list) {
        this.curPage = curPage;
        this.prePage = prePage;
        this.nextPage = nextPage;
        this.totalPages = totalPage;
        this.totalRecords = totalRecords;
        this.pageSize = pageSize;
        this.list = list;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    /**
     * 上一页
     * 当前页等于1或0不能在减
     * @return
     */
    public int getPrePage() {

        return curPage==1||curPage==0?curPage:curPage-1;
    }


    /**
     * 下一页
     * 当前页等于总页数不能在加
     * @return
     */
    public int getNextPage() {
        return curPage==totalPages?totalPages:curPage+1;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPage) {
        this.totalPages = totalPage;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}


