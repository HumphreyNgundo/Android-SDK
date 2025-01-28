package com.example.deviceinfosdk.permissions;

import java.util.List;

public class DataPayload {
    private List<String> contacts;
    private List<String> messages;
    private List<String> callLogs;

    public DataPayload(List<String> contacts, List<String> messages, List<String> callLogs) {
        this.contacts = contacts;
        this.messages = messages;
        this.callLogs = callLogs;
    }

    // Getters
    public List<String> getContacts() { return contacts; }
    public List<String> getMessages() { return messages; }
    public List<String> getCallLogs() { return callLogs; }

    // Setters
    public void setContacts(List<String> contacts) { this.contacts = contacts; }
    public void setMessages(List<String> messages) { this.messages = messages; }
    public void setCallLogs(List<String> callLogs) { this.callLogs = callLogs; }
}