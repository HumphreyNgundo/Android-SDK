package com.example.deviceinfosdk.permissions;

import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;

import java.util.ArrayList;
import java.util.List;

public class CallLogFetcher {
    public static List<String> fetchCallLogs(Context context) {
        List<String> callLogs = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(
                CallLog.Calls.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                callLogs.add(number);
            }
            cursor.close();
        }

        return callLogs;
    }
}
