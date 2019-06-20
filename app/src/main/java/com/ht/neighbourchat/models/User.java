package com.ht.neighbourchat.models;

import java.util.ArrayList;

public class User {
    private String uId;
    private String userName;
    private String lastSeen;
    private ArrayList<Message> messages;

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    public String getuId() {
        return uId;
    }

    public String getUserName() {
        return userName;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
