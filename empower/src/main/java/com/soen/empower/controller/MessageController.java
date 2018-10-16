
package com.soen.empower.controller;

import com.soen.empower.entity.Conversation;
import com.soen.empower.entity.Message;
import com.soen.empower.service.ConversationService;
import com.soen.empower.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ConversationService conversationService;

    @RequestMapping("")
    public ModelAndView index(HttpSession session) {
        ModelAndView model = new ModelAndView("message/index");
        Long currentUserId = (Long) session.getAttribute("user_id");
        model.addObject("conversations", conversationService.fetchAll(currentUserId));
        return model;
    }

    @RequestMapping("/{id}")
    public ModelAndView index(HttpSession session, @PathVariable(value = "id") String conversationId) {
        ModelAndView model = new ModelAndView("message/chat");
        Long currentUserId = (Long) session.getAttribute("user_id");
        model.addObject("conversations", conversationService.fetchAll(currentUserId));
        model.addObject("currentConversation", conversationService.fetchById(Long.valueOf(conversationId)));
        model.addObject("messages", messageService.fetch(Long.valueOf(conversationId)));
        return model;
    }

  

}
