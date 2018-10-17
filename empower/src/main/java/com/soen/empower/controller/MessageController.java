
package com.soen.empower.controller;

import com.soen.empower.entity.Conversation;
import com.soen.empower.entity.Message;
import com.soen.empower.service.ConversationService;
import com.soen.empower.service.MessageService;
import com.soen.empower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * The Class MessageController.
 */
@Controller
@RequestMapping("/message")
public class MessageController {

    /** The message service. */
    @Autowired
    private MessageService messageService;

    /** The conversation service. */
    @Autowired
    private ConversationService conversationService;

    /** The user service. */
    @Autowired
    private UserService userService;

    /**
     * Index.
     *
     * @param session the session
     * @return the model and view
     */
    @RequestMapping("")
    public ModelAndView index(HttpSession session) {
        ModelAndView model = new ModelAndView("message/index");
        Long currentUserId = (Long) session.getAttribute("user_id");
        model.addObject("conversations", conversationService.fetchAll(currentUserId));
        return model;
    }

    /**
     * Index.
     *
     * @param session the session
     * @param conversationId the conversation id
     * @return the model and view
     */
    @RequestMapping("/{id}")
    public ModelAndView index(HttpSession session, @PathVariable(value = "id") String conversationId) {
        ModelAndView model = new ModelAndView("message/chat");
        Long currentUserId = (Long) session.getAttribute("user_id");
        model.addObject("conversations", conversationService.fetchAll(currentUserId));
        model.addObject("currentConversation", conversationService.fetchById(Long.valueOf(conversationId)));
        model.addObject("messages", messageService.fetch(Long.valueOf(conversationId)));
        return model;
    }

    /**
     * New message.
     *
     * @param session the session
     * @param message the message
     * @return the model and view
     */
    @RequestMapping("/new")
    public ModelAndView newMessage(HttpSession session, @ModelAttribute Message message) {
        return new ModelAndView("message/new");
    }

    /**
     * Adds the new message.
     *
     * @param session the session
     * @param message the message
     * @param conversation the conversation
     * @return the string
     */
    @RequestMapping(value = "/addNewMessage", method = RequestMethod.POST)
    public String addNewMessage(HttpSession session, @ModelAttribute Message message, @ModelAttribute Conversation conversation) {
        Conversation resolvedConversation = conversationService.resolve(conversation);
        message.setConversation(resolvedConversation);
        Message savedMessage = messageService.add(message);
        resolvedConversation.setLastMessageId(savedMessage.getId());
        conversationService.update(resolvedConversation);
        return "redirect:/message/" + resolvedConversation.getId();
    }

}
