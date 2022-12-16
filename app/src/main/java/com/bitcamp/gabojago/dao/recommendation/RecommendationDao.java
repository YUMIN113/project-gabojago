package com.bitcamp.gabojago.dao.recommendation;

import com.bitcamp.gabojago.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecommendationDao {

  // recommendationAdd - 1
  int recommendationAdd(Recommendation recommendation);

  // recommendationAdd - 2
  int jangSoReviewAdd(JangSoReview jangSoReview);

  // recommendationAdd - 3
  int jangSoReviewAttachedFileAdd(JangSoReviewAttachedFile jangSoReviewAttachedFile);

  // recommendationList
  List<Recommendation> recommendationList(); // 나중에 날짜 받기

  List<JangSoReviewAttachedFile> recommendationAttachedFiles();

  List<Recommendation> recommendationListOrderByRecent();
  List<Recommendation> recommendationListOrderByComments();
  List<Recommendation> recommendationListOrderByCnt();

  // 혼자
  List<Recommendation> recommendationListOrderByRecentForAlone();
  List<Recommendation> recommendationListOrderByCommentsForAlone();
  List<Recommendation> recommendationListOrderByCntForAlone();

  // 커플
  List<Recommendation> recommendationListOrderByRecentForCouple();
  List<Recommendation> recommendationListOrderByCommentsForCouple();
  List<Recommendation> recommendationListOrderByCntForCouple();

  // 가족
  List<Recommendation> recommendationListOrderByRecentForFamily();
  List<Recommendation> recommendationListOrderByCommentsForFamily();
  List<Recommendation> recommendationListOrderByCntForFamily();

  // 친구
  List<Recommendation> recommendationListOrderByRecentForFriend();
  List<Recommendation> recommendationListOrderByCommentsForFriend();
  List<Recommendation> recommendationListOrderByCntForFriend();

  // 전체보기용
  List<Recommendation> recommendationListOrderByRecentAll();
  List<Recommendation> recommendationListOrderByCommentsAll();
  List<Recommendation> recommendationListOrderByCntAll();

  // recommendationDetail - 1
  Recommendation getRecommendation(int recono);

  // recommendationDetail - 2
  List<JangSoReview> getJangSoReviewList(int recono);

  // recommendationDisable
  int disableRecommend(int recono);

  // recommendationUpdate
  int recommendationUpdate(Recommendation recommendation);

  // deleteFilesByRecono
  int deleteFilesByRecono(int recono);

  // deleteJangSoReviewsByRecono
  int deleteJangSoReviewsByRecono(int recono);

  // comment Insert
  int setCntRecommendation(int recono);

  int getTotal();

  /* 게시판 목록(페이징 적용) */
  List<Recommendation> recommendationListPageOrderByRecent(@Param("displayPost") int displayPost, @Param("size") int size);
  List<Recommendation> recommendationListPageOrderByComments(@Param("displayPost") int displayPost, @Param("size") int size);
  List<Recommendation> recommendationListPageOrderByCnt(@Param("displayPost") int displayPost, @Param("size") int size);

}
