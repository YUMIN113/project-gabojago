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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/support/notice/")
public class NoticeController {

    private ServletContext sc;
    private NoticeService noticeService;

    private int PAGE_CORRECTION = 1;



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
    public Map noticeDetail(int no) throws Exception {

        noticeService.addHits(no); // 조회수
        Notice notice = noticeService.get(no);


        if (notice == null) {
            throw new Exception("해당 번호의 게시글이 없습니다!");
        }

         Map map = new HashMap();
        map.put("notice", notice);
        return map;

    }

    @GetMapping("noticeEditDetail")
    public Map noticeEditDetail(int no) throws Exception {
        Notice notice = noticeService.getEdit(no);

        if (notice == null) {
            throw new Exception("해당 번호의 게시글이 없습니다!");
        }

        Map map = new HashMap();
        map.put("notice", notice);
        return map;

    }

    @PostMapping ("noticeEditUpdate")
    public String noticeEditUpdate(Notice notice, HttpSession session) throws Exception {
        noticeService.noticeEditUpdate(notice);
        return "redirect:noticeListPage?page=1";
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
        PageResponseDto<Notice> pageResponseDto = new PageResponseDto<>(page, size, total, noticeList);

        model.addAttribute("notices", pageResponseDto.getDtoList());
        model.addAttribute("pages", pageResponseDto.getPage());
        model.addAttribute("pageTotal", pageResponseDto.getTotal());
        model.addAttribute("pageStart", pageResponseDto.getStart());
        model.addAttribute("pageEnd", pageResponseDto.getEnd());
        model.addAttribute("prev", pageResponseDto.isPrev());
        model.addAttribute("next", pageResponseDto.isNext());

    }
}
