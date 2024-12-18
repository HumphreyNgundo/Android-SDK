package com.example.deviceinfosdk;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionManager {
    private static final String[] REQUIRED_PERMISSIONS = {
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_SMS,
            Manifest.permission.READ_CALL_LOG
    };

    public static boolean checkAndRequestPermissions(Activity activity) {
        List<String> deniedPermissions = new ArrayList<>();
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permission);
            }
        }

        if (!deniedPermissions.isEmpty()) {
            ActivityCompat.requestPermissions(
                    activity,
                    deniedPermissions.toArray(new String[0]),
                    1001
            );
            return false;
        }

        return true;
    }
}
