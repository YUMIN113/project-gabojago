package com.bitcamp.gabojago.service.search;

import java.util.List;
import java.util.Map;

public abstract class AbstractSearchService<T extends Enum<T> & SearchType> implements SearchService<T>{
  @Override
  public List<Map<String, String>> getResult(T type, String keyword) throws IllegalArgumentException {
    throw new UnsupportedOperationException();
  }

  protected abstract String[] parseKeyword(String keyword);

  protected abstract void isCorrectKeyword(String keyword) throws IllegalArgumentException;
}
