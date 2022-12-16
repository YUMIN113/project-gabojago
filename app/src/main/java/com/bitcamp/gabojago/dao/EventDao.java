package com.bitcamp.gabojago.dao;

import com.bitcamp.gabojago.vo.event.Event;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EventDao {
    int insert(Event event);

    Event findByNo(int no);

    int deleteByNo(int no);

    int update(Event event);

    int deleteJoinByEventNo(int eventNo);

    int addViewCount(int no);

    int eventPostCount();

    List<Event> findAll(@Param("displayPost") int displayPost, @Param("size") int size);
}
