package com.soen.empower.fixture;

import com.soen.empower.entity.*;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.List;

public class Factory {
    public static Teacher teacher1 = new Teacher((long) 1);
    public static Parent parent1 = new Parent((long) 1);
    public static User user1 = new User((long) 1, "a", "1", "ROLE_TEACHER", teacher1, null);
    public static User user2 = new User((long) 2, "b", "2", "ROLE_PARENT", null, parent1);
    public static Card card1 = new Card((long) 1, "title 1", "text 1", user1, user1);
    public static Card card2 = new Card((long) 2, "title 2", "text 2", user2, user1);

    public static Like like1 = new Like(1L, card1, user2);
    public static Like like2 = new Like(2L, card2, user2);

    public static Comment comment1 = new Comment((long) 1, "comment 1", user1, card1);

    public static Conversation conversation1 = new Conversation((long) 1, user1, user2, (long) 2);
    public static Message message1 = new Message((long) 1, "new message", user2, user1, conversation1);
    public static Message message2 = new Message((long) 2, "new message 2", user1, user2, conversation1);
    public static Friend friend1 = new Friend(1L, user1, user2, 1);
    public static Dislike dislike1;
}
