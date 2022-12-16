package com.bitcamp.gabojago.service.search.recommendation;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bitcamp.gabojago.dao.search.RecommendationSearchDao;
import com.bitcamp.gabojago.service.search.AbstractSearchService;

@Service
public class RecommendationSearchService extends AbstractSearchService<RecommendationSearchType> {

  @Autowired
  private RecommendationSearchDao recommendationDao;

  @Override
  @Transactional
  public List<Map<String, String>> getResult(RecommendationSearchType type, String keyword) throws IllegalArgumentException{
    isCorrectKeyword(keyword);
    
    return type.execute(recommendationDao, parseKeyword(keyword));
  }

  @Override
  protected String[] parseKeyword(String keyword) {
    return keyword.split(" ");
  }

  @Override
  protected void isCorrectKeyword(String keyword) throws IllegalArgumentException{
    if(keyword.equals("") || keyword.length() <= 1) {
      throw new IllegalArgumentException("검색어는 최소 1 글자 이상이어야 합니다.");
    }
  }
}
