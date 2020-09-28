package com.nntc.escapefromcastilla.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.preference.PreferenceManager;

import com.crown.time.Timeline;
import com.crown.time.VirtualClock;
import com.nntc.escapefromcastilla.Actor;
import com.nntc.escapefromcastilla.GameState;
import com.nntc.escapefromcastilla.R;
import com.nntc.escapefromcastilla.maps.GlobalMap;
import com.nntc.escapefromcastilla.maps.MapLevel;

import io.github.controlwear.virtual.joystick.android.JoystickView;

public class GameActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] controllers = getResources().getStringArray(R.array.moveControls);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String moveController = sharedPref.getString("moveController", controllers[0]);
        if (moveController.equals(controllers[0])) {
            setContentView(R.layout.activity_game_joystick);

            ((JoystickView) findViewById(R.id.joystick)).setOnMoveListener((angle, strength) -> {
                if (strength > 40) {
                    if (angle <= 225 && angle >= 135) {
                        // left
                        Actor.get().moveBy(-1, 0);
                    } else if (angle < 135 && angle >= 45) {
                        // up
                        Actor.get().moveBy(0, -1);
                    } else if (angle <= 315 && angle > 225) {
                        // down
                        Actor.get().moveBy(0, 1);
                    } else {
                        // right
                        Actor.get().moveBy(1, 0);
                    }
                }
            }, 150);
        } else if (moveController.equals(controllers[1])) {
            setContentView(R.layout.activity_game_arrows);
        }

        Log.d("Global map", "Initializing...");
        int mapSize = getMapSize();
        GlobalMap map = new GlobalMap("Global map", mapSize, mapSize, MapLevel.height);
        Log.d("Global map", "OK");

        Log.d("Game state", "Initializing...");
        GameState gameState = new GameState(this, map);
        Log.d("Game state", "OK");

        Log.d("Main timeline", "Initializing...");
        Timeline.init(
            new VirtualClock(1000, () -> {
            }).startAtRnd(),
            gameState
        );
        Log.d("Main timeline", "OK");

        gameState.addPlayer("Runner");
        Actor.pick("Runner");
    }

    public int getMapSize() {
        SharedPreferences prefs =
            PreferenceManager.getDefaultSharedPreferences(this);
        String value = prefs.getString("mapSize", null);
        return value == null ? -1 : Integer.parseInt(value);
    }

    public void onUpClick(View view) {
        Actor.get().moveBy(0, -1);
    }

    public void onLeftClick(View view) {
        Actor.get().moveBy(-1, 0);
    }

    public void onRightClick(View view) {
        Actor.get().moveBy(1, 0);
    }

    public void onDownClick(View view) {
        Actor.get().moveBy(0, 1);
    }
}