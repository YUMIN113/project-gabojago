package com.bitcamp.gabojago.service.search.recommendation;

import java.util.List;
import java.util.Map;
import com.bitcamp.gabojago.dao.search.RecommendationSearchDao;
import com.bitcamp.gabojago.service.search.SearchType;


public enum RecommendationSearchType implements SearchType{

  DEFAULT{
    @Override
    protected List<Map<String, String>> execute(RecommendationSearchDao dao, String [] keyword) {
      return dao.resultRecommendation(keyword);
    }
  };

  protected abstract List<Map<String, String>> execute(RecommendationSearchDao recommendationDao, String [] keyword);
}