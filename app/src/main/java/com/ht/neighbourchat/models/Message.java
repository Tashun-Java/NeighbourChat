package com.ht.neighbourchat.models;

public class Message {

    private String messageId;
    private String messageContent;
    private String sender;
    private int status;

    public String getMessageId() {
        return messageId;
    }


    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public int getStatus() {
        return status;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSender() {
        return sender;
    }
}

