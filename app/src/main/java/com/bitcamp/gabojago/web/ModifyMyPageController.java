package com.bitcamp.gabojago.web;

import com.bitcamp.gabojago.service.MemberService;
import com.bitcamp.gabojago.service.ModifyMyPageService;
import com.bitcamp.gabojago.vo.Member;
import java.sql.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/myPage/modify/")
public class ModifyMyPageController {

    private ServletContext sc;

    private ModifyMyPageService modifyMyPageService;

    private MemberService memberService;


    public ModifyMyPageController(ServletContext sc,
                                  ModifyMyPageService modifyMyPageService,
                                  MemberService memberService) {
        this.sc = sc;
        this.modifyMyPageService = modifyMyPageService;
        this.memberService = memberService;
    }

    @GetMapping("profileDetail")
    public Map profileDetail(HttpSession session) throws Exception {
        Member loginMember = (Member) session.getAttribute("loginMember");
        Member member = modifyMyPageService.get(loginMember.getId());

        Map map = new HashMap();
        map.put("member", member);
        return map;
    }

    @GetMapping("myAccountDetail")
    public Map myAccountDetail(HttpSession session) throws Exception {
        Member loginMember = (Member) session.getAttribute("loginMember");
        Member member = modifyMyPageService.get(loginMember.getId());

        Map map = new HashMap();
        map.put("member", member);
        return map;
    }

    @PostMapping("profileUpdate")
    public String profileUpdate(
            Member member,
            @RequestParam MultipartFile file,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpSession session) throws Exception {

        Member loginMember = (Member) session.getAttribute("loginMember");

        Member saveMember = memberService.get(loginMember.getId());
        saveMember.setNickName(member.getNickName().trim());
        saveMember.setMbti(member.getMbti());
        saveMember.setSnsAddress(member.getSnsAddress().trim());

        if (!file.isEmpty()) {
            String dirPath = sc.getRealPath("/board/files");
            String filename = UUID.randomUUID().toString();
            String profileFig = filename;
            file.transferTo(new File(dirPath + "/" + filename));
            saveMember.setProfileFig(profileFig);
        }

        modifyMyPageService.profileUpdate(saveMember);

        model.addAttribute("profileFig", saveMember.getProfileFig());
        model.addAttribute("nickname", saveMember.getNickName());
        model.addAttribute("mbti", saveMember.getMbti());
        model.addAttribute("snsAddress", saveMember.getSnsAddress());

        return "redirect:/myPage/";
    }

    @PostMapping("myAccountUpdate")
    public String myAccountUpdate(Member member, HttpSession session) throws Exception {

        Member loginMember = (Member) session.getAttribute("loginMember");

        Member saveMember = memberService.get(loginMember.getId());
        saveMember.setPassword(member.getPassword().trim());
        saveMember.setPhoneNo(member.getPhoneNo());
        saveMember.setBirthDay(member.getBirthDay());
        saveMember.setGender(member.getGender());

        modifyMyPageService.myAccountUpdate(member);

        return "redirect:/myPage/";
    }

    // 닉네임 중복 확인
    @ResponseBody
    @PostMapping("nickCheck")
    public int nickCheck(String nickName) throws Exception {
        int result = modifyMyPageService.nickCheck(nickName);
        return result;
    }

    @GetMapping("resignmemberform")
    public ModelAndView resignMemberForm(HttpSession session) throws Exception {
        Member member = (Member)session.getAttribute("loginMember");
        ModelAndView mv = new ModelAndView();
        if(member == null) {
            mv.setViewName("/auth/loginExpire");
            return mv;
        }
        mv.setViewName("/myPage/modify/resignMemberForm");
        mv.addObject("member", member);
        return mv;
    }

    @ResponseBody
    @PostMapping("pwcheck")
    public int pwCheck(String memberId, String memberPw,HttpSession session) throws Exception {
        Member member = (Member) session.getAttribute("loginMember");
        if(member == null) {
            return -1004;
        }
        if(!member.getId().equals(memberId)) {
            session.invalidate();
            return -1005;
        }
        return modifyMyPageService.pwCheck(memberId, memberPw);
    }

    @ResponseBody
    @PostMapping("resignmember")
    public int resignMember(String memberId, String memberPw, HttpSession session) throws Exception {
        int result = modifyMyPageService.resignMember(memberId, memberPw);
        if(result == 1) {
            session.invalidate();
        }
        return result;
    }
}

