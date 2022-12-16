package com.bitcamp.gabojago.dao;


import com.bitcamp.gabojago.vo.qna.QnaBoard;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QnaAnswerDao {

    int update(QnaBoard qnaBoard);

}
