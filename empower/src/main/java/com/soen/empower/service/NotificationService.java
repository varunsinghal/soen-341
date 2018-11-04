package com.soen.empower.service;

import com.soen.empower.entity.Card;
import com.soen.empower.entity.Message;
import com.soen.empower.entity.Notification;
import com.soen.empower.entity.User;
import com.soen.empower.repository.NotificationRepository;
import com.soen.empower.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        sendNotification(toUser, HTML, "message");
    }

    public void notify(Card card) {
        User toUser = userRepository.findById(card.getBelongsTo().getId());
        User fromUser = userRepository.findById(card.getUser().getId());
        if(!toUser.getId().equals(fromUser.getId())) {
            String HTML = fromUser.getFullName() + " posted on your wall about '" + card.getTitle() + "' : " + card.getText();
            sendNotification(toUser, HTML, "wall");
        }
    }

    private void sendNotification(User toUser, String HTML, String type) {
        Notification notification = new Notification(HTML, toUser, type);
        notification = notificationRepository.save(notification);
        String wrappedHTML = "<li>" + "<a href='/notification/" + notification.getId() + "' class='dropdown-item'>"
                + HTML + "</a></li>";
        messagingTemplate.convertAndSendToUser(toUser.getUsername(), "/queue/notify", wrappedHTML);
    }


    public List<Notification> fetchAll(Long id) {
        return notificationRepository.findByUserIdOrderByIdDesc(id);
    }

    public Notification find(long id) {
        return notificationRepository.findById(id);
    }

    public void delete(Notification notification) {
        notificationRepository.delete(notification);
    }

}

