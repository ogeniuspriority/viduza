package com.techhome.pitsan;

import android.support.annotation.NonNull;

public class MessageItem extends MessageListItem {
    @NonNull
    private Message message;

    public MessageItem(Message message) {
        this.message = message;
    }

    @NonNull
    public Message getMessage() {
        return message;
    }

    @Override
    public int getType() {
        return TYPE_MESSAGE;
    }
}
