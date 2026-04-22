package com.zjut.campusai;

import java.util.List;

public class PageResult<T>{
    private long total;
    private List<T> list;   //当前页数据
    private int currentPage;
    private int pageSize;

    public PageResult(long total, List<T> list, int currentPage,int pageSize ){
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
    }

}
