package com.bitcamp.gabojago.dao;

import com.bitcamp.gabojago.vo.Exhibition;
import com.bitcamp.gabojago.vo.ExhibitionReview;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExhibitionReviewDao {
  // 리뷰 조회
  List<ExhibitionReview> exhibitionReviewList(int exno);

  ExhibitionReview exhibitionReviewSelect (int rvno);

  // 리뷰 입력
int exhibitionReviewInsert(ExhibitionReview exhibitionReview);

  int exhibitionReviewDelete(int rvno);

int exhibitionReviewUpdate(ExhibitionReview exhibitionReview);

int deleteByMember(int ID);


 int deleteReviewByExhibition (int exno);

/*  int deleteReviewsByMemberexhibitions(int ID);*/

}
