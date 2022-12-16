package com.bitcamp.gabojago.dao;

import com.bitcamp.gabojago.vo.ExhibitionReview;
import com.bitcamp.gabojago.vo.JangComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JangCommentDao {
  // 리뷰 조회
  List<JangComment> jangCommentList(int recono);


  // 리뷰 입력
  int jangCommentInsert(JangComment jangComment);

  int jangCommentDelete(int cmno);


  JangComment getJangCommentByCmno(int cmno);


}
