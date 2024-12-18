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

}
