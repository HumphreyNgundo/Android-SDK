package com.example.deviceinfosdk.permissions;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class MessageFetcher {
    public static List<String> fetchMessages(Context context) {
        List<String> messages = new ArrayList<>();
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String body = cursor.getString(cursor.getColumnIndex("body"));
                messages.add(body);
            }
            cursor.close();
        }
        System.out.println("Messages: " + messages); // Debug log
        return messages;
    }

    public static List<String> fetchMessagesForSender(Context context, String senderId) {
        List<String> messages = new ArrayList<>();
        ContentResolver resolver = context.getContentResolver();

        // Query for messages from the specific sender
        Cursor cursor = resolver.query(
                Uri.parse("content://sms/inbox"),
                null,
                "address = ?", // WHERE clause to filter by sender ID
                new String[]{senderId}, // Provide the sender ID as an argument
                null
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range")
                String body = cursor.getString(cursor.getColumnIndex("body"));
                messages.add(body);
            }
            cursor.close();
        }
        System.out.println("Messages for Sender " + senderId + ": " + messages); // Debug log
        return messages;
    }
}

