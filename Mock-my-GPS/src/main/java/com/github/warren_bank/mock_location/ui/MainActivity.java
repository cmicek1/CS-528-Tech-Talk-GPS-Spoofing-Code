package com.github.warren_bank.mock_location.ui;

import com.github.warren_bank.mock_location.R;
import com.github.warren_bank.mock_location.data_model.BookmarkItem;
import com.github.warren_bank.mock_location.data_model.LocPoint;
import com.github.warren_bank.mock_location.data_model.SharedPrefs;
import com.github.warren_bank.mock_location.service.LocationService;

import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends ActivityGroup {
    private TabHost tabHost;

    // ---------------------------------------------------------------------------------------------
    // Lifecycle Events:
    // ---------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(getLocalActivityManager());

        String tab_1_tag = getString(R.string.MainActivity_tab_1_tag);

        String tab_1_label = getString(R.string.MainActivity_tab_1_label);

        tabHost.addTab(tabHost.newTabSpec(tab_1_tag).setIndicator(tab_1_label).setContent(new Intent(this, FixedPositionActivity.class)));

        final Intent intent = getIntent();
        if (intent == null)
            return;

        String currentTabTag = getCurrentTabTag(intent);
        if (currentTabTag != null) {
            tabHost.setCurrentTabByTag(currentTabTag);
        }

        showToast(intent);
    }

    private String getCurrentTabTag(Intent intent) {
        return intent.getStringExtra(getString(R.string.MainActivity_extra_current_tab_tag));
    }

    private void showToast(Intent intent) {
        String text = intent.getStringExtra(getString(R.string.MainActivity_extra_toast));
        if (text == null)
            return;

        Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        boolean is_started = LocationService.isStarted();

        if (is_started && !isFinishing())
            finish();
    }
}
