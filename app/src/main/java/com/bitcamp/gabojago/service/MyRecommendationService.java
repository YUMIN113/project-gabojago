package com.bitcamp.gabojago.service;

import com.bitcamp.gabojago.vo.Recommendation;

import java.util.List;

public interface MyRecommendationService {

    // 게시물 총개수
    int count(String id) throws Exception;

    // 게시물 목록 + paging
    List<Recommendation> myRecommendationListPage(int displayPost, int size, String id) throws Exception;
}
