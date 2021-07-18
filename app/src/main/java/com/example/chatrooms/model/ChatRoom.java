package com.example.chatrooms.model;

public class ChatRoom {
    private String name;
    private String lastMsg;
    private String time;
    private String avatar;
    private int numOfUnreadMsgs;
    private ChatRoomType type;

    public ChatRoom(String name, String lastMsg, String time, String avatar, int numOfUnreadMsgs, ChatRoomType type) {
        this.name = name;
        this.lastMsg = lastMsg;
        this.time = time;
        this.avatar = avatar;
        this.numOfUnreadMsgs = numOfUnreadMsgs;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public String getTime() {
        return time;
    }

    public String getAvatar() {
        return avatar;
    }

    public int getNumOfUnreadMsgs() {
        return numOfUnreadMsgs;
    }

    public ChatRoomType getType() {
        return type;
    }
}
