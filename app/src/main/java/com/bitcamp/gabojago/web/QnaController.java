package com.bitcamp.gabojago.web;

import com.bitcamp.gabojago.service.QnaService;
import com.bitcamp.gabojago.vo.Member;
import com.bitcamp.gabojago.vo.PageResponseDto;
import com.bitcamp.gabojago.vo.qna.QnaBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/support/qna/")
public class QnaController {
    @Autowired
    QnaService qnaService;
    private int PAGE_CORRECTION = 1;

    public QnaController(QnaService qnaService) {
        System.out.println("QnaController() 호출됨!");
        this.qnaService = qnaService;
    }

    @GetMapping("list")
    public void list(Model model, @RequestParam("page") Integer page, @RequestParam(value = "size", defaultValue = "3") Integer size) throws Exception {
        int total = qnaService.qnaPostCount();

        page -= PAGE_CORRECTION;
        List<QnaBoard> list = qnaService.list((page) * size, size);
        PageResponseDto<QnaBoard> qnaBoardPageResponseDto = new PageResponseDto<>(page, size, total, list);

        model.addAttribute("qnaBoards", qnaBoardPageResponseDto.getDtoList());
        model.addAttribute("pages", qnaBoardPageResponseDto.getPage());
        model.addAttribute("pageTotal", qnaBoardPageResponseDto.getTotal());
        model.addAttribute("pageStart", qnaBoardPageResponseDto.getStart());
        model.addAttribute("pageEnd", qnaBoardPageResponseDto.getEnd());
        model.addAttribute("prev", qnaBoardPageResponseDto.isPrev());
        model.addAttribute("next", qnaBoardPageResponseDto.isNext());
    }

    @GetMapping("addForm")
    public void addForm() throws Exception {

    }

    @PostMapping("add")
    public String add(
            QnaBoard qnaBoard,
            HttpSession session) throws Exception {
        Member member = (Member) session.getAttribute("loginMember");
        qnaBoard.setId(member.getId());
        qnaService.add(qnaBoard);
        return "redirect:list?page=1";
    }

    @GetMapping("detail")
    public void detail(int no, Model model, HttpSession session) throws Exception {
        Member member = (Member) session.getAttribute("loginMember");
        QnaBoard qnaBoard = qnaService.get(no);
        model.addAttribute("qnaBoard", qnaBoard);
        boolean isPass = true;

        if (qnaBoard.getDisclosure() && member == null) {
            isPass = false;
        } else if (qnaBoard.getDisclosure() && member != null) {
            if (!member.getId().equals(qnaBoard.getId()) && !member.getId().equals("admin")) {
                isPass = false;
            }
        }

        model.addAttribute("isPass", isPass);
    }

    @GetMapping("delete")
    public String delete(int no, HttpSession session) throws Exception {
        Member member = (Member) session.getAttribute("loginMember");

        qnaService.delete(no);
        return "redirect:list?page=1";
    }

    @GetMapping("editForm")
    public void editForm(int no, Model model, HttpSession session) throws Exception {
        Member member = (Member) session.getAttribute("loginMember");
        model.addAttribute("qnaBoard", qnaService.get(no));
        model.addAttribute("member", member);
    }

    @PostMapping("update")
    public String update(
            QnaBoard qnaBoard,
            HttpSession session) throws Exception {
        Member member = (Member) session.getAttribute("loginMember");
        if (member == null) {
            return "redirect:list?page=1";
        } else if (!member.getId().equals(qnaBoard.getId())) {
            return "redirect:list?page=1";
        }
        qnaService.update(qnaBoard);
        return "redirect:list?page=1";
    }
}
