package com.ht.neighbourchat.models;

public class User {
    private String uId;
    private String userName;
    private String lastSeen;

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
