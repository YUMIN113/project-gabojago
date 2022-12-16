package com.bitcamp.gabojago.web;

import com.bitcamp.gabojago.service.EventService;
import com.bitcamp.gabojago.vo.Member;
import com.bitcamp.gabojago.vo.PageResponseDto;
import com.bitcamp.gabojago.vo.event.Event;
import com.bitcamp.gabojago.vo.event.EventAttachedFile;
import com.bitcamp.gabojago.vo.event.EventItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/support/event/")
public class EventController {

    ServletContext sc;
    @Autowired
    EventService eventService;
    private int PAGE_CORRECTION = 1;

    public EventController(EventService eventService, ServletContext sc) {
        System.out.println("EventController() 호출됨>-<");
        this.eventService = eventService;
        this.sc = sc;
    }

    @GetMapping("list")
    public void list(Model model, @RequestParam("page") Integer page, @RequestParam(value = "size", defaultValue = "3") Integer size) throws Exception {
        int total = eventService.eventPostCount();

        page -= PAGE_CORRECTION;
        List<Event> list = eventService.list((page) * size, size);
        PageResponseDto<Event> eventPageResponseDto = new PageResponseDto<>(page, size, total, list);

        model.addAttribute("events", eventPageResponseDto.getDtoList());
        model.addAttribute("pages", eventPageResponseDto.getPage());
        model.addAttribute("pageTotal", eventPageResponseDto.getTotal());
        model.addAttribute("pageStart", eventPageResponseDto.getStart());
        model.addAttribute("pageEnd", eventPageResponseDto.getEnd());
        model.addAttribute("prev", eventPageResponseDto.isPrev());
        model.addAttribute("next", eventPageResponseDto.isNext());
    }

    @GetMapping("addForm")
    public void addForm() throws Exception {

    }

    @PostMapping("add")
    public String add(
            Event event,
            HttpSession session) throws Exception {
        System.out.println("eventController : " + event.toString());
        eventService.add(event);
        return "redirect:list?page=1";
    }

    @GetMapping("detail")
    public void detail(int no, Model model) throws Exception {
        model.addAttribute("event", eventService.get(no));
        model.addAttribute("eventItems", eventService.itemList(no));
        eventService.addViewCount(no);
    }

    @GetMapping("delete")
    public String delete(int no) throws Exception {
        eventService.delete(no);
        return "redirect:list?page=1";
    }

    @GetMapping("editForm")
    public void editForm(int no, Model model) throws Exception {
        model.addAttribute("event", eventService.get(no));
    }

    @PostMapping("update")
    public String update(
            Event event,
            HttpSession session) throws Exception {
        System.out.println("EventControllerUpdate :" + event.toString());
        eventService.update(event);
        return "redirect:list?page=1";
    }

    @GetMapping("item/addForm")
    public void itemAddForm(int no, Model model) throws Exception {
        model.addAttribute("eventNo", no);
    }

    @PostMapping("item/itemadd")
    public String itemAdd(
            EventItem eventItem,
            MultipartFile file,
            HttpSession session) throws Exception {
        EventAttachedFile eventAttachedFile = saveAttachedFile(file);
        eventItem.setEventAttachedFile(eventAttachedFile);
        eventService.itemAdd(eventItem);
        return "redirect:../detail?no=" + eventItem.getEventNo();
    }

    private EventAttachedFile saveAttachedFile(MultipartFile file)
            throws IOException, ServletException {
        EventAttachedFile eventAttachedFile = new EventAttachedFile();
        String dirPath = sc.getRealPath("/event/files");
        if (file.isEmpty()) {
            return eventAttachedFile;
        }

        String fileName = UUID.randomUUID().toString();
        file.transferTo(new File(dirPath + "/" + fileName + "." + file.getContentType().split("/")[1]));
        eventAttachedFile.setFileName(fileName + "." + file.getContentType().split("/")[1]);
        eventAttachedFile.setFilePath(dirPath+"/");
        return eventAttachedFile;
    }
}
