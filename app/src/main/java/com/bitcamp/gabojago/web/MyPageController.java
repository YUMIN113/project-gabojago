package com.bitcamp.gabojago.web;

import com.bitcamp.gabojago.service.MemberService;
import com.bitcamp.gabojago.vo.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/myPage")
public class MyPageController {

    private MemberService memberService;

    public MyPageController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String myPage(HttpSession session, Model model) throws Exception {

        Member loginMember = (Member) session.getAttribute("loginMember");

        if (loginMember == null) {

            model.addAttribute("myPageFailMessage", "로그인이 필요한 항목입니다. 회원가입 또는 로그인해 주세요.");

            return "myPage/myPageFail";

        } else {
            Member member = memberService.get(loginMember.getId());
            model.addAttribute("id", member.getId());
            model.addAttribute("name", member.getName());
            model.addAttribute("profileFig", member.getProfileFig());
            model.addAttribute("nickName", member.getNickName());
            model.addAttribute("mbti", member.getMbti());
            model.addAttribute("snsAddress", member.getSnsAddress());

            return "myPage/myPage";

        }
    }
}

