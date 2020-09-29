package com.nntc.escapefromcastilla.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.preference.PreferenceManager;

import com.crown.i18n.I18n;
import com.crown.i18n.ITemplate;
import com.crown.time.Timeline;
import com.crown.time.VirtualClock;
import com.nntc.escapefromcastilla.Actor;
import com.nntc.escapefromcastilla.GameState;
import com.nntc.escapefromcastilla.R;
import com.nntc.escapefromcastilla.maps.GlobalMap;
import com.nntc.escapefromcastilla.maps.MapLevel;

import io.github.controlwear.virtual.joystick.android.JoystickView;

import static com.nntc.escapefromcastilla.ui.MainActivity.locale;

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
                    ITemplate result;
                    if (angle <= 225 && angle >= 135) {
                        // left
                        result = Actor.get().moveBy(-1, 0);
                    } else if (angle < 135 && angle >= 45) {
                        // up
                        result = Actor.get().moveBy(0, -1);
                    } else if (angle <= 315 && angle > 225) {
                        // down
                        result = Actor.get().moveBy(0, 1);
                    } else {
                        // right
                        result = Actor.get().moveBy(1, 0);
                    }
                    setStatusMessage(result);
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

    public void setStatusMessage(ITemplate message) {
        if (message == I18n.okMessage) message = I18n.empty;
        ((TextView) findViewById(R.id.status)).setText(message.getLocalized(locale));
    }

    public int getMapSize() {
        SharedPreferences prefs =
            PreferenceManager.getDefaultSharedPreferences(this);
        String value = prefs.getString("mapSize", null);
        return value == null ? -1 : Integer.parseInt(value);
    }

    public void onUpClick(View view) {
        ITemplate result = Actor.get().moveBy(0, -1);
        setStatusMessage(result);
    }

    public void onLeftClick(View view) {
        ITemplate result = Actor.get().moveBy(-1, 0);
        setStatusMessage(result);
    }

    public void onRightClick(View view) {
        ITemplate result = Actor.get().moveBy(1, 0);
        setStatusMessage(result);
    }

    public void onDownClick(View view) {
        ITemplate result = Actor.get().moveBy(0, 1);
        setStatusMessage(result);
    }
}