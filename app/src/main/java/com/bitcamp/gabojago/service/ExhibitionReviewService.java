package com.bitcamp.gabojago.service;

import com.bitcamp.gabojago.vo.Exhibition;
import com.bitcamp.gabojago.vo.ExhibitionReview;

import java.util.List;


public interface ExhibitionReviewService {

  List<ExhibitionReview> exhibitionReviewList(int exno) throws Exception;

void exhibitionReviewInsert(ExhibitionReview exhibitionReview) throws Exception;
  boolean exhibitionReviewDelete(int rvno) throws Exception;

boolean exhibitionReviewUpdate(ExhibitionReview exhibitionReview) throws Exception;

ExhibitionReview get(int rvno) throws Exception;
  int deleteReviewByExhibition (int exno) throws Exception;

}

