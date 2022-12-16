package com.bitcamp.gabojago.web;

import com.bitcamp.gabojago.service.QnaAnswerService;
import com.bitcamp.gabojago.vo.qna.QnaBoard;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/support/qna/")
public class QnAAnswerController {

   QnaAnswerService qnaAnswerService;

    public QnAAnswerController(QnaAnswerService qnaAnswerService) {
        this.qnaAnswerService = qnaAnswerService;
    }


    @PostMapping("qnaAnswerUpdate")
    private String qnaAnswerUpdate (
            QnaBoard qnaBoard,
            HttpSession session) throws Exception {

        qnaAnswerService.qnaAnswerUpdate(qnaBoard);
        String redirect_url = "redirect:detail?no=" + Integer.toString(qnaBoard.getNo());
        return redirect_url;

    }
}


