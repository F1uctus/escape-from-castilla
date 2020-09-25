package com.nntc.escapefromcastilla.ui;

import android.app.Activity;
import android.os.Bundle;

import com.nntc.escapefromcastilla.Actor;
import com.nntc.escapefromcastilla.R;

import io.github.controlwear.virtual.joystick.android.JoystickView;

public class GameActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ((JoystickView) findViewById(R.id.joystick)).setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
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
        }, 200);
    }
}