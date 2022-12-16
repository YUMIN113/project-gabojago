package com.bitcamp.gabojago.service.search.exhibition;

import java.util.List;
import java.util.Map;
import com.bitcamp.gabojago.dao.search.ExhibitionSearchDao;
import com.bitcamp.gabojago.service.search.SearchType;

/**
 * <p>
   * 전시회 검색을 하는 메서드가 모인 열거형 타입입니다.
 * </p>
 * <p>
   * 이 클래스의 원소들은
   * {@link ExhibitionSearchService} 클래스의
   * {@link ExhibitionSearchService#getResult(ExhibitionSearchType, String)}
   * 메서드 파라미터로 사용하기 위해 설계되었습니다.
   * 
 * </p>
 */
public enum ExhibitionSearchType implements SearchType{
  /**
   * <p> 
   * 제목으로 전시회를 검색하는 원소입니다.
   * </p>
   */
  TITLE{
    @Override
    protected List<Map<String, String>> execute(ExhibitionSearchDao dao, String[] keyword) {
      return dao.resultByTitle(keyword);
    }
  },
  /**
   * <p> 
   * 내용으로 전시회를 검색하는 원소입니다.
   * </p>
   */
  CONTENT{
    @Override
    protected List<Map<String, String>> execute(ExhibitionSearchDao dao, String[] keyword) {
      return dao.resultByContent(keyword);
    }
  },
  /**
   * <p> 
   * 제목 및 내용으로 전시회를 검색하는 원소입니다.
   * </p>
   */
  TITLE_WITH_CONTENT{
    @Override
    protected List<Map<String, String>> execute(ExhibitionSearchDao dao, String[] keyword) {
      return dao.resultByTitleAndContent(keyword);
    }
  },
  /**
   * <p> 
   * @deprecated
   * 사용불가 미개발
   * </p>
   */
  DATE{
    @Override
    protected List<Map<String, String>> execute(ExhibitionSearchDao dao, String[] keyword) {
      return dao.resultByDate(keyword);
    }    
  };
  protected abstract List<Map<String, String>> execute(ExhibitionSearchDao dao, String[] keyword);
}