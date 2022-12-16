package com.bitcamp.gabojago.web;

import com.bitcamp.gabojago.service.MemberService;
import com.bitcamp.gabojago.vo.Member;
import com.bitcamp.gabojago.vo.Report;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member/")
public class MemberController {


  @Autowired
  MemberService memberService;
  // HttpSession 클래스 주입.
  @Autowired
  private HttpSession session;

  @GetMapping("list")
  public void list(Model model) throws Exception {
    // 프론트 컨트롤러가 건네준 Model 객체에 작업 결과를 담아 두면
    // 핸들러 호출이 끝났을 때 JSP 를 실행하기 전에
    // 먼저 Model 객체에 담아둔 값을 ServletRequest 보관소로 옮긴다.
    model.addAttribute("members", memberService.list());
  }
  @GetMapping("detail")
  public void detail(String id, Map map) throws Exception {
    Member member = memberService.get(id);
    List<Report> report = memberService.findAllReport(id);


    if (member == null) {
      throw new Exception("해당 아이디의 회원이 없습니다.");
    }

    map.put("member", member);
    map.put("reports", report);

  }


@PostMapping("update")
  public String update(Member member) throws Exception {
    if (!memberService.update(member)) {
      throw new Exception("회원 변경 오류입니다!");
    }
    return "redirect:list";
  }

  @GetMapping("delete")
  public String delete(String id) throws Exception {
    if (!memberService.delete(id)) {
      throw new Exception("회원 삭제 오류입니다!");
    }

    return "redirect:list";
  }

  @GetMapping("findid/{name}/{email}")
  @ResponseBody
  public String findId(@PathVariable("name") String name, @PathVariable("email") String email) throws Exception {
    Map<String, String> map = new HashMap();
    map.put("name", name);
    map.put("email", email);

    Member member = memberService.findId(map);

    if(member ==null){
      return "입력한 정보에 일치하는 회원이 존재하지 않습니다";
    }
    return member.getId() ;
  }

  @GetMapping("findid")
  public void findId()  throws Exception {

  }



  //  비밀번호 찾기
  @GetMapping("findpwd/{id}/{name}/{email}")
@ResponseBody
  public String findpwd(@PathVariable("id") String id,@PathVariable("name") String name,@PathVariable("email") String email) throws Exception {
    Map<String, String> map = new HashMap();
    map.put("id", id);
    map.put("name", name);
    map.put("email", email);

    Member member = memberService.findpwd(map);

    if (member == null) {
      return "입력한 정보에 일치하는 회원이 존재하지 않습니다";
    }
    return member.getPassword();
  }

  @GetMapping("findpwd")
  public void findpwd()  throws Exception {

  }

  static final String BASE_URL = "https://kauth.kakao.com";
  static final String AUTHORIZE_URL = "/oauth/authorize";
  static final String TOKEN_URL = "/oauth/token";
  static final String REST_API_KEY = "88b44f74865aa118de9f54888c85a112";
  static final String REDIRECT_URL = "http://192.168.0.114/app/member/kakaoLogin";

  @RequestMapping("kakaoLoginPage")
  String kakaoLoginPage() {
    String parameter = String.format("?client_id=%s&redirect_uri=%s&response_type=%s",
        REST_API_KEY,
        REDIRECT_URL,
        "code");

    return String.format("redirect:%s%s%s", BASE_URL, AUTHORIZE_URL, parameter);
  }

  @RequestMapping("kakaoLogin")
  public ModelAndView kakaoLogin(
      @RequestParam String code,
      HttpServletResponse response,
      HttpSession session) throws Exception {
    URL url = new URL(BASE_URL+TOKEN_URL);
    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
    connection.setDoOutput(true);

    try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()))) {
      writer.write(String.format("grant_type=%s&client_id=%s&redirect_uri=%s&code=%s",
          "authorization_code",
          REST_API_KEY,
          REDIRECT_URL,
          code));
      writer.flush();
    }

    StringBuilder builder = new StringBuilder();
    String temp;
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
      while ((temp = reader.readLine()) != null) builder.append(temp);
    }
    Map<String, Object> responseData = jsonToMap(builder.toString());
    Map<String, Object> userData = userData((String)responseData.get("access_token"));

    Member member = memberService.selectKakaoId((long)userData.get("id"));

    if (member != null) {
      session.setAttribute("loginMember", member); // 로그인한 멤버 정보를 세션 보관소에 저장

      // 클라이언트에게 쿠키 보내기
      // - 쿠키 데이터는 문자열만 가능
      Cookie cookie = new Cookie("id", member.getId()); // 클라이언트 쪽에 저장할 쿠키 생성
      cookie.setPath("/app");
    }

    ModelAndView mv = new ModelAndView("/auth/loginResult");
    mv.addObject("member", member);
    return mv;
  }

  Map<String, Object> userData(@RequestParam String accessToken) throws Exception {
    URL url = new URL("https://kapi.kakao.com/v2/user/me");
    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Authorization", String.format("Bearer %s", accessToken));
    connection.setDoOutput(true);

    StringBuilder builder = new StringBuilder();
    String temp;
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
      while ((temp = reader.readLine()) != null) builder.append(temp);
    }
    Map<String, Object> responseData = jsonToMap(builder.toString());

    return responseData;
  }

  private static ObjectMapper objectMapper;
  private static ObjectMapper getObjectMapper() {
    if (objectMapper == null) {
      objectMapper = new ObjectMapper();
    }
    return objectMapper;
  }
  public static Map<String, Object> jsonToMap(String json) throws Exception {
    return getObjectMapper().readValue(json, new TypeReference<Map<String, Object>>(){});
  }
}
