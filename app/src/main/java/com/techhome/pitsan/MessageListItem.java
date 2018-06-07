package com.techhome.pitsan;

public abstract class MessageListItem {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_MESSAGE = 1;

    abstract public int getType();
}
