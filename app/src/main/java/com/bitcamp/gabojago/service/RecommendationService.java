package com.bitcamp.gabojago.service;

import com.bitcamp.gabojago.vo.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;


public interface RecommendationService {

  // recommendationAdd
  void recommendationAdd(Recommendation recommendation) throws Exception;

  // recommendationList
  List<Recommendation> recommendationList() throws Exception;

  List<JangSoReviewAttachedFile> recommendationAttachedFiles() throws Exception;

  // Origianl
  List<Recommendation> recommendationListOrderByRecent() throws Exception;
  List<Recommendation> recommendationListOrderByComments() throws Exception;
  List<Recommendation> recommendationListOrderByCnt() throws Exception;
  // Alone
  List<Recommendation> recommendationListOrderByRecentForAlone() throws Exception;
  List<Recommendation> recommendationListOrderByCommentsForAlone() throws Exception;
  List<Recommendation> recommendationListOrderByCntForAlone() throws Exception;
  // Couple
  List<Recommendation> recommendationListOrderByRecentForCouple() throws Exception;
  List<Recommendation> recommendationListOrderByCommentsForCouple() throws Exception;
  List<Recommendation> recommendationListOrderByCntForCouple() throws Exception;
  // Family
  List<Recommendation> recommendationListOrderByRecentForFamily() throws Exception;
  List<Recommendation> recommendationListOrderByCommentsForFamily() throws Exception;
  List<Recommendation> recommendationListOrderByCntForFamily() throws Exception;
  // Friend
  List<Recommendation> recommendationListOrderByRecentForFriend() throws Exception;
  List<Recommendation> recommendationListOrderByCommentsForFriend() throws Exception;
  List<Recommendation> recommendationListOrderByCntForFriend() throws Exception;

  // 전체보기용
  List<Recommendation> recommendationListOrderByRecentAll() throws Exception;
  List<Recommendation> recommendationListOrderByCommentsAll() throws Exception;
  List<Recommendation> recommendationListOrderByCntAll() throws Exception;

  // recommendationDetail - 1
  Recommendation getRecommendation(int recono) throws Exception;

  // recommendationDetail - 2
  List<JangSoReview> getJangSoReviewList(int recono) throws Exception;

  // recommendationDisable
  boolean disableRecommend(int recono) throws Exception;

  // recommendationUpdate
  void recommendationUpdate(Recommendation recommendation) throws Exception;

  // Comment Insert
  void setCntRecommendation(int recono) throws Exception;

  void recommendationReportAdd(String id, int recono, String rsn) throws Exception;

  int countReportById(String reportedId) throws Exception;

  void updateStatus(Member reportedUser) throws Exception;

  boolean checkCorrectUser(String id) throws Exception;

  int countRecommendationReport(int recono);

  //paging
  int getTotal();

  List<Recommendation> recommendationListPageOrderByRecent(int displayPost, int postNum) throws Exception;
  List<Recommendation> recommendationListPageOrderByComments(int displayPost, int postNum) throws Exception;
  List<Recommendation> recommendationListPageOrderByCnt(int displayPost, int postNum) throws Exception;
}