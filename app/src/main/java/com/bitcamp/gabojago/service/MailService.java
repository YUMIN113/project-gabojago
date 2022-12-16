package com.bitcamp.gabojago.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import com.bitcamp.gabojago.vo.MailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

  private final JavaMailSender emailSender;
  private final SpringTemplateEngine templateEngine;

  public void sendTemplateMessage(MailDto mailDto) throws MessagingException {
    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
    System.out.println("민구메일 전송 완료");
    //메일 제목 설정
    helper.setSubject(mailDto.getTitle());

    //수신자 설정
    helper.setTo(mailDto.getAddress());

    String email = mailDto.getAddress();
    String checkNum = String.valueOf(mailDto.getCheckNum());

    Context context = new Context();
    context.setVariable("email", email);
    context.setVariable("checkNum", checkNum);

    //메일 내용 설정 : 템플릿 프로세스
    String html = templateEngine.process("emailCode", context);
    helper.setText(html, true);

    //메일 보내기
    emailSender.send(message);
  }
}