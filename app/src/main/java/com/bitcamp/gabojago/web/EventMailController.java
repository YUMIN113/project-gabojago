package com.bitcamp.gabojago.web;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test/mail")
public class EventMailController {

    private JavaMailSender javaMailSender;

    public EventMailController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @GetMapping("/")
    public String testMailSender() {


        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("cfy113@naver.com");
        simpleMailMessage.setSubject("황망하다");
        simpleMailMessage.setText("황망해");
        javaMailSender.send(simpleMailMessage);
        return "/app";
    }
}
