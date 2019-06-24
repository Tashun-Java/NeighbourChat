package com.ht.neighbourchat.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class UserDoa {

    @PrimaryKey
    @ColumnInfo(name = "user-id")
    @NonNull
    private String id;
    @ColumnInfo(name = "user-name")
    private String userName;

    @ColumnInfo(name = "last-seen")
    private String lastSeen;

//    private ArrayList<Message> messages;

//    public void setMessages(ArrayList<Message> messages) {
//        this.messages = messages;
//    }
//
//    public ArrayList<Message> getMessages() {
//        return messages;
//    }


    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
