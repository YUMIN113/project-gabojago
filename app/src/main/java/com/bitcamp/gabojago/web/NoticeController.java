package com.bitcamp.gabojago.web;


import com.bitcamp.gabojago.service.NoticeService;
import com.bitcamp.gabojago.vo.Notice;
import com.bitcamp.gabojago.vo.PageResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/support/notice/")
public class NoticeController {

    private ServletContext sc;
    private NoticeService noticeService;

    private int PAGE_CORRECTION = 1;

    private final int PAGE_LENGTH = 2;



    public NoticeController(NoticeService noticeService, ServletContext sc) {
        this.noticeService = noticeService;
        this.sc = sc;
    }


    @GetMapping("noticeForm")
    public void noticeForm() throws Exception { }


    @PostMapping("noticeAdd")
    public String noticeAdd(Notice notice, HttpSession session) throws Exception {
        noticeService.noticeAdd(notice);
        return "redirect:noticeListPage?page=1";
    }

    @GetMapping("noticeDetail")
    public void noticeDetail(int no, Model model, @RequestParam("page") Integer page) throws Exception {

        noticeService.addHits(no); // 조회수
        Notice notice = noticeService.get(no);


        if (notice == null) {
            throw new Exception("해당 번호의 게시글이 없습니다!");
        }

        model.addAttribute("notice", notice);
        model.addAttribute("page", page);

    }

    @GetMapping("noticeEditDetail")
    public void noticeEditDetail(int no, Model model, @RequestParam("page") Integer page) throws Exception {

        Notice notice = noticeService.getEdit(no);

        if (notice == null) {
            throw new Exception("해당 번호의 게시글이 없습니다!");
        }

        model.addAttribute("notice", notice);
        model.addAttribute("page", page);

    }

    @PostMapping ("noticeEditUpdate")
    public String noticeEditUpdate(Notice notice, @RequestParam("page") Integer page) throws Exception {

        noticeService.noticeEditUpdate(notice);

        return "redirect:noticeListPage?page=" + page;
    }

    @GetMapping("noticeDelete")
    public String noticeDelete(int no, HttpSession session) throws Exception {
        noticeService.noticeDelete(no);
        return "redirect:noticeListPage?page=1";
    }

    // 게시물 목록 + paging
    @GetMapping("noticeListPage")
    public void noticeListPage(Model model, @RequestParam("page") Integer page, @RequestParam(value = "size", defaultValue = "3") Integer size ) throws Exception {

        // 게시물 총개수
        int total = noticeService.count();

        page -= PAGE_CORRECTION;

        List<Notice> noticeList = noticeService.noticeListPage((page) * size, size);
        PageResponseDto<Notice> pageResponseDto = new PageResponseDto<>(page, size, total, PAGE_LENGTH, noticeList);

        model.addAttribute("notices", pageResponseDto.getDtoList());
        model.addAttribute("page", pageResponseDto.getPage() + 1);
        model.addAttribute("pageTotal", pageResponseDto.getTotal());
        model.addAttribute("pageStart", pageResponseDto.getStart());
        model.addAttribute("pageEnd", pageResponseDto.getEnd());
        model.addAttribute("prev", pageResponseDto.isPrev());
        model.addAttribute("next", pageResponseDto.isNext());

    }
}
