package com.example.chatrooms.model;

public class ChatBubble {
    private boolean isSender;
    private boolean isPrivate;
    private String msg;
    private String time;
    private String avatar;
    private String name;

    public ChatBubble(String msg, String time, String avatar, String name) {
        this.msg = msg;
        this.time = time;
        this.avatar = avatar;
        this.name = name;
        isSender = false;
        isPrivate = false;
    }

    public ChatBubble(boolean isSender, boolean isPrivate, String msg, String time) {
        this.isSender = isSender;
        this.isPrivate = isPrivate;
        this.msg = msg;
        this.time = time;
    }

    public boolean isSender() {
        return isSender;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public String getMsg() {
        return msg;
    }

    public String getTime() {
        return time;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }
}
