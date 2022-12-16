package com.bitcamp.gabojago.service;

import com.bitcamp.gabojago.dao.ExhibitionReviewDao;
import com.bitcamp.gabojago.dao.JangCommentDao;
import com.bitcamp.gabojago.vo.ExhibitionReview;
import com.bitcamp.gabojago.vo.JangComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultJangCommentService implements JangCommentService {
  @Autowired
  JangCommentDao jangCommentDao;


  public List<JangComment> jangCommentList(int recono) throws Exception {
    return jangCommentDao.jangCommentList(recono);
  }


  @Transactional
  @Override
  public void jangCommentInsert(JangComment jangComment) throws Exception {
    if (jangCommentDao.jangCommentInsert(jangComment) ==0){
      throw new Exception("댓글 등록 실패!");
    }
  }

  @Transactional
  @Override
  public boolean jangCommentDelete(int cmno) throws Exception {
    return jangCommentDao.jangCommentDelete(cmno) >0;
  }

  @Override
  public JangComment getJangCommentByCmno(int cmno) throws Exception {
    return jangCommentDao.getJangCommentByCmno(cmno);
  }


}
