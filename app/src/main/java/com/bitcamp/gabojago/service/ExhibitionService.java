package com.bitcamp.gabojago.service;

import com.bitcamp.gabojago.vo.Exhibition;
import com.bitcamp.gabojago.vo.ExhibitionFile;

import java.util.List;
import java.util.Map;


public interface ExhibitionService {

  List<Exhibition> exhibitionList() throws Exception;

  Exhibition exhibitionSelect(int exno) throws Exception;

  void insert(Exhibition exhibition) throws Exception;

  boolean delete(int exno) throws Exception;

  boolean update(Exhibition exhibition) throws Exception;

  Exhibition get (int exno) throws Exception;

  // 첨부파일
ExhibitionFile getExhibitionFile(int exfno) throws Exception;

boolean deleteExhibitionFile(int exfno) throws Exception;

  List<Exhibition> exhibitionLimitList(Map<String, Object> map) throws Exception;

  int exhibitionCount () throws Exception;


}