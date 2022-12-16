package com.bitcamp.gabojago.dao.search;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecommendationSearchDao {
  public List<Map<String, String>> resultRecommendation(String [] keyword);
}
