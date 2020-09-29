package com.nntc.escapefromcastilla.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.crown.i18n.I18n;
import com.nntc.escapefromcastilla.R;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainActivity extends AppCompatActivity {
    public static final HashMap<String, ResourceBundle> bundles = new HashMap<>();
    public static final String locale = Locale.getDefault().getLanguage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_main);

        Log.d("Icons", "Initializing...");
        MapIcons.init(this);
        Log.d("Icons", "OK");

        Log.d("I18n", "Initializing...");
        bundles.put("ru", new Resources.Russian());
        bundles.put("en", new Resources.English());
        I18n.init(bundles);
        Log.d("I18n", "OK");
    }

    public void onPlayClick(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void onSettingsClick(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}