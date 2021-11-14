package com.mobileapplicationdev.roboproject.models;

/**
 * MessageTypes for debug messages
 */
@SuppressWarnings("unused")
public enum MessageType {
    ERROR(-1),
    UNDEFINED(0),
    SET_TARGET(1),
    CONNECT(2),
    GET_PID(3),
    SET_PID(4),
    DATA(5),
    SET_VALUE(6);

    private final int messageType;

    MessageType(int messageType) {
        this.messageType = messageType;
    }

    public int getMessageType() {
        return messageType;
    }
}
