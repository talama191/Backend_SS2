package com.example.ecommercebackend.Entities.SearchFilter;

import jakarta.persistence.Entity;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

public class ProductSearchFilter {
    private String keyword;
    private List<Integer> category_ids;
    private List<Integer> brand_ids;
    private Integer perPage;
    private Integer pageNum;
    private String sortType;
    private String sortField;

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }


    public ProductSearchFilter() {

    }

    public ProductSearchFilter(String keyword) {
        this.keyword = keyword;
    }


    public String getKeyword() {
        return "%" + keyword + "%";
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Integer> getCategory_ids() {
        return category_ids;
    }

    public void setCategory_ids(List<Integer> category_ids) {
        this.category_ids = category_ids;
    }

    public List<Integer> getBrand_ids() {
        return brand_ids;
    }

    public void setBrand_ids(List<Integer> brand_ids) {
        this.brand_ids = brand_ids;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
