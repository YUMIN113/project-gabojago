package com.bitcamp.gabojago.service;


import com.bitcamp.gabojago.vo.Member;
import com.bitcamp.gabojago.vo.qna.QnaBoard;

public interface QnaAnswerService {

    boolean qnaAnswerUpdate(QnaBoard qnaBoard) throws Exception;

}