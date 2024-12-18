package com.example.deviceinfosdk;

import okhttp3.*;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;

public class DataLogger {
    private static final String SERVER_URL = "https://10.10.0.100:8083/nic_sasa_api/api/log";


    public static void logData(String jsonData) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(
                jsonData,
                MediaType.get("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(SERVER_URL)
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
