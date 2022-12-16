package com.bitcamp.gabojago.service;

import com.bitcamp.gabojago.vo.qna.QnaBoard;

import java.util.List;

public interface QnaService {

    void add(QnaBoard qnaBoard) throws Exception;

    QnaBoard get(int no) throws Exception;

    Boolean delete(int no) throws Exception;

    boolean update(QnaBoard qnaBoard) throws Exception;

    int qnaPostCount() throws Exception;


    List<QnaBoard> list(int displayPost, int size) throws Exception;
}
