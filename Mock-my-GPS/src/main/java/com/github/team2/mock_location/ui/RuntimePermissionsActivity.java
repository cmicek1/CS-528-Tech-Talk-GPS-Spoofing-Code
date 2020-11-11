package com.github.team2.mock_location.ui;

import android.app.Activity;

public abstract class RuntimePermissionsActivity extends Activity {
    public abstract void onPermissionsGranted();
    public abstract void onPermissionsDenied(String[] permissions);
}
