package com.soen.empower.controller;

import com.soen.empower.entity.Notification;
import com.soen.empower.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @RequestMapping("/{id}")
    public String redirectNotification(HttpSession session, @PathVariable("id") long id){
        Notification notification = notificationService.find(id);
        notificationService.delete(notification);
        long userId = (long) session.getAttribute("user_id");
        session.setAttribute("notifications", notificationService.fetchAll(userId));
        return "redirect:/" + notification.getType();
    }
}
