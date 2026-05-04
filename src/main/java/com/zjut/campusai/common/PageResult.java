package com.zjut.campusai.common;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageResult<T>{

    private final List<T> content;   //当前页数据
    private final int page;
    private final int size;
    private final long totalElements;
    private final int totalPages;

    public PageResult( List<T> content,long totalElements, int totalPages, int page, int size) {
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.content = content;
        this.page = page;
        this.size = size;
    }

    public static <T> PageResult<T> of(Page<T> page) {
        return new PageResult<>(page.getContent(),page.getTotalElements(),page.getTotalPages(),page.getNumber(),page.getSize());
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<T> getContent() {
        return content;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }
}
