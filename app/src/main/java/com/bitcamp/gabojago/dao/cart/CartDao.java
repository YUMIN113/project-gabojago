package com.bitcamp.gabojago.dao.cart;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.bitcamp.gabojago.vo.Baguni;
import com.bitcamp.gabojago.vo.OrderingInformation;

@Mapper
public interface CartDao {

  List<Map<String, String>> getCartList(String id);
	
  List<Map<String, String>> getCheckedCartList( @Param("id") String id, @Param("array") String [] exno);
  
  List<OrderingInformation> getOrderingInfo(@Param("array") String [] exno);
  
  void insertOrderingInfo(OrderingInformation info);
  
  void addBaguni(Baguni baguni);
  
  void deleteBaguni( @Param("id") String id, @Param("array") String [] exno);
  
  List<Map<String, String>> getTicket(String id);
  
  List<Map<String, String>> getTicketDetail( @Param("id") String id, @Param("exno") String exno);
}
