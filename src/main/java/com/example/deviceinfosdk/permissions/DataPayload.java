package com.example.deviceinfosdk.permissions;

import java.util.List;
import java.util.Map;

public class DataPayload {
    private List<String> contacts;
    private Map<String, List<String>> messagesBySender; // Changed to Map
    private List<String> callLogs;

    public DataPayload(List<String> contacts, Map<String, List<String>> messagesBySender, List<String> callLogs) {
        this.contacts = contacts;
        this.messagesBySender = messagesBySender;
        this.callLogs = callLogs;
    }
}
