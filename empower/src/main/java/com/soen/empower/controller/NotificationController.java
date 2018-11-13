package com.soen.empower.controller;

import com.soen.empower.entity.Notification;
import com.soen.empower.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Notification controller to redirect user from one window to another.
 */
@Controller
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    /**
     * This method forces the session to update itself once the notification has been visited.
     * As it is deleted from the database.
     *
     * @param session HttpSession of logged in user
     * @param id      notification id
     * @return redirect to notification type window.
     */
    @RequestMapping("/{id}")
    public String redirectNotification(HttpSession session, @PathVariable("id") long id) {
        Notification notification = notificationService.find(id);
        notificationService.delete(notification);
        long userId = (long) session.getAttribute("user_id");
        session.setAttribute("notifications", notificationService.fetchAll(userId));
        return "redirect:/" + notification.getType();
    }
}
