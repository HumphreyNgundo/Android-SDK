package com.example.deviceinfosdk;

public class DataPayload {
    private final Object contacts;
    private final Object messages;
    private final Object callLogs;

    public DataPayload(Object contacts, Object messages, Object callLogs) {
        this.contacts = contacts;
        this.messages = messages;
        this.callLogs = callLogs;
    }
}