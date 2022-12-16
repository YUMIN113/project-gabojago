package com.bitcamp.gabojago.dao;


import com.bitcamp.gabojago.vo.event.EventAttachedFile;
import com.bitcamp.gabojago.vo.event.EventItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventItemDao {

    int insert(EventItem eventItem);

    int getItemsCountByEventNo(int eventNo);

    List<EventItem> getItemListByEventNo(int eventNo);
    int deleteItemByEventNo(int eventNo);

    int deleteFileByEventNo(int eventNo);

    int insertEventFile(EventAttachedFile eventAttachedFile);
}
