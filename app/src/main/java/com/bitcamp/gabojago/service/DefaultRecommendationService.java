package com.bitcamp.gabojago.service;

import com.bitcamp.gabojago.dao.MemberDao;
import com.bitcamp.gabojago.dao.recommendation.RecommendationDao;
import com.bitcamp.gabojago.dao.recommendation.ReportDao;
import com.bitcamp.gabojago.vo.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultRecommendationService implements RecommendationService  {
  @Autowired
  RecommendationDao recommendationDao;

  @Autowired
  MemberDao memberDao;

  @Autowired
  ReportDao reportDao;

  // recommendationAdd
  @Transactional
  @Override
  public void recommendationAdd(Recommendation recommendation) throws Exception {
    // 1) 코스추천글 등록
    if (recommendationDao.recommendationAdd(recommendation) == 0) {
      throw new Exception("코스추천글 등록 실패!");
    }

    // 자동증가한 코스추천글 recono 받아오기
    int recono = recommendation.getRecono();

    // 자동증가한 장소리뷰 번호를 받을 변수 준비
    int prvno;

    for (int i = 0; i < recommendation.getJangSoReviews().size(); i++) {
      // 각각의 장소리뷰에 코스추천글 번호 set 하기
      recommendation.getJangSoReviews().get(i).setRecono(recono);

      // 2) 각각의 장소리뷰 insert 하기
      if (recommendationDao.jangSoReviewAdd(recommendation.getJangSoReviews().get(i)) == 0) {
        throw new Exception("장소리뷰 등록 실패!");
      }

      // 각각의 장소리뷰를 insert 하면서 자동증가한 prvno 받아오기
      prvno = recommendation.getJangSoReviews().get(i).getPrvno();

      for (int j = 0; j < recommendation.getJangSoReviews().get(i).getAttachedFiles().size(); j++) {
        // 장소리뷰를 insert 하면서 자동증가한 prvno를 장소리뷰 첨부파일에 set 하기
        recommendation.getJangSoReviews().get(i).getAttachedFiles().get(j).setPrvno(prvno);

        // 3) 장소리뷰 첨부파일 insert 하기
        if (recommendationDao.jangSoReviewAttachedFileAdd(
            recommendation.getJangSoReviews().get(i).getAttachedFiles().get(j)
        ) == 0) {
          throw new Exception("장소리뷰첨부파일 등록 실패!");
        }
      }
    }
  }

  // recommendationList
  @Override
  public List<Recommendation> recommendationList() throws Exception {
    return recommendationDao.recommendationList();
  }

  public List<JangSoReviewAttachedFile> recommendationAttachedFiles() throws Exception {
    return recommendationDao.recommendationAttachedFiles();
  }

  // Original
  @Override
  public List<Recommendation> recommendationListOrderByRecent() throws Exception {
    return recommendationDao.recommendationListOrderByRecent();
  }
  @Override
  public List<Recommendation> recommendationListOrderByComments() throws Exception{
    return recommendationDao.recommendationListOrderByComments();
  }
  @Override
  public List<Recommendation> recommendationListOrderByCnt() throws Exception{
    return recommendationDao.recommendationListOrderByCnt();
  }

  // Alone
  @Override
  public List<Recommendation> recommendationListOrderByRecentForAlone() throws Exception {
    return recommendationDao.recommendationListOrderByRecentForAlone();
  }
  @Override
  public List<Recommendation> recommendationListOrderByCommentsForAlone() throws Exception{
    return recommendationDao.recommendationListOrderByCommentsForAlone();
  }
  @Override
  public List<Recommendation> recommendationListOrderByCntForAlone() throws Exception{
    return recommendationDao.recommendationListOrderByCntForAlone();
  }


  // Couple
  @Override
  public List<Recommendation> recommendationListOrderByRecentForCouple() throws Exception {
    return recommendationDao.recommendationListOrderByRecentForCouple();
  }
  @Override
  public List<Recommendation> recommendationListOrderByCommentsForCouple() throws Exception{
    return recommendationDao.recommendationListOrderByCommentsForCouple();
  }
  @Override
  public List<Recommendation> recommendationListOrderByCntForCouple() throws Exception{
    return recommendationDao.recommendationListOrderByCntForCouple();
  }

  // Family
  @Override
  public List<Recommendation> recommendationListOrderByRecentForFamily() throws Exception {
    return recommendationDao.recommendationListOrderByRecentForFamily();
  }
  @Override
  public List<Recommendation> recommendationListOrderByCommentsForFamily() throws Exception{
    return recommendationDao.recommendationListOrderByCommentsForFamily();
  }
  @Override
  public List<Recommendation> recommendationListOrderByCntForFamily() throws Exception{
    return recommendationDao.recommendationListOrderByCntForFamily();
  }

  // Friend
  @Override
  public List<Recommendation> recommendationListOrderByRecentForFriend() throws Exception {
    return recommendationDao.recommendationListOrderByRecentForFriend();
  }
  @Override
  public List<Recommendation> recommendationListOrderByCommentsForFriend() throws Exception{
    return recommendationDao.recommendationListOrderByCommentsForFriend();
  }
  @Override
  public List<Recommendation> recommendationListOrderByCntForFriend() throws Exception{
    return recommendationDao.recommendationListOrderByCntForFriend();
  }

  // 전체보기용
  public List<Recommendation> recommendationListOrderByRecentAll() throws Exception {
    return recommendationDao.recommendationListOrderByRecentAll();
  }
  public List<Recommendation> recommendationListOrderByCommentsAll() throws Exception {
    return recommendationDao.recommendationListOrderByCommentsAll();
  }
  public List<Recommendation> recommendationListOrderByCntAll() throws Exception {
    return recommendationDao.recommendationListOrderByCntAll();
  }


  // recommendationDetail - 1
  @Override
  public Recommendation getRecommendation(int recono) throws Exception {
    return recommendationDao.getRecommendation(recono);
  }

  // recommendationDetail - 2
  @Override
  public List<JangSoReview> getJangSoReviewList(int recono) throws Exception {
    return recommendationDao.getJangSoReviewList(recono);
  }

  public void setCntRecommendation(int recono) throws Exception {
    recommendationDao.setCntRecommendation(recono);
  }

  // recommendationDisable
  @Override
  public boolean disableRecommend(int recono) throws Exception {
    return recommendationDao.disableRecommend(recono) > 0;
  }

  // recommendationUpdate
  @Transactional
  @Override
  public void recommendationUpdate(Recommendation recommendation) throws Exception {
    // 1) 코스추천글 업데이트
    if (recommendationDao.recommendationUpdate(recommendation) == 0) {
      throw new Exception("코스추천글 업데이트 실패!");
    }

    // 2) 원래 코스추천글의 장소리뷰 데이터 삭제하기
    // 코스추천글의 번호 받아오기
    int recono = recommendation.getRecono();

    // 코스추천글 번호에 해당하는 장소리뷰첨부파일 찾아서 삭제
    recommendationDao.deleteFilesByRecono(recono);

    // 코스추천글 번호에 해당하는 장소리뷰 찾아서 삭제
    recommendationDao.deleteJangSoReviewsByRecono(recono);

    // 3) 각각의 장소리뷰 insert 하기
    // 자동증가한 장소리뷰 번호를 받을 변수 준비
    int prvno;

    for (int i = 0; i < recommendation.getJangSoReviews().size(); i++) {
      // 각각의 장소리뷰에 코스추천글 번호 set 하기
      recommendation.getJangSoReviews().get(i).setRecono(recono);

      // 각각의 장소리뷰 insert 하기
      if (recommendationDao.jangSoReviewAdd(recommendation.getJangSoReviews().get(i)) == 0) {
        throw new Exception("장소리뷰 등록 실패!");
      }

      // 각각의 장소리뷰를 insert 하면서 자동증가한 prvno 받아오기
      prvno = recommendation.getJangSoReviews().get(i).getPrvno();

      for (int j = 0; j < recommendation.getJangSoReviews().get(i).getAttachedFiles().size(); j++) {
        // 장소리뷰를 insert 하면서 자동증가한 prvno를 장소리뷰 첨부파일에 set 하기
        recommendation.getJangSoReviews().get(i).getAttachedFiles().get(j).setPrvno(prvno);

        // 4) 장소리뷰 첨부파일 insert 하기
        if (recommendationDao.jangSoReviewAttachedFileAdd(
            recommendation.getJangSoReviews().get(i).getAttachedFiles().get(j)
        ) == 0) {
          throw new Exception("장소리뷰첨부파일 등록 실패!");
        }
      }
    }
  }

  // recommendationReportAdd - 신고처리
  @Override
  public void recommendationReportAdd(String id, int recono, String rsn) throws Exception {
    Report report = new Report();
    report.setId(id);
    report.setRecono(recono);
    report.setRsn(rsn);
    reportDao.recommendationReportAdd(report);
  }

  // 신고당한 user의 과거행적 조회
  @Override
  public int countReportById(String reportedId) throws Exception {
    return reportDao.countReportById(reportedId);
  }

  // 신고당한 유저 제재하기 위해 상태 변경
  @Override
  public void updateStatus(Member reportedUser) throws Exception {
    if (memberDao.updateStatus(reportedUser) == 0) {
      throw new Exception("유저상태 변경 실패!");
    }
  }

  // 이미 신고를 5회 받은 게시글은 신고할 수 없다.
  @Override
  public int countRecommendationReport(int recono) {
    return reportDao.countRecommendationReport(recono);
  }

  @Override
  public boolean checkCorrectUser(String id) throws Exception {
    return (memberDao.checkCorrectUser(id) == null);
  }

  @Override
  public int getTotal() {
    return recommendationDao.getTotal();
  }

  @Override
  public List<Recommendation> recommendationListPageOrderByRecent(int displayPost, int size) throws Exception{
    return recommendationDao.recommendationListPageOrderByRecent(displayPost, size);
  }

  @Override
  public List<Recommendation> recommendationListPageOrderByComments(int displayPost, int size) throws Exception{
    return recommendationDao.recommendationListPageOrderByComments(displayPost, size);
  }

  @Override
  public List<Recommendation> recommendationListPageOrderByCnt(int displayPost, int size) throws Exception{
    return recommendationDao.recommendationListPageOrderByCnt(displayPost, size);
  }

}
