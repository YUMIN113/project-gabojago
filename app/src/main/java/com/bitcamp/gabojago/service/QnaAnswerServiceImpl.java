package com.bitcamp.gabojago.service;

import com.bitcamp.gabojago.dao.QnaAnswerDao;
import com.bitcamp.gabojago.vo.Member;
import com.bitcamp.gabojago.vo.qna.QnaBoard;
import org.springframework.stereotype.Service;

@Service
public class QnaAnswerServiceImpl implements QnaAnswerService {

    QnaAnswerDao qnaAnswerDao;

    public QnaAnswerServiceImpl(QnaAnswerDao qnaAnswerDao) {
        this.qnaAnswerDao = qnaAnswerDao;
    }

    @Override
    public boolean qnaAnswerUpdate(QnaBoard qnaBoard) throws Exception {
        if (qnaAnswerDao.update(qnaBoard) == 0) {
            return false;
        }
        return true;
    }

}

