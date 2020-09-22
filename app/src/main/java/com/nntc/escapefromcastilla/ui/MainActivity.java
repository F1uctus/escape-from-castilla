package com.nntc.escapefromcastilla.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.crown.BaseGameState;
import com.crown.i18n.I18n;
import com.crown.time.Timeline;
import com.crown.time.VirtualClock;
import com.nntc.escapefromcastilla.GameState;
import com.nntc.escapefromcastilla.R;
import com.nntc.escapefromcastilla.maps.GlobalMap;
import com.nntc.escapefromcastilla.maps.MapLevel;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainActivity extends AppCompatActivity {
    public static final HashMap<String, ResourceBundle> bundles = new HashMap<>();
    public static final int mapSize = 10;

    BaseGameState gameState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Icons", "Initializing...");
        MapIcons.init(this);
        Log.d("Icons", "OK");

        Log.d("I18n", "Initializing...");
        bundles.put("ru", new Resources.Russian());
        bundles.put("en", new Resources.English());
        I18n.init(bundles);
        Log.d("I18n", "OK");

        Log.d("Game state", "Initializing...");
        gameState = new GameState(
            this,
            new GlobalMap("Global map", mapSize, mapSize, MapLevel.height)
        );
        Log.d("Game state", "OK");

        Log.d("Main timeline", "Initializing...");
        Timeline.init(
            new VirtualClock(1000, new Runnable() {
                @Override
                public void run() {
                }
            }).startAtRnd(),
            gameState
        );
        Log.d("Main timeline", "OK");
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