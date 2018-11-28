package com.soen.empower.service;

import com.soen.empower.entity.Card;
import com.soen.empower.entity.Comment;
import com.soen.empower.entity.User;
import com.soen.empower.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
class TagService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationService notificationService;


    Card process(Card card) {
        Pattern p = Pattern.compile("@(\\w+)");   // the pattern to search for
        Matcher m = p.matcher(card.getText());
        while (m.find()) {
            User user = userRepository.findByUsername(m.group(1));
            card.setText(card.getText().replace("@" + user.getUsername()+" ", "<a href='/user/profile/" + user.getId() + "'>@" + user.getUsername() + "</a> "));
            notificationService.notify(user, card);
        }
        return card;
    }

    Comment process(Comment comment) {
        Pattern p = Pattern.compile("@(\\w+)");   // the pattern to search for
        Matcher m = p.matcher(comment.getText());
        while (m.find()) {
            User user = userRepository.findByUsername(m.group(1));
            comment.setText(comment.getText().replace("@" + user.getUsername()+" ", "<a href='/user/profile/" + user.getId() + "'>@" + user.getUsername() + "</a> "));
            notificationService.notify(user, comment);
        }
        return comment;
    }


}
