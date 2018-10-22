package com.soen.empower.fixture;

import com.soen.empower.entity.*;

public class Factory {
    public static Teacher teacher1 = new Teacher((long) 1);
    public static Parent parent1 = new Parent((long) 1);
    public static User user1 = new User((long) 1, "a", "1", "ROLE_TEACHER", teacher1, null);
    public static User user2 = new User((long) 2, "b", "2", "ROLE_PARENT", null, parent1);
    public static Card card1 = new Card((long) 1, "title 1", "text 1", user1);
    public static Card card2 = new Card((long) 2, "title 2", "text 2", user2);
    public static Comment comment1 = new Comment((long) 1, "comment 1", user1, card1);


}
