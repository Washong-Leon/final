package com.clin.controller;

import com.clin.entity.Message;
import com.clin.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageController {
    @Resource
    private MessageService messageService;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        List<Message> list=messageService.findAll();
        model.addAttribute("list",list);
        return "message";
    }

}
