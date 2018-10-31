package com.soen.empower.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * The Entity Class Message shows the attributes of message functionality.
 * 
 * @version 1.0
 * @since 1.0
 */
@Entity
public class Message {

    /** The id. */
    @Id
    @GeneratedValue
    private Long id;
    
    /** The text. */
    private String text;
    
    /** The created. */
    private Date created = new Date();

    /** The to. */
    @ManyToOne
    @JoinColumn(name = "to_id")
    private User to;

    /** The from. */
    @ManyToOne
    @JoinColumn(name = "from_id")
    private User from;

    /** The conversation. */
    @ManyToOne
    @JoinColumn(name="conversation_id")
    private Conversation conversation;

    public Message(){

    }

    public Message(Long id, String text, User to, User from, Conversation conversation) {
        this.id = id;
        this.text = text;
        this.to = to;
        this.from = from;
        this.conversation = conversation;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text.
     *
     * @param text the new text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets the to.
     *
     * @return the to
     */
    public User getTo() {
        return to;
    }

    /**
     * Sets the to.
     *
     * @param to the new to
     */
    public void setTo(User to) {
        this.to = to;
    }

    /**
     * Gets the from.
     *
     * @return the from
     */
    public User getFrom() {
        return from;
    }

    /**
     * Sets the from.
     *
     * @param from the new from
     */
    public void setFrom(User from) {
        this.from = from;
    }

    /**
     * Gets the created.
     *
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Sets the created.
     *
     * @param created the new created
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * Gets the conversation.
     *
     * @return the conversation
     */
    public Conversation getConversation() {
        return conversation;
    }

    /**
     * Sets the conversation.
     *
     * @param conversation the new conversation
     */
    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }
}
