package com.example.deviceinfosdk.permissions;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageFetcher {
    @SuppressLint("Range")
    public static List<Map<String, String>> fetchMessages(Context context) {
        List<Map<String, String>> messages = new ArrayList<>();
        ContentResolver resolver = context.getContentResolver();

        // Define the columns we want to retrieve
        String[] projection = new String[] {
                Telephony.Sms._ID,
                Telephony.Sms.ADDRESS,  // sender/recipient number
                Telephony.Sms.BODY,     // message content
                Telephony.Sms.DATE,     // date received
                Telephony.Sms.TYPE      // message type (1 = incoming, 2 = outgoing)
        };

        Cursor cursor = resolver.query(
                Uri.parse("content://sms"),
                projection,
                null,
                null,
                Telephony.Sms.DEFAULT_SORT_ORDER
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Map<String, String> messageDetails = new HashMap<>();

                // Get message details
                String address = cursor.getString(cursor.getColumnIndex(Telephony.Sms.ADDRESS));
                String body = cursor.getString(cursor.getColumnIndex(Telephony.Sms.BODY));
                long dateMillis = cursor.getLong(cursor.getColumnIndex(Telephony.Sms.DATE));
                int type = cursor.getInt(cursor.getColumnIndex(Telephony.Sms.TYPE));

                // Format the data
                messageDetails.put("address", address);
                messageDetails.put("body", body);
                messageDetails.put("date", String.valueOf(dateMillis));
                messageDetails.put("type", type == 1 ? "incoming" : "outgoing");

                messages.add(messageDetails);
            }
            cursor.close();
        }

        System.out.println("Messages with details: " + messages); // Debug log
        return messages;
    }

    @SuppressLint("Range")
    public static List<Map<String, String>> fetchMessagesForSender(Context context, String senderId) {
        List<Map<String, String>> messages = new ArrayList<>();
        ContentResolver resolver = context.getContentResolver();

        String[] projection = new String[] {
                Telephony.Sms._ID,
                Telephony.Sms.ADDRESS,
                Telephony.Sms.BODY,
                Telephony.Sms.DATE,
                Telephony.Sms.TYPE
        };

        // Query for messages from the specific sender
        Cursor cursor = resolver.query(
                Uri.parse("content://sms"),
                projection,
                Telephony.Sms.ADDRESS + " = ?",
                new String[]{senderId},
                Telephony.Sms.DEFAULT_SORT_ORDER
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Map<String, String> messageDetails = new HashMap<>();

                String address = cursor.getString(cursor.getColumnIndex(Telephony.Sms.ADDRESS));
                String body = cursor.getString(cursor.getColumnIndex(Telephony.Sms.BODY));
                long dateMillis = cursor.getLong(cursor.getColumnIndex(Telephony.Sms.DATE));
                int type = cursor.getInt(cursor.getColumnIndex(Telephony.Sms.TYPE));

                messageDetails.put("address", address);
                messageDetails.put("body", body);
                messageDetails.put("date", String.valueOf(dateMillis));
                messageDetails.put("type", type == 1 ? "incoming" : "outgoing");

                messages.add(messageDetails);
            }
            cursor.close();
        }

        System.out.println("Messages for Sender " + senderId + ": " + messages); // Debug log
        return messages;
    }
}