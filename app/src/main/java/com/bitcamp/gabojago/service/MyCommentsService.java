package com.bitcamp.gabojago.service;

import com.bitcamp.gabojago.vo.ExhibitionReview;
import com.bitcamp.gabojago.vo.JangComment;

import java.util.List;

public interface MyCommentsService {


    // paging_게시물 총개수 (코스추천 댓글)
    int myRecommendationCommentCount(String id) throws Exception;

    // 게시물 목록 + paging (코스추천 댓글)
    List<JangComment> myRecommendationCommentListPage(int displayPost, int size, String id) throws Exception;

    // paging_게시물 총개수 (전시회 댓글)
    int count(String id) throws Exception;

    // 게시물 목록 + paging (전시회 댓글)
    List<ExhibitionReview> myExhibitionReviewListPage(int displayPost, int size, String id) throws Exception;

}
