package com.bitcamp.gabojago.dao;


import com.bitcamp.gabojago.vo.Recommendation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyRecommendationDao {

    // 게시물 총개수
    int count(String id);

    // 게시물 목록 + paging
    List<Recommendation> myRecommendationListPage(@Param("displayPost") int displayPost, @Param("size") int size, @Param("id") String id);

}
