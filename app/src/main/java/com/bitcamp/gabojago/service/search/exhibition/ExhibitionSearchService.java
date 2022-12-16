package com.bitcamp.gabojago.service.search.exhibition;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bitcamp.gabojago.dao.search.ExhibitionSearchDao;
import com.bitcamp.gabojago.service.search.AbstractSearchService;

/**
 * 
 * <p>
 * 전시회 검색 기능을 제공하는 Service 클래스입니다.
 * </p>
 * 
 * <p>
 * 이 객체는 {@link #getResult(ExhibitionSearchType, String)} 메서드를 통해 전시회 검색을 수행합니다.<br>
 * </p>
 *
 */
@Service
public class ExhibitionSearchService extends AbstractSearchService<ExhibitionSearchType> {

  @Autowired
  private ExhibitionSearchDao searchDAO;

  /**
   * <p>
   * 전시회 검색 기능을 제공하는 메서드입니다.
   * </p>
   * 
   * @param type : {@link #ExhibitionSearchType} 열거형 원소 중 하나를 입력받습니다.
   * 각각의 원소가 실행하는 검색 방법은 해당 열거형 타입의 설명을 참조해주세요.
   * 
   * @param keyword : 검색할 검색어를 입력받습니다. 검색한 입력어는 공백을 기준으로 파싱되어 검색됩니다.
   * 
   * @exception {@link #IllegalArgumentException} 검색어의 글자 수가 1개 이하인 경우 발생합니다.
   */
  @Override
  @Transactional
  public List<Map<String, String>> getResult(ExhibitionSearchType type, String keyword) throws IllegalArgumentException{
    isCorrectKeyword(keyword);
    
    return type.execute(searchDAO, parseKeyword(keyword));
  }

  /**
   * <p>
   * 검색어를 파싱합니다.
   * </p>
   * 
   * <p>
   * 이 메서드는 자가사용 메서드입니다. 클래스 외부에서 호출하거나, 재정의 해서는 안됩니다.
   * </p>
   */
  @Override
  protected String[] parseKeyword(String keyword) {
    return keyword.split(" ");
  }

  /**
   * <p>
   * 검색어가 올바른지 확인합니다.
   * </p>
   * 
   * <p>
   * 이 메서드는 자가사용 메서드입니다. 클래스 외부에서 호출하거나, 재정의 해서는 안됩니다.
   * </p>
   */
  @Override
  protected void isCorrectKeyword(String keyword) throws IllegalArgumentException {
    if(keyword.equals("") || keyword.length() <= 1) {
      throw new IllegalArgumentException("검색어는 최소 1 글자 이상이어야 합니다.");
    }
  }
}
