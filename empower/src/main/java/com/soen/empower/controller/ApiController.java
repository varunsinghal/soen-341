package com.soen.empower.controller;

import com.soen.empower.service.MessageService;
import com.soen.empower.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Rest controller to provide services in the form of API.
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private NotificationService notificationService;

    /**
     * To search the database via service layer to search the users.
     * Since, this service is dedicated to the messaging it generates the JSON in the format -
     * { "label" : fullName , "value": userId}
     *
     * @param name full name of the user
     * @return JSON
     */
    @RequestMapping("/users")
    public List<Object> fetchUsers(@RequestParam("term") String name) {
        return messageService.findUsersForNewMessage(name);
    }

    /**
     * In case, there is an update in session variable via repository layer, and session
     * variables are created only once when the user logs in the application. This API is
     * used to refresh the session context.
     * Currently, only notification has been identified to be refreshed.
     *
     * @param session HttpSession of logged in user
     * @return success string.
     */
    @RequestMapping("/session")
    public String updateSession(HttpSession session) {
        long userId = (long) session.getAttribute("user_id");
        session.setAttribute("notifications", notificationService.fetchAll(userId));
        return "Done";
    }

}
