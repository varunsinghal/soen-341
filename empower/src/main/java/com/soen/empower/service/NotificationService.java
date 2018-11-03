package com.soen.empower.service;

import com.soen.empower.entity.Message;
import com.soen.empower.entity.Notification;
import com.soen.empower.entity.User;
import com.soen.empower.repository.NotificationRepository;
import com.soen.empower.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Service class for sending notification messages.
 */
@Service
public class NotificationService {

    // The SimpMessagingTemplate is used to send Stomp over WebSocket messages.
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Send notification to users subscribed on channel "/user/queue/notify".
     * <p>
     * The message will be sent only to the user with the given username.
     *
     * @param message The notification message.
     */
    public void notify(Message message) {
        User toUser = userRepository.findById(message.getTo().getId());
        User fromUser = userRepository.findById(message.getFrom().getId());
        String HTML = fromUser.getFullName() + " messaged you: " + message.getText();
        sendNotification(toUser, HTML);
    }

    private void sendNotification(User toUser, String HTML) {
        Notification notification = new Notification(HTML, toUser);
        notification = notificationRepository.save(notification);
        String wrappedHTML = "<a href='#' notification-id ='" + notification.getId() + "' class=''>" + HTML + "</a>";
        messagingTemplate.convertAndSendToUser(toUser.getUsername(), "/queue/notify", wrappedHTML);
    }


}

