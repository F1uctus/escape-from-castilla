package com.nntc.escapefromcastilla.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.nntc.escapefromcastilla.Actor;
import com.nntc.escapefromcastilla.R;

public class GameActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
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