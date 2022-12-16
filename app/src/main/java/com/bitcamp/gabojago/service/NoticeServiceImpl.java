package com.bitcamp.gabojago.service;

import com.bitcamp.gabojago.dao.NoticeDao;
import com.bitcamp.gabojago.vo.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeDao noticeDao;

    @Override
    public void noticeAdd(Notice notice) throws Exception {
        if (noticeDao.insert(notice) == 0) {
            throw new Exception("게시글 등록 실패!");
        }
    }

    @Override
    public boolean noticeEditUpdate(Notice notice) throws Exception {
        if (noticeDao.update(notice) == 0) {
            return  false;
        }
        return true;
    }

    @Override
    public Notice get(int no) throws Exception {
        return noticeDao.findByNo(no);
    }

    @Override
    public Notice getEdit(int no) throws Exception {
        return noticeDao.findByEditNo(no);
    }

    @Override
    public boolean noticeDelete(int no) throws Exception {
        return noticeDao.delete(no) > 0;
    }

    @Override
    public List<Notice> noticeList() throws Exception {
        return noticeDao.findAll();
    }

    // 조회수
    @Override
    public void addHits(int no) throws Exception {
        noticeDao.addHits(no);
    }

    // paging_게시물 총개수
    @Override
    public int count() throws Exception {
        return noticeDao.count();
    }

    // 게시물 목록 + paging
    @Override
    public List<Notice> noticeListPage(int displayPost, int size) throws Exception {
        return noticeDao.noticeListPage(displayPost, size);
    }
}
