package com.soen.empower.service;

import com.soen.empower.entity.*;
import com.soen.empower.repository.CardRepository;
import com.soen.empower.repository.NotificationRepository;
import com.soen.empower.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private CardRepository cardRepository;

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

    /**
     * Send notification to users subscribed on channel "/user/queue/notify".
     * <p>
     * The message will be sent only to the user with the given username.
     *
     * @param card The feed story's card.
     */
    public void notify(Card card) {
        User toUser = userRepository.findById(card.getBelongsTo().getId());
        User fromUser = userRepository.findById(card.getUser().getId());
        if (!toUser.getId().equals(fromUser.getId())) {
            String HTML = fromUser.getFullName() + " posted on your wall about : " + card.getTitle();
            sendNotification(toUser, HTML, "wall");
        }
    }

    /**
     * Send notification to users subscribed on channel "/user/queue/notify".
     * <p>
     * The message will be sent only to the user with the given username.
     * Notification is sent to the wall owner.
     *
     * @param comment comment on the wall post.
     */
    public void notify(Comment comment) {
        Card card = cardRepository.findById((long) comment.getCard().getId());
        User toUser = userRepository.findById(card.getBelongsTo().getId());
        User fromUser = userRepository.findById(comment.getUser().getId());
        if (!toUser.getId().equals(fromUser.getId())) {
            String HTML = fromUser.getFullName() + " commented on the post : " + comment.getText();
            sendNotification(toUser, HTML, "wall");
        }
    }

    public void notify(Friend friend) {
        if (friend.getEnabled() == 0) { // new friend request
            User toUser = userRepository.findById(friend.getOtherUser().getId());
            User fromUser = userRepository.findById(friend.getUser().getId());
            String HTML = fromUser.getFullName() + " sent you the friend request";
            sendNotification(toUser, HTML, "friend/received");
        } else if (friend.getEnabled() == 1) { // accepted friend request
            User toUser = userRepository.findById(friend.getUser().getId());
            User fromUser = userRepository.findById(friend.getOtherUser().getId());
            String HTML = fromUser.getFullName() + " accepted your friend request";
            sendNotification(toUser, HTML, "friend");
        }
    }

    public void notify(Group group) {
        for (User admin : group.getAdmins()) {
            String HTML = group.getName() + " has pending join requests";
            sendNotification(admin, HTML, "group/" + group.getId() + "/requests");
        }
    }

    public void notify(Group group, long userId) {
        User toUser = userRepository.findById(userId);
        String HTML = "Your request to join " + group.getName() + " has been accepted.";
        sendNotification(toUser, HTML, "group/" + group.getId() + "/wall");
    }

    void notify(User tag, Card card) {
        User cardOwner = userRepository.findById(card.getUser().getId());
        String HTML = "You have been tagged to a post by " + cardOwner.getFullName();
        String type = card.getBelongsTo() == null ? "group/" + card.getBelongsToGroup().getId() + "/wall" : "wall/" + card.getBelongsTo().getId();
        sendNotification(tag, HTML, type);
    }

    void notify(User tag, Comment comment){
        Card card = cardRepository.findById((long) comment.getCard().getId());
        User commentOwner = userRepository.findById(comment.getUser().getId());
        String HTML = "You have been tagged in a comment by " + commentOwner.getFullName();
        String type = card.getBelongsTo() == null ? "group/" + card.getBelongsToGroup().getId() + "/wall" : "wall/" + card.getBelongsTo().getId();
        sendNotification(tag, HTML, type);
    }

    /**
     * Save the notification and send it to the channel /queue/notify.
     *
     * @param toUser user id
     * @param HTML   content
     * @param type   platform of notification - message, comment, wall
     */
    private void sendNotification(User toUser, String HTML, String type) {
        Notification notification = new Notification(HTML, toUser, type);
        notification = notificationRepository.save(notification);
        String wrappedHTML = "<li>" + "<a href='/notification/" + notification.getId() + "' class='dropdown-item'>"
                + HTML + "</a></li>";
        messagingTemplate.convertAndSendToUser(toUser.getUsername(), "/queue/notify", wrappedHTML);
    }


    /**
     * Fetch all notifications for a given user id
     *
     * @param id user id
     * @return list of notification
     */
    public List<Notification> fetchAll(Long id) {
        return notificationRepository.findByUserIdOrderByIdDesc(id);
    }

    /**
     * Find the notification by id.
     *
     * @param id notification id
     * @return notification
     */
    public Notification find(long id) {
        return notificationRepository.findById(id);
    }

    /**
     * remove the notification once it has been visited.
     *
     * @param notification entity
     */
    public void delete(Notification notification) {
        notificationRepository.delete(notification);
    }
}

