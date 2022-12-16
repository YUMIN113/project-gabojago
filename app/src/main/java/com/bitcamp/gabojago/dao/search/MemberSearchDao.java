package com.bitcamp.gabojago.dao.search;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberSearchDao {
  List<Map<String, String>> resultForAdmin(String keyword);
  
  List<Map<String, String>> resultForPublic(String keyword);
}
