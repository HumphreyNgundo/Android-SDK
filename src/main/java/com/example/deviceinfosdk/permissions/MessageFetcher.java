package com.example.deviceinfosdk.permissions;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class MessageFetcher {
    public static List<String> fetchMessages(Context context) {
        List<String> messages = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(
                Uri.parse("content://sms"),
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String body = cursor.getString(cursor.getColumnIndex("body"));
                messages.add(body);
            }
            cursor.close();
        }

        return messages;
    }
}
