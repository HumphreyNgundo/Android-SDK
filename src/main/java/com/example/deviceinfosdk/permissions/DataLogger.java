package com.example.deviceinfosdk.permissions;

import okhttp3.*;
import java.io.IOException;

public class DataLogger {
    private static String serverUrl; // Remove final, make it changeable

    // Add a method to set the URL
    public static void setServerUrl(String url) {
        serverUrl = url;
    }

    public static void logData(String jsonData) {
        if (serverUrl == null || serverUrl.isEmpty()) {
            throw new IllegalStateException("Server URL not set. Call setServerUrl() first");
        }

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(
                jsonData,
                MediaType.get("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(serverUrl)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    System.out.println("Data logged successfully");
                }
            }
        });
    }
}