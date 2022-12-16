package com.bitcamp.gabojago.web;

import com.bitcamp.gabojago.service.MemberService;
import com.bitcamp.gabojago.service.MyRecommendationService;
import com.bitcamp.gabojago.vo.Member;
import com.bitcamp.gabojago.vo.PageResponseDto;
import com.bitcamp.gabojago.vo.Recommendation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/myPage/myRecommendation/")
public class MyRecommendationController {

    private MyRecommendationService myRecommendationService;
    private MemberService memberService;

    private int PAGE_CORRECTION = 1;

    public MyRecommendationController(MyRecommendationService myRecommendationService, MemberService memberService) {
        this.myRecommendationService = myRecommendationService;
        this.memberService = memberService;
    }

    // 게시물 목록 + paging
    @GetMapping("myRecommendationListPage")
    public void myRecommendationListPage(Recommendation recommendation,
                                         HttpSession session,
                                         Model model,
                                         @RequestParam("page") Integer page, @RequestParam(value = "size", defaultValue = "3") Integer size) throws Exception {

        Member loginMember = (Member) session.getAttribute("loginMember");
        Member member = memberService.get(loginMember.getId());

        model.addAttribute("id", member.getId());
        model.addAttribute("name", member.getName());
        model.addAttribute("profileFig", member.getProfileFig());
        model.addAttribute("nickName", member.getNickName());
        model.addAttribute("mbti", member.getMbti());
        model.addAttribute("snsAddress", member.getSnsAddress());

        // 게시물 총개수
        int total = myRecommendationService.count(loginMember.getId());

        page -= PAGE_CORRECTION;

        recommendation.setWriter((Member) session.getAttribute("loginMember"));
        List<Recommendation> recommendationList = myRecommendationService.myRecommendationListPage((page) * size, size, recommendation.getWriter().getId());
        PageResponseDto<Recommendation> pageResponseDto = new PageResponseDto<>(page, size, total, recommendationList);

        model.addAttribute("myRecommendations", pageResponseDto.getDtoList());
        model.addAttribute("pages", pageResponseDto.getPage());
        model.addAttribute("pageTotal", pageResponseDto.getTotal());
        model.addAttribute("pageStart", pageResponseDto.getStart());
        model.addAttribute("pageEnd", pageResponseDto.getEnd());
        model.addAttribute("prev", pageResponseDto.isPrev());
        model.addAttribute("next", pageResponseDto.isNext());

    }
}
