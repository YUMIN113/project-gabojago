package com.bitcamp.gabojago.web;

import com.bitcamp.gabojago.service.ExhibitionReviewService;
import com.bitcamp.gabojago.service.ExhibitionService;
import com.bitcamp.gabojago.vo.Exhibition;
import com.bitcamp.gabojago.vo.ExhibitionReview;
import com.bitcamp.gabojago.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller // 주소 전달
@RequestMapping("/exhibition-review/")
public class ExhibitionReviewController {

@Autowired
ExhibitionReviewService exhibitionReviewService;

  @ResponseBody  //데이터 전달 빠르게 확인 가능
  @RequestMapping("review-select-list/{exno}")
  public List<ExhibitionReview> exhibitionReviewList(@PathVariable("exno") int exno) throws Exception{
    return exhibitionReviewService.exhibitionReviewList(exno);
  }

  @PostMapping("add")
  public String exhibitionReviewInsert(ExhibitionReview exhibitionReview,
      HttpSession session) throws Exception {
    exhibitionReview.setWriter((Member) session.getAttribute("loginMember"));
    exhibitionReviewService.exhibitionReviewInsert(exhibitionReview);
    return "redirect:../exhibition/detail?exno="+exhibitionReview.getExno();
  }

  @GetMapping("delete")
  public String exhibitionReviewDelete(int rvno, HttpSession session, ExhibitionReview exhibitionReview) throws Exception {
    int exno = checkOwner(rvno, session);
    if(!exhibitionReviewService.exhibitionReviewDelete(rvno)) {
      throw new Exception("리뷰를 삭제 할 수 없습니다.");
    }
    return "redirect:../exhibition/detail?exno=" + exno;
  }

  @PostMapping("update")
  public String update(ExhibitionReview exhibitionReview, HttpSession session) throws Exception{

     checkOwner(exhibitionReview.getRvno(),session);

    if(!exhibitionReviewService.exhibitionReviewUpdate(exhibitionReview)){
      throw new Exception("리뷰를 변경 할 수 없습니다!");
    }
    return "redirect:../exhibition/exhibitionlist";
  }

  private int checkOwner(int rvno, HttpSession session) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
  ExhibitionReview review  = exhibitionReviewService.get(rvno) ;
    if (!exhibitionReviewService.get(rvno).getId().equals(loginMember.getId())) {
      throw new Exception("리뷰 작성자가 아닙니다.");
    }

    return review.getExno();
  }


}
