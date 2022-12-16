package com.bitcamp.gabojago.service;

import com.bitcamp.gabojago.dao.EventDao;
import com.bitcamp.gabojago.dao.EventItemDao;
import com.bitcamp.gabojago.vo.event.Event;
import com.bitcamp.gabojago.vo.event.EventAttachedFile;
import com.bitcamp.gabojago.vo.event.EventItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventDao eventDao;
    @Autowired
    EventItemDao eventItemDao;

    @Override
    public void add(Event event) throws Exception {

        if (eventDao.insert(event) == 0) {
            throw new Exception("게시글 등록 실패!!");
        }
    }

    @Override
    public Event get(int no) throws Exception {
        return eventDao.findByNo(no);
    }

    @Override
    public boolean delete(int no) throws Exception {
        int deleteFileResult = eventItemDao.deleteFileByEventNo(no);
        int deleteItemResult = eventItemDao.deleteItemByEventNo(no);
        int deleteJoinResult = eventDao.deleteJoinByEventNo(no);
        return eventDao.deleteByNo(no) > 0;
    }

    @Override
    public boolean update(Event event) throws Exception {
        if (eventDao.update(event) == 0) {
            return false;
        }
        return true;
    }

    @Transactional
    @Override
    public void itemAdd(EventItem eventItem) throws Exception {

        // 파라미터에는 이벤트 번호가 들어가야함
        int itemsCount = eventItemDao.getItemsCountByEventNo(eventItem.getEventNo());
        // 신규 이벤트 아이템 지급 순위 로직
        int ranking = itemsCount + 1;
        // 신규 이벤트 아이템 지급 순위 세팅
        eventItem.setRanking(ranking);
        int newItemNo = eventItemDao.insert(eventItem);
        if (newItemNo == 0) {
            throw new Exception("아이템 등록 실패!!");
        }

        EventAttachedFile eventAttachedFile = eventItem.getEventAttachedFile();
        eventAttachedFile.setItemNo(eventItem.getItemNo());
        eventItemDao.insertEventFile(eventAttachedFile);
    }

    @Transactional
    @Override
    public List<EventItem> itemList(int no) throws Exception {
        List<EventItem> itemListByEventNo = eventItemDao.getItemListByEventNo(no);
        return itemListByEventNo;
    }

    @Override
    public void addViewCount(int no) throws Exception {
        eventDao.addViewCount(no);
    }

    @Override
    public int eventPostCount() throws Exception {
        return eventDao.eventPostCount();
    }

    @Transactional
    @Override
    public List<Event> list(int displayPost, int size) throws Exception {
        return eventDao.findAll(displayPost, size);
    }
}
