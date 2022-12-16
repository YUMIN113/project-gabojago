package com.bitcamp.gabojago.service;

import com.bitcamp.gabojago.dao.ExhibitionDao;
import com.bitcamp.gabojago.dao.ExhibitionReviewDao;
import com.bitcamp.gabojago.vo.Exhibition;
import com.bitcamp.gabojago.vo.ExhibitionFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class DefaultExhibitionService implements ExhibitionService  {
  @Autowired
  ExhibitionDao exhibitionDao;

  @Autowired
  ExhibitionReviewDao exhibitionReviewDao;


  @Override
  public List<Exhibition> exhibitionList() throws Exception {
    return exhibitionDao.exhibitionList();
  }

  public Exhibition exhibitionSelect(int exno) throws Exception{
    return exhibitionDao.exhibitionSelect(exno);
  }

  @Transactional
  @Override
  public void insert(Exhibition exhibition) throws Exception {
    // 1) 게시글 등록
    if (exhibitionDao.exhibitionInsert(exhibition) == 0) {
      throw new Exception("게시글 등록 실패!");
    }
    // 2) 첨부파일 등록
if (exhibition.getExhibitionFiles().size()>0){
  exhibitionDao.insertFiles(exhibition);
}
  }

  @Transactional
  @Override
  public boolean delete(int exno) throws Exception {
    // 1) 첨부파일 삭제
    exhibitionDao.deleteFiles(exno);

    // 2) 리뷰 삭제
 // 테스트 필요
exhibitionReviewDao.deleteReviewByExhibition(exno);
    // 3) 게시글 삭제
    return exhibitionDao.delete(exno) > 0;

  }

  @Transactional
  @Override
  public boolean update(Exhibition exhibition) throws Exception {
    // 1) 게시글 변경
    if(exhibitionDao.update(exhibition) == 0) {
      return false;
    }
    //2) 첨부파일 등록
    if(exhibition.getExhibitionFiles().size()>0){
      exhibitionDao.insertFiles(exhibition);
    }
    return true;
  }

  @Override
  public Exhibition get(int exno) throws Exception {
    return exhibitionDao.exhibitionSelect(exno);
  }


  @Override
  public ExhibitionFile getExhibitionFile(int exfno) throws Exception {
    return exhibitionDao.findFileByNo(exfno);
  }

  @Override
  public boolean deleteExhibitionFile(int exfno) throws Exception {
    return exhibitionDao.deleteFile(exfno) > 0;
  }


// 페이징

  @Override
  public List<Exhibition> exhibitionLimitList(Map<String, Object> map) throws Exception {
    return exhibitionDao.exhibitionLimitList(map);
  }
  public int exhibitionCount () throws Exception{
    return exhibitionDao.exhibitionCount();
  }

}
