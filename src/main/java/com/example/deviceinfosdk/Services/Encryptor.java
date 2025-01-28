package com.example.deviceinfosdk.Services;

import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;

public class Encryptor {
    private static final int GCM_IV_LENGTH = 12;
    private static final int GCM_TAG_LENGTH = 128;
    private static SecretKey secretKey;

    // Initialize encryption key
    public static void init() throws Exception {
        if (secretKey == null) {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            secretKey = keyGen.generateKey();
        }
    }

    // Encrypt data
    public static String encrypt(String data) throws Exception {
        if (secretKey == null) {
            throw new IllegalStateException("Encryption not initialized. Call init() first.");
        }

        // Generate random IV
        byte[] iv = new byte[GCM_IV_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        // Create cipher instance
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec);

        // Encrypt
        byte[] encryptedData = cipher.doFinal(data.getBytes());

        // Combine IV and encrypted data
        byte[] combined = new byte[iv.length + encryptedData.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(encryptedData, 0, combined, iv.length, encryptedData.length);

        // Encode as base64 string using Android's Base64
        return android.util.Base64.encodeToString(combined, Base64.NO_WRAP);
    }

    // For testing/verification purposes
    public static String decrypt(String encryptedData) throws Exception {
        byte[] decoded = android.util.Base64.decode(encryptedData, Base64.NO_WRAP);

        // Extract IV
        byte[] iv = new byte[GCM_IV_LENGTH];
        System.arraycopy(decoded, 0, iv, 0, GCM_IV_LENGTH);

        // Extract encrypted data
        byte[] encrypted = new byte[decoded.length - GCM_IV_LENGTH];
        System.arraycopy(decoded, GCM_IV_LENGTH, encrypted, 0, encrypted.length);

        // Decrypt
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, parameterSpec);

        byte[] decrypted = cipher.doFinal(encrypted);
        return new String(decrypted);
    }

    // Get the current encryption key (Base64 encoded)
    public static String getEncodedKey() {
        if (secretKey == null) {
            throw new IllegalStateException("Encryption not initialized. Call init() first.");
        }
        return android.util.Base64.encodeToString(secretKey.getEncoded(), Base64.NO_WRAP);
    }
}