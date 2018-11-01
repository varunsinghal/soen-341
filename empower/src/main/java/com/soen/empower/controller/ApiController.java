package com.soen.empower.controller;

import com.soen.empower.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Rest controller to provide services in the form of API.
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private MessageService messageService;

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
}
