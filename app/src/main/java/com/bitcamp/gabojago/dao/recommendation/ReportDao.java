package com.bitcamp.gabojago.dao.recommendation;

import com.bitcamp.gabojago.vo.Report;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReportDao {

  // recommendationReportAdd - 신고처리
  void recommendationReportAdd(Report report);

  // 신고당한 user의 과거행적 조회
  int countReportById(String reportedId);

  // 이미 신고를 5회 받은 게시글은 신고할 수 없다.
  int countRecommendationReport(int recono);
}
