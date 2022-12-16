package com.bitcamp.gabojago.dao;

import com.bitcamp.gabojago.vo.qna.QnaBoard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QnaDao {
    int insert(QnaBoard qnaBoard);

    QnaBoard findByNo(int no);

    int deleteByNo(int no);

    int update(QnaBoard qnaBoard);

    int qnaPostCount();

    List<QnaBoard> findAll(@Param("displayPost") int displayPost, @Param("size") int size);
}
