package com.nntc.escapefromcastilla.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.crown.maps.Map;
import com.crown.maps.MapIcon;
import com.crown.maps.Point3D;
import com.crown.time.Timeline;
import com.nntc.escapefromcastilla.Actor;
import com.nntc.escapefromcastilla.creatures.Human;

import java.util.Timer;
import java.util.TimerTask;

class MapView extends View {
    Paint p;
    Timer timer = new Timer();

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        p = new Paint();
        p.setColor(Color.GREEN);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                invalidate();
            }
        }, 100, 15);
    }

    protected void onDraw(Canvas canvas) {
        Human player = Actor.get();
        Map map;
        int radius;
        Point3D centerPoint;
        if (player == null) {
            map = Timeline.main.getGameState().getGlobalMap();
            radius = map.xSize / 2;
            int center = radius % 2 == 0 ? radius + 1 : radius;
            centerPoint = new Point3D(center, center, map.zSize - 1);
        } else {
            map = player.getMap();
            radius = player.getFov();
            centerPoint = player.getPt0().withZ(map.zSize - 1);
        }
        MapIcon<?>[][][] objects = map.get3DArea(centerPoint, radius);
        int tileSide = getWidth() / (radius * 2 + 1);
        for (MapIcon<?>[][] layer : objects) {
            for (int areaY = 0; areaY < layer.length; areaY++) {
                for (int areaX = 0; areaX < layer[areaY].length; areaX++) {
                    MapIcon<?> icon = layer[areaY][areaX];
                    if (icon != null) {
                        canvas.drawBitmap(
                            ImageTools.resizeTile((Bitmap) icon.get(), tileSide, tileSide),
                            areaX * tileSide,
                            areaY * tileSide,
                            p
                        );
                    }
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // float evX = event.getX();
        // float evY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}