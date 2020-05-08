package com.java.website.myblog.entity;

public class BlogCategoryCount {
    private Integer categoryId;

    private String categoryName;

    private Integer categoryCount;

    private String categoryBlog;

    public String getCategoryBlog() {
        return categoryBlog;
    }

    public void setCategoryBlog(String categoryBlog) {
        this.categoryBlog = categoryBlog;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryCount() {
        return categoryCount;
    }

    public void setCategoryCount(Integer categoryCount) {
        this.categoryCount = categoryCount;
    }
}
