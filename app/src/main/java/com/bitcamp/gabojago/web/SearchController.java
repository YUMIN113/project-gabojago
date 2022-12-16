package com.bitcamp.gabojago.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bitcamp.gabojago.service.search.exhibition.ExhibitionSearchService;
import com.bitcamp.gabojago.service.search.exhibition.ExhibitionSearchType;
import com.bitcamp.gabojago.service.search.member.MemberSearchService;
import com.bitcamp.gabojago.service.search.member.MemberSearchType;

@Controller
@RequestMapping("/search/")
public class SearchController {
  
  @Autowired
  ExhibitionSearchService exhibitionSearchService;
  
  @Autowired
  MemberSearchService memberSearchService;
  
  @GetMapping("searchForm")
  public void search(Model model) throws Exception {
  }
  
  @GetMapping("searchResult")
  public void searchResult(Model model, String keyword) throws Exception {
    model.addAttribute("exhibitionResult", exhibitionSearchService.getResult(ExhibitionSearchType.TITLE, keyword));
    model.addAttribute("memberResult", memberSearchService.getResult(MemberSearchType.PUBLIC, keyword));      
  }
}
