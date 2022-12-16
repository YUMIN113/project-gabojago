package com.bitcamp.gabojago.dao;

import com.bitcamp.gabojago.vo.ExhibitionReview;
import com.bitcamp.gabojago.vo.JangComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyCommentsDao {

    // paging_게시물 총개수 (코스추천 댓글)
    int myRecommendationCommentCount(String id);

    // 게시물 목록 + paging (코스추천 댓글)
    List<JangComment> myRecommendationCommentListPage(@Param("displayPost") int displayPost, @Param("size") int size, @Param("id") String id);

    // paging_게시물 총개수 (전시회 댓글)
    int count(String id);

    // 게시물 목록 + paging (전시회 댓글)
    List<ExhibitionReview> myExhibitionReviewListPage(@Param("displayPost") int displayPost, @Param("size") int size, @Param("id") String id);

}
