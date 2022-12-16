package com.bitcamp.gabojago.web.payment;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bitcamp.gabojago.service.payment.PaymentService;
import com.bitcamp.gabojago.vo.Member;

@Controller
@RequestMapping("/payment/")
public class PaymentController {
  
  @Autowired
  PaymentService paymentService;
  
  @GetMapping("showCart")
  public String showCart(Model model, HttpSession session) throws Exception {
    Member member = (Member) session.getAttribute("loginMember");
    List<Map<String, String>> cartList = paymentService.getCartList(member);
    
    
    if(cartList.isEmpty()) {      
      return "payment/emptyShowCart";
    }
    else {
      model.addAttribute("cartList", paymentService.getCartList(member));
      return "payment/showCart";
    }
  }
  
  @GetMapping("paymentPage")
  public String paymentPage (Model model, HttpSession session, String exno) throws Exception {
    Member member = (Member) session.getAttribute("loginMember");
    
    model.addAttribute("cartList", paymentService.getCheckedCartList(member, exno));
    model.addAttribute("exno", exno);
    
    return "payment/paymentPage";
  }
  
  @GetMapping("paymentSuccessful")
  public String paymentSuccessful (Model model, HttpSession session, String paymentType, String exno, String totalPrice) throws Exception {
    Member member = (Member) session.getAttribute("loginMember");
    
    paymentService.insertOrderingInfo(paymentType, member, exno);
    paymentService.deleteBaguni(member, exno);
    
    model.addAttribute("totalPrice", totalPrice);
    
    return "payment/paymentSuccessful";
  }
  
  @GetMapping("showOrderingInfo")
  public String showOrderingInformation(Model model, HttpSession session) {
    Member member = (Member) session.getAttribute("loginMember");
    
    model.addAttribute("orderingInfoList", paymentService.getOrderingInfo(member));
    
    if (member.getId().equals("admin")) {
      return "payment/showAdminOrderingInfo";
    } else {
      return "payment/showOrderingInfo";
    }
  }
  
  @GetMapping("showOrderInfoDetail")
  public String showOrderInfoDetail(Model model, HttpSession session, String exno) {
    Member member = (Member) session.getAttribute("loginMember");
    
    model.addAttribute("orderingInfo", paymentService.getOrderingInfoDetail(member, exno));
    
    return null;
  }
}
