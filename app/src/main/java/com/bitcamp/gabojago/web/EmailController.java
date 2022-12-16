package com.bitcamp.gabojago.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.bitcamp.gabojago.service.EmailService;
import com.bitcamp.gabojago.vo.event.EmailMessage;
import lombok.RequiredArgsConstructor;

//@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService = null;
    
    @GetMapping("/send-mail")
    public ResponseEntity sendMail() {
        System.out.println("sendMail Controller");
        EmailMessage emailMessage = EmailMessage.builder()
                .to("받는이이메일@gmail.com")
                .subject("엉아 발송 테스트 메일 제목")
                .message("<html><head></head><body><div style=\"background-color: gray;\">엉아 발송 테스트 메일 본문<div></body></html>")
                .build();
        emailService.sendMail(emailMessage);
        return new ResponseEntity(HttpStatus.OK);
    }
}

