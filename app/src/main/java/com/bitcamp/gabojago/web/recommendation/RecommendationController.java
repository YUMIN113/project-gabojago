package com.bitcamp.gabojago.web.recommendation;

import com.bitcamp.gabojago.service.JangCommentService;
import com.bitcamp.gabojago.vo.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;

import com.bitcamp.gabojago.service.RecommendationService;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/recommendation/")
public class RecommendationController {
  @Autowired
  ServletContext sc;
  @Autowired
  RecommendationService recommendationService;
  @Autowired
  JangCommentService jangCommentService;

  private int PAGE_CORRECTION = 1;

  public RecommendationController() {
    System.out.println("RecommendationController() 호출됨!");
  }

  @GetMapping("recommendationForm")
  public void form() throws Exception {
  }

  // Add method 시작
  @Transactional
  @PostMapping("recommendationAdd")
  public String recommendationAdd(
          String place1, MultipartFile[] files1, String cont1,
          String place2, MultipartFile[] files2, String cont2,
          String place3, MultipartFile[] files3, String cont3,
          String place4, MultipartFile[] files4, String cont4,
          String place5, MultipartFile[] files5, String cont5,
          String pet, String frd, String cple, String fmly, String solo, String tpname,
          HttpSession session, Recommendation recommendation) throws Exception {

    // 작성자정보 set하기
    // 현재 사용자가 회원인지 확인
    if (session.getAttribute("loginMember") == null) {
      return "redirect:recommendationList";
    } else {
      Member member = (Member) session.getAttribute("loginMember");
      // 제재받은 사용자인지 확인
      if (!recommendationService.checkCorrectUser(member.getId())) {
        return "redirect:recommendationList";
      }
      recommendation.setWriter(member);
    }

    // 변수분류 저장
    MultipartFile[][] files = {files1, files2, files3, files4, files5};
    String[] cont = {cont1, cont2, cont3, cont4, cont5};
    String[] place = {place1, place2, place3, place4, place5};

    // recommendation에 JangSoReview List를 set하기
    recommendation.setJangSoReviews(saveJangSoReviews(files, cont, place));

    // recommendation에 meta 데이터 set하기
    recommendation.setTpname(tpname);
    recommendation.setPet(pet != null);
    recommendation.setFrd(frd != null);
    recommendation.setCple(cple != null);
    recommendation.setFmly(fmly != null);
    recommendation.setSolo(solo != null);

    // recommendation add하기
    recommendationService.recommendationAdd(recommendation);

    return "redirect:recommendationList";
  }

  private List<JangSoReview> saveJangSoReviews(
          MultipartFile[][] files, String[] cont, String[] place) throws Exception {

    // JangSoReview List 생성
    List<JangSoReview> jangSoReviews = new ArrayList<>();

    // 각각의 JangSoReview 저장
    for (int i = 0; i < cont.length; i++) {
      // 내용이 없거나 장소정보가 없다면 저장하지 않는다. (=첨부파일 유무와 관계없다)
      if (cont[i].length() == 0 || place[i].length() == 0) {
        continue;
      }

      // 장소정보 parsing
      // JangSoReview 객체 생성
      JangSoReview jangSoReview = new JangSoReview();
      // 해당 리뷰글 set
      jangSoReview.setCont(cont[i]);

      // 장소 parsing
      if (place[i].contains(", ")) {
        // 장소명 parsing 및 set
        jangSoReview.setPlname(place[i].split(", ")[0]);
        // 장소주소 parsing 및 set
        jangSoReview.setAdrs(place[i].split(", ")[1]);
      } else {
        jangSoReview.setPlname(place[i]);
      }
      // 첨부파일 저장 및 set
      jangSoReview.setAttachedFiles(saveJangSoReviewAttachedFiles(files[i]));
      // JangSoReview List에 처리가 끝난 JangSoReview add
      jangSoReviews.add(jangSoReview);
    }

    return jangSoReviews;
  }

  private List<JangSoReviewAttachedFile> saveJangSoReviewAttachedFiles(
          MultipartFile[] files)
          throws Exception {

    List<JangSoReviewAttachedFile> jangSoReviewAttachedFiles = new ArrayList<>();
    String dirPath = sc.getRealPath("/board/files");

    for (MultipartFile file : files) {
      if (file.isEmpty()) {
        continue;
      }

      String filepath = UUID.randomUUID().toString();
      String filename = file.getOriginalFilename();
      file.transferTo(new File(dirPath + "/" + filepath));
      jangSoReviewAttachedFiles.add(new JangSoReviewAttachedFile(filepath, filename));
    }
    return jangSoReviewAttachedFiles;
  }
  // Add method 끝

  // List
  @GetMapping("recommendationList")
  public void recommendationList(Model model) throws Exception {
    model.addAttribute("recommendations", recommendationService.recommendationList());
    model.addAttribute("recommendationsOrderByRecent", recommendationService.recommendationListOrderByRecent());
    model.addAttribute("recommendationsOrderByComments", recommendationService.recommendationListOrderByComments());
    model.addAttribute("recommendationsOrderByCnt", recommendationService.recommendationListOrderByCnt());
  }

  @GetMapping("recommendationListForAlone")
  public void recommendationListForAlone(Model model) throws Exception {
    // alone
    model.addAttribute("recommendationsOrderByRecentForAlone", recommendationService.recommendationListOrderByRecentForAlone());
    model.addAttribute("recommendationsOrderByCommentsForAlone", recommendationService.recommendationListOrderByCommentsForAlone());
    model.addAttribute("recommendationsOrderByCntForAlone", recommendationService.recommendationListOrderByCntForAlone());
  }

  @GetMapping("recommendationListForCouple")
  public void recommendationListForCouple(Model model) throws Exception {
    // couple
    model.addAttribute("recommendationsOrderByRecentForCouple", recommendationService.recommendationListOrderByRecentForCouple());
    model.addAttribute("recommendationsOrderByCommentsForCouple", recommendationService.recommendationListOrderByCommentsForCouple());
    model.addAttribute("recommendationsOrderByCntForCouple", recommendationService.recommendationListOrderByCntForCouple());
  }

  @GetMapping("recommendationListForFamily")
  public void recommendationListForFamily(Model model) throws Exception {
    // family
    model.addAttribute("recommendationsOrderByRecentForFamily", recommendationService.recommendationListOrderByRecentForFamily());
    model.addAttribute("recommendationsOrderByCommentsForFamily", recommendationService.recommendationListOrderByCommentsForFamily());
    model.addAttribute("recommendationsOrderByCntForFamily", recommendationService.recommendationListOrderByCntForFamily());
  }

  @GetMapping("recommendationListForFriend")
  public void recommendationListForFriend(Model model) throws Exception {
    // family
    model.addAttribute("recommendationsOrderByRecentForFriend", recommendationService.recommendationListOrderByRecentForFriend());
    model.addAttribute("recommendationsOrderByCommentsForFriend", recommendationService.recommendationListOrderByCommentsForFriend());
    model.addAttribute("recommendationsOrderByCntForFriend", recommendationService.recommendationListOrderByCntForFriend());
  }

  @GetMapping("recommendationListOrderByRecentAll")
  public void recommendationListOrderByRecentAll(Model model, @RequestParam("page") Integer page, @RequestParam(value = "size", defaultValue = "4") Integer size) throws Exception {
    int total = recommendationService.getTotal();
    page -= PAGE_CORRECTION;
    List<Recommendation> recommendationList = recommendationService.recommendationListPageOrderByRecent((page) * size, size);

    PageMakerDTO pageMakerDTO = new PageMakerDTO(page, size, total, recommendationList);

    model.addAttribute("recommendationListOrderByRecent", pageMakerDTO.getDtoList());
    model.addAttribute("page", pageMakerDTO.getPage());
    model.addAttribute("pageNum", pageMakerDTO.getTotal());
    model.addAttribute("pageStart", pageMakerDTO.getStart());
    model.addAttribute("pageEnd", pageMakerDTO.getEnd());
    model.addAttribute("prev", pageMakerDTO.isPrev());
    model.addAttribute("next", pageMakerDTO.isNext());
  }
  @GetMapping("recommendationListOrderByCommentsAll")
  public void recommendationListOrderByCommentsAll(Model model, @RequestParam("page") Integer page, @RequestParam(value = "size", defaultValue = "4") Integer size) throws Exception {
    int total = recommendationService.getTotal();
    page -= PAGE_CORRECTION;
    List<Recommendation> recommendationList = recommendationService.recommendationListPageOrderByComments((page) * size, size);

    PageMakerDTO pageMakerDTO = new PageMakerDTO(page, size, total, recommendationList);

    model.addAttribute("recommendationsOrderByComments", pageMakerDTO.getDtoList());
    model.addAttribute("page", pageMakerDTO.getPage());
    model.addAttribute("pageNum", pageMakerDTO.getTotal());
    model.addAttribute("pageStart", pageMakerDTO.getStart());
    model.addAttribute("pageEnd", pageMakerDTO.getEnd());
    model.addAttribute("prev", pageMakerDTO.isPrev());
    model.addAttribute("next", pageMakerDTO.isNext());
  }

  @GetMapping("recommendationListOrderByCntAll")
  public void recommendationListOrderByCntAll(Model model, @RequestParam("page") Integer page, @RequestParam(value = "size", defaultValue = "4") Integer size) throws Exception {
    int total = recommendationService.getTotal();
    page -= PAGE_CORRECTION;
    List<Recommendation> recommendationList = recommendationService.recommendationListPageOrderByCnt((page) * size, size);

    PageMakerDTO pageMakerDTO = new PageMakerDTO(page, size, total, recommendationList);
    model.addAttribute("recommendationsOrderByCnt",  pageMakerDTO.getDtoList());
    model.addAttribute("page", pageMakerDTO.getPage());
    model.addAttribute("pageNum", pageMakerDTO.getTotal());
    model.addAttribute("pageStart", pageMakerDTO.getStart());
    model.addAttribute("pageEnd", pageMakerDTO.getEnd());
    model.addAttribute("prev", pageMakerDTO.isPrev());
    model.addAttribute("next", pageMakerDTO.isNext());
  }

  // Detail
  @GetMapping("recommendationDetail")
  public void jangSoReviewList(int recono, Model model) throws Exception {
    recommendationService.setCntRecommendation(recono);
    model.addAttribute("recommendation", recommendationService.getRecommendation(recono));
    model.addAttribute("jangSoReviews", recommendationService.getJangSoReviewList(recono));
    model.addAttribute("jangComments", jangCommentService.jangCommentList(recono));
  }

  // Disable (not Delete)
  @GetMapping("disableRecommend")
  public String disableRecommend(int recono, HttpSession session) throws Exception {
    // 현재 사용자가 회원인지 확인
    if (session.getAttribute("loginMember") == null) {
      return "redirect:recommendationList";
    } else {
      Member member = (Member) session.getAttribute("loginMember");
      // 작성자 본인인지 확인
      if (!recommendationService.getRecommendation(recono).getWriter().getId()
              .equals(member.getId())) {
        return "redirect:recommendationList";
        // 제재받은 사용자인지 확인
      } else if (!recommendationService.checkCorrectUser(member.getId())) {
        return "redirect:recommendationList";
      }
    }

    // 삭제를 가장한 비활성화
    if (!recommendationService.disableRecommend(recono)) {
      throw new Exception("코스추천글 삭제 실패");
    }

    return "redirect:recommendationList";
  }

  // Update - 1
  @GetMapping("recommendationUpdateForm")
  public String recommendationUpdate(int recono, HttpSession session, Model model) throws Exception {
    // 현재 사용자가 회원인지 확인
    if (session.getAttribute("loginMember") == null) {
      return "redirect:recommendationList";
    } else {
      Member member = (Member) session.getAttribute("loginMember");
      // 작성자 본인인지 확인
      if (!recommendationService.getRecommendation(recono).getWriter().getId()
              .equals(member.getId())) {
        return "redirect:recommendationList";
        // 제재받은 사용자인지 확인
      } else if (!recommendationService.checkCorrectUser(member.getId())) {
        return "redirect:recommendationList";
      }
    }

    // UpdateForm에 미리 입력할 데이터 담기
    model.addAttribute("recommendation", recommendationService.getRecommendation(recono));
    model.addAttribute("jangSoReviews", recommendationService.getJangSoReviewList(recono));
    return "recommendation/recommendationUpdateForm";
  }

  // Update - 2
  @PostMapping("recommendationUpdateConfirm")
  public String recommendationUpdateConfirm(
          String place1, MultipartFile[] files1, String cont1,
          String place2, MultipartFile[] files2, String cont2,
          String place3, MultipartFile[] files3, String cont3,
          String place4, MultipartFile[] files4, String cont4,
          String place5, MultipartFile[] files5, String cont5,
          String pet, String frd, String cple, String fmly, String solo, String tpname,
          int recono, Recommendation recommendation) throws Exception {

    // 원래 recono 다시 set 하기
    recommendation.setRecono(recono);

    // 변수분류 저장
    MultipartFile[][] files = {files1, files2, files3, files4, files5};
    String[] cont = {cont1, cont2, cont3, cont4, cont5};
    String[] place = {place1, place2, place3, place4, place5};

    // recommendation에 JangSoReview List를 set하기
    recommendation.setJangSoReviews(saveJangSoReviews(files, cont, place));

    // recommendation에 meta 데이터 set하기
    recommendation.setTpname(tpname);
    recommendation.setPet(pet != null);
    recommendation.setFrd(frd != null);
    recommendation.setCple(cple != null);
    recommendation.setFmly(fmly != null);
    recommendation.setSolo(solo != null);

    // recommendation update하기
    recommendationService.recommendationUpdate(recommendation);

    return "redirect:recommendationList";
  }

