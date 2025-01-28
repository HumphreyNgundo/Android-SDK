package com.example.deviceinfosdk.permissions;

import static android.content.ContentValues.TAG;

import com.example.deviceinfosdk.Services.Encryptor;
import okhttp3.*;
import org.json.JSONObject;
import java.io.IOException;
import android.util.Log;

public class DataLogger {
    private static String serverUrl;

    public static void setServerUrl(String url) {
        serverUrl = url;
    }

    public static void logData(String jsonData) {
        if (serverUrl == null || serverUrl.isEmpty()) {
            throw new IllegalStateException("Server URL not set. Call setServerUrl() first");
        }

        try {
            // Initialize encryption if not already done
            Encryptor.init();

            // Encrypt the data
            String encryptedData = Encryptor.encrypt(jsonData);

            Log.d(TAG, "Encrypted data: " + encryptedData);
            Log.d(TAG, "Encryption key ID: " + Encryptor.getEncodedKey());





            // Create payload with encrypted data and key identifier
            JSONObject payload = new JSONObject();
            payload.put("data", encryptedData);
            payload.put("keyId", Encryptor.getEncodedKey()); // This helps server identify which key to use

            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(
                    payload.toString(),
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
                        System.out.println("Encrypted data logged successfully");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to encrypt and send data", e);
        }
    }
}