//package com.bitcamp.gabojago.web.filter;
//
//import com.bitcamp.gabojago.vo.Member;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class RecommendationLoginCheckFilter implements Filter {
//
//  @Override
//  public void init(FilterConfig filterConfig) throws ServletException {
//    System.out.println("RecommendationLoginCheckFilter.init() 실행!");
//  }
//
//  @Override
//  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//
//    System.out.println("RecommendationLoginCheckFilter.doFilter() 실행!");
//
//    System.out.println(httpServletRequest.getContextPath());
//    String servletPath = httpServletRequest.getServletPath();
//    System.out.println(servletPath);
//
//    // 콘텐트를 등록,변경,삭제하는 경우 로그인 여부를 검사한다.
//    if (servletPath.toLowerCase().endsWith("recommendation/recommendationForm") ||
////        servletPath.toLowerCase().endsWith("recommendation/updateform") ||
//        servletPath.toLowerCase().endsWith("recommendation/recommendationAdd") ||
//        servletPath.toLowerCase().endsWith("recommendation/disableRecommend") ||
//        servletPath.toLowerCase().endsWith("recommendation/jangCommentAdd") ||
//        servletPath.toLowerCase().endsWith("recommendation/jangCommentDelete")) {
//      Member loginMember = (Member) httpServletRequest.getSession().getAttribute("loginMember");
//      if (loginMember == null || // 로그인이 안됐거나
//          !loginMember.getId().equals("admin")) { // 관리자가 아니라면
//        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/recommendation/recommendationList");
//        return;
//      }
//    }
//
//
//    chain.doFilter(request, response);
//  }
//}