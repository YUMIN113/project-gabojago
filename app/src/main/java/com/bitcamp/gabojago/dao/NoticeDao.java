package com.bitcamp.gabojago.dao;


import com.bitcamp.gabojago.vo.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeDao {
    int insert(Notice notice);

    Notice findByNo(int no);

    Notice findByEditNo(int no);

    int update(Notice notice);

    int delete(int no);

    List<Notice> findAll();

    // 조회수
    int addHits(int no);

    // paging_게시물 총개수
    int count();

    // 게시물 목록 + paging
    List<Notice> noticeListPage(@Param("displayPost") int displayPost, @Param("size") int size);
}


