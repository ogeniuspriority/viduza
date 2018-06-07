package com.techhome.pitsan;

import android.support.annotation.NonNull;

import java.util.Date;

public class MessageHeaderItem extends MessageListItem {

    @NonNull
    private Date date;

    public MessageHeaderItem(@NonNull Date date) {

        this.date = date;
    }

    @NonNull
    public Date getDate() {
        return date;
    }

    @Override
    public int getType() {
        return TYPE_HEADER;
    }
}
