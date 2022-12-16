package com.bitcamp.gabojago.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
@RequestMapping("/")
public class ErrorController {
  @GetMapping("error")
  @ExceptionHandler(Exception.class)
  public String errorReport(Model model, Exception e) throws Exception {
    model.addAttribute("stackTrace", exceptionToString(e));
    
    return "error";
  }
  
  private String exceptionToString(Exception e) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    
    e.printStackTrace(pw);

    String str = sw.toString();
    try {
        if (sw != null) {
            sw.flush();
            sw.close();
        }
        if (pw != null) {
            pw.flush();
            pw.close();
        }
    } catch (IOException e1) {}
    
    return str;
  }
}