  // 게시물 신고 처리
  @Transactional
  @PostMapping("recommendationReport")
  public String recommendationReport(
          int recono, String rsn1, String rsn2, HttpSession session) throws Exception {
    // 신고자 로그인상태 확인
    if (session.getAttribute("loginMember") == null) {
      session.setAttribute("reportResult", "empty");
      return "recommendation/recommendationReportFail";
    }

    // 신고자 id 확인
    String id = ((Member) session.getAttribute("loginMember")).getId();

    // 제재당한 이용자는 신고할 수 없다.
    if (!recommendationService.checkCorrectUser(id)) {
      session.setAttribute("reportResult", "incorrect");
      return "recommendation/recommendationReportFail";
    }

    // 본인 게시글은 신고할 수 없다.
    if (recommendationService.getRecommendation(recono).getWriter().getId().equals(id)) {
      session.setAttribute("reportResult", "self");
      return "recommendation/recommendationReportFail";
    }

    // 이미 신고를 5회 받은 게시글은 신고할 수 없다.
    if (recommendationService.countRecommendationReport(recono) >= 5) {
      session.setAttribute("reportResult", "overflow");
      return "recommendation/recommendationReportFail";
    }

    // 신고사유 작성
    switch (Integer.parseInt(rsn1)) {
      case 0: rsn1 = "회원비난/비하";
      case 1: rsn1 = "욕설/비속어";
      case 2: rsn1 = "허위사실 유포";
      case 3: rsn1 = "무단광고/홍보";
      case 4: rsn1 = "외설적 표현물";
      case 5: rsn1 = "불법행위";
      case 6: rsn1 = "게시판 용도 부적절";
      case 7: rsn1 = "이용방해 행위";
    }
    String rsn = "신고사유: " + rsn1 + "\n" + "상세내용: " + rsn2;

    // 신고자가 현재 게시글을 신고한다.
    recommendationService.recommendationReportAdd(id, recono, rsn);

    // 현재 신고당한 게시글의 작성자를 조회한다.
    Member reportedUser = recommendationService.getRecommendation(recono).getWriter();
    int countReport = recommendationService.countReportById(reportedUser.getId());

    // 게시글 작성자 status를 "신고"로 변경
    if (countReport >= 5) {
      recommendationService.updateStatus(reportedUser);
    }
    session.setAttribute("reportResult", "correct");
    return "redirect:recommendationList";
  }

  // JangComment : 코스추천글에 댓글 작성 기능
  @PostMapping("jangCommentInsert")
  public String jangCommentInsert(
          JangComment jangComment, HttpSession session, Model model) throws Exception {
    // 로그인을 하지 않은 경우 댓글 작성이 불가능하다.
    boolean check = checkOwner(session);
    model.addAttribute("checkInsert", check);

    if(check) {
      jangComment.setWriter((Member) session.getAttribute("loginMember"));
      jangCommentService.jangCommentInsert(jangComment);

      // 제재당한 이용자는 댓글을 작성할 수 없다.
      if (!recommendationService.checkCorrectUser(((Member) session.getAttribute("loginMember")).getId())) {
        return "redirect:recommendationList";
      }
    }

    return "redirect:../recommendation/recommendationDetail?recono="+jangComment.getRecono();
  }

  @GetMapping("jangCommentDelete")
  public String jangCommentDelete(int cmno, HttpSession session, JangComment jangComment, Model model) throws Exception {
    int recono = jangCommentService.getJangCommentByCmno(cmno).getRecono();
    boolean check =checkOwner(jangCommentService.getJangCommentByCmno(cmno), session);

    if(check) {
      // 제재당한 이용자는 댓글을 삭제할 수 없다.
      if (!recommendationService.checkCorrectUser(((Member) session.getAttribute("loginMember")).getId())) {
        return "redirect:recommendationList";
      }

      if (!jangCommentService.jangCommentDelete(cmno)) {
        throw new Exception("댓글을 삭제 할 수 없습니다.");
      }
    }

    return "redirect:../recommendation/recommendationDetail?recono="+recono;
  }

  private boolean checkOwner(JangComment jangComment, HttpSession session) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    if(loginMember == null) {
      return false;
    }
    if (!jangComment.getWriter().getId().equals(loginMember.getId())) {
      return false;
    }
    return true;
  }

  private boolean checkOwner(HttpSession session) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    if(loginMember == null) {
      return false;
      //throw new Exception("로그인 이용자만 가능한 기능입니다.");
    }
    System.out.println(loginMember);
    return true;
  }

}
