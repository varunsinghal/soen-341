package com.soen.empower.controller;

import com.soen.empower.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/users")
    public List<Object> fetchUsers(@RequestParam("term") String name) {
        return messageService.findUsersForNewMessage(name);
    }
}
