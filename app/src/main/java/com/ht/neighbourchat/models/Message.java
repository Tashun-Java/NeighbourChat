package com.ht.neighbourchat.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Message {
    @ColumnInfo(name = "user-id")
    private String id;

    @PrimaryKey
    @ColumnInfo(name = "message-id")
    private String messageId;

    @ColumnInfo(name = "message-content")
    private String messageContent;

    @ColumnInfo(name = "sender")
    private String sender;

    @ColumnInfo(name = "status")
    private int status;

    public String getMessageId() {
        return messageId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

