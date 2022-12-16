package com.bitcamp.gabojago.service.search.member;

import java.util.List;
import java.util.Map;
import com.bitcamp.gabojago.dao.search.MemberSearchDao;
import com.bitcamp.gabojago.service.search.SearchType;

/**
 * <p>
   * 회원 검색을 하는 메서드가 모인 열거형 타입입니다.
 * </p>
 * <p>
   * 이 클래스의 원소들은
   * {@link MemberSearchService} 클래스의
   * {@link MemberSearchService#getResult(MemberSearchType, String)}
   * 메서드 파라미터로 사용하기 위해 설계되었습니다.
   * 
 * </p>
 */
public enum MemberSearchType implements SearchType{
  /**
   * <p> 
   * 일반 사용자를 위한 검색 기능을 제공합니다. 민감한 개인 정보는 검색되지 않습니다.
   * </p>
   */
  PUBLIC{
    @Override
    protected List<Map<String, String>> execute(MemberSearchDao dao, String keyword) {
      return dao.resultForPublic(keyword);
    }
  },
  /**
   * <p> 
   * 관리자를 위한 검색 기능을 제공합니다. 회원의 모든 정보가 검색됩니다. <br>
   * 일반 사용자 및 외부인은 이 검색 기능을 절대 이용해서는 안됩니다.
   * </p>
   */
  ADMIN{
    @Override
    protected List<Map<String, String>> execute(MemberSearchDao dao, String keyword) {
      return dao.resultForAdmin(keyword);
    }
  };

  protected abstract List<Map<String, String>> execute(MemberSearchDao dao, String keyword);
}