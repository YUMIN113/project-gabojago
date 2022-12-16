package com.bitcamp.gabojago.dao.search;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExhibitionSearchDao{
  
  List<Map<String, String>> resultByTitle(String[] keyword);
  
  List<Map<String, String>> resultByContent(String[] keyword);
  
  List<Map<String, String>> resultByTitleAndContent(String[] keyword);
    
  List<Map<String, String>> resultByDate(String[] date);
  
}
