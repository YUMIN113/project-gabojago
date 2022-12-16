package com.bitcamp.gabojago.service.search;

import java.util.List;
import java.util.Map;

public interface SearchService<T extends Enum<T> & SearchType> {
  
  List<Map<String, String>> getResult(T type, String keyword); 
  
}
