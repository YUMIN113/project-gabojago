package com.bitcamp.gabojago.web.filter;

import com.bitcamp.gabojago.vo.Member;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AdminCheckFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    System.out.println("AdminCheckFilter.init() 실행!");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;

    System.out.println("AdminCheckFilter.doFilter() 실행!");

    System.out.println(httpServletRequest.getContextPath());
    String servletPath = httpServletRequest.getServletPath();
    System.out.println(servletPath);

    // 콘텐트를 등록,변경,삭제하는 경우 로그인 여부를 검사한다.
    if (servletPath.toLowerCase().endsWith("exhibition/form") ||
        servletPath.toLowerCase().endsWith("exhibition/updateform") ||
        servletPath.toLowerCase().endsWith("exhibition/update") ||
        servletPath.toLowerCase().endsWith("exhibition/delete")) {

      Member loginMember = (Member) httpServletRequest.getSession().getAttribute("loginMember");
      if (loginMember == null || // 로그인이 안됐거나
          !loginMember.getId().equals("admin")) { // 관리자가 아니라면
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/exhibition/exhibitionlist");
        return;
      }
    }

    if (httpServletRequest.getServletPath().startsWith("/member") //회원관리페이지
        && !httpServletRequest.getServletPath().startsWith("/member/findid") // 아이디찾기엔 필터넘어감
        && !httpServletRequest.getServletPath().startsWith("/member/findpwd")// 비밀번호찾기엔 필터넘어감
        && !httpServletRequest.getServletPath().startsWith("/member/kakaoLogin") // 카카오 로그인 필터넘어감
    ) {
      Member loginMember = (Member) httpServletRequest.getSession().getAttribute("loginMember");
      if (loginMember == null || // 로그인이 안됐거나
          !loginMember.getId().equals("admin")) { // 관리자가 아니라면
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");
        return;
      }
    }

    chain.doFilter(request, response);
  }
}