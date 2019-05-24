package com.bhayu.app.news;

public interface NewsUseCase {
    void executeGetNews(String keyword, String email, int page, int page_size, String type) throws Exception;
}
