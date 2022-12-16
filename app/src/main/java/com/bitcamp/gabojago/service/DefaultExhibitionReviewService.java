package com.bitcamp.gabojago.service;

import com.bitcamp.gabojago.dao.ExhibitionReviewDao;
import com.bitcamp.gabojago.vo.ExhibitionReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultExhibitionReviewService implements ExhibitionReviewService {
  @Autowired
  ExhibitionReviewDao exhibitionReviewDao;


  public List<ExhibitionReview> exhibitionReviewList(int exno) throws Exception {
    return exhibitionReviewDao.exhibitionReviewList(exno);
  }


  @Transactional
  @Override
  public void exhibitionReviewInsert(ExhibitionReview exhibitionReview) throws Exception {
    if (exhibitionReviewDao.exhibitionReviewInsert(exhibitionReview) ==0){
      throw new Exception("댓글 등록 실패!");
    }
  }

  @Transactional
  @Override
  public boolean exhibitionReviewDelete(int rvno) throws Exception {
    return exhibitionReviewDao.exhibitionReviewDelete(rvno) >0;
  }


  @Override
  public boolean exhibitionReviewUpdate(ExhibitionReview exhibitionReview) throws Exception {
    if(exhibitionReviewDao.exhibitionReviewUpdate(exhibitionReview) == 0 ){
  return false;
}
    return true;
  }

  @Override
  public ExhibitionReview get(int rvno) throws Exception {
    ExhibitionReview review =  exhibitionReviewDao.exhibitionReviewSelect(rvno);
    return review;
  }

  @Override
  public int deleteReviewByExhibition(int exno) throws Exception {
    return exhibitionReviewDao.deleteReviewByExhibition(exno);
  }
}
