package com.bitcamp.gabojago.service.payment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bitcamp.gabojago.dao.cart.CartDao;
import com.bitcamp.gabojago.vo.Baguni;
import com.bitcamp.gabojago.vo.Member;
import com.bitcamp.gabojago.vo.OrderingInformation;

@Service
public class PaymentService {
  
  @Autowired
  CartDao cartDao;
  
  @Transactional
  public List<Map<String, String>> getCartList(Member member) {
    return cartDao.getCartList(member.getId());
  }
  
  @Transactional
  public List<Map<String, String>> getCheckedCartList(Member member, String exno){
    return cartDao.getCheckedCartList(member.getId(), exno.split(","));
  }
  
  @Transactional
  public void insertOrderingInfo (String paymentType, Member member, String exno) {
    List<OrderingInformation> infoList = cartDao.getOrderingInfo(exno.split(","));
    
    for(OrderingInformation info : infoList) {      
      info.setId(member.getId());
      info.setPayment(paymentType);
      info.setPurdate(new SimpleDateFormat ("yyyy-MM-dd").format(new Date()));
    
      cartDao.insertOrderingInfo(info);
    }
  }
  
  @Transactional
  public void addBaguni(Baguni baguni) {
    cartDao.addBaguni(baguni);
  }
  
  @Transactional
  public List<Map<String, String>> getOrderingInfo(Member member) {
    return cartDao.getTicket(member.getId());
  }
  
  @Transactional
  public List<Map<String, String>> getOrderingInfoDetail(Member member, String exno) {
    return cartDao.getTicketDetail(member.getId(), exno);
  }
  
  @Transactional
  public void deleteBaguni(Member member, String exno) {
    cartDao.deleteBaguni(member.getId(), exno.split(","));
  }
}
