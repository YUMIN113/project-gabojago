package com.bitcamp.gabojago.service;

import com.bitcamp.gabojago.dao.ExhibitionReviewDao;
import com.bitcamp.gabojago.dao.MemberDao;
import com.bitcamp.gabojago.vo.Member;
import com.bitcamp.gabojago.vo.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

@Service
public class DefaultMemberService implements MemberService {
  @Autowired
  MemberDao memberDao;

  @Autowired
  ExhibitionReviewDao exhibitionReviewDao;



  @Override
  public Member idCheck(String id) throws Exception {
    return memberDao.findById(id);
  }

  @Override
  public Member nickNameCheck(String nickName) throws Exception {
    return memberDao.findByNickName(nickName);
  }

  @Override
  public Member get(String id, String password) throws Exception {
    return memberDao.findByIdPassword(id, password);
  }

  @Override
  public boolean join(String email, String phoneNo, Member member) throws Exception {
    if (memberDao.findByEmail(email) != null
        || memberDao.findByPhoneNo(phoneNo) != null) {
      return false;
    }
    memberDao.join(member);
    return true;
  }

  @Override
  public Member phoneNoCheck(String phoneNo) throws Exception {
    return memberDao.findByPhoneNo(phoneNo);
  }

  @Override
  public Member emailCheck(String email) throws Exception {
    return memberDao.findByEmail(email);
  }



  /* 밑으로 꼉이 수정중*/
  @Transactional
  @Override
  public boolean delete(String id) throws Exception {
    /*boardDao.deleteFilesByMemberBoards(no); // 회원이 작성한 게시글의 모든 첨부파일 삭제
    boardDao.deleteByMember(no); // 회원이 작성한 게시글 삭제*/
/*
    exhibitionReviewDao.deleteByMember(Integer.parseInt(id)); // 전시회 리뷰 삭제
*/
    return memberDao.delete(id) > 0; // 회원 삭제
  }

  @Override
  public List<Member> list() throws Exception {
    return memberDao.findAll();
  }

  @Override
  public Member get(String id)throws Exception {
    return memberDao.findByIdAll(id);
  }

  public boolean update(Member member) throws Exception {
    return memberDao.update(member) > 0;
  }

  @Override
  public Member findId(Map<String, String> map) throws Exception {
    return memberDao.findId(map);

  }
  @Override
  public Member findpwd(Map<String, String> map) throws Exception {
    Member member = memberDao.findpwd(map);
    if (member == null) return member;

    String newPw = Integer.toString((int)(Math.random()*(100000000-10000000)+10000000));
    map.put("password", newPw); // map정보 받아서 pw 수정
    memberDao.findpwdupdate(map); // pw 업데이트

    String id = "";
    String pw = "";

    Properties prop = new Properties();
    prop.put("mail.smtp.host", "smtp.naver.com");
    prop.put("mail.smtp.port", 465);
    prop.put("mail.smtp.auth", "true");
    prop.put("mail.smtp.ssl.enable", "true");
    prop.put("mail.smtp.ssl.trust", "smtp.naver.com");

    Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(id, pw);
      }
    });

    MimeMessage message = new MimeMessage(session);
    message.setFrom(new InternetAddress(id));

    message.addRecipient(Message.RecipientType.TO, new InternetAddress(member.getEmail()));
    message.setSubject("가보자Go 임시 비밀번호를 발급해드립니다.");
    message.setText("안녕하세요 회원님 가보자Go의 임시비밀번호 발송해드립니다\n" + " 회원님의 임시비밀번호는" +
                    "임시 비밀번호: " + newPw);
    Transport.send(message);
    System.out.println("임시비밀번호 메일 전송 완료");

    return member;
  }

  @Override
  public Report findByIdReport(String id) throws Exception {
    return memberDao.findByIdReport(id);
  }
  @Override
  public List<Report> findAllReport(String id) throws Exception {
    return memberDao.findAllReport(id);
  }

  public Member selectKakaoId(long kakaoId) {
    return memberDao.selectKakaoId(kakaoId);
  }
}
