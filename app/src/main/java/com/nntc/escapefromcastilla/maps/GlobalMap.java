package com.nntc.escapefromcastilla.maps;

import com.crown.common.utils.Random;
import com.crown.i18n.I18n;
import com.crown.i18n.ITemplate;
import com.crown.maps.Map;
import com.crown.maps.Point3D;
import com.nntc.escapefromcastilla.maps.gen.Cell;
import com.nntc.escapefromcastilla.maps.gen.MazeGenerator;
import com.nntc.escapefromcastilla.objects.Floor;
import com.nntc.escapefromcastilla.objects.GoldCoin;
import com.nntc.escapefromcastilla.objects.Wall;
import com.nntc.escapefromcastilla.ui.MapIcons;

import java.util.Timer;
import java.util.TimerTask;

public class GlobalMap extends Map {
    private Timer coinAnimTimer;

    public GlobalMap(String name, int xSize, int ySize, int zSize) {
        super(name, xSize, ySize, zSize);
        Cell[][] gen = MazeGenerator.generate(xSize, ySize);
        int wallsCount = 0;
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < ySize; x++) {
                add(new Floor(this, new Point3D(x, y, MapLevel.ground)));
                if (gen[x][y] == Cell.wall) {
                    add(new Wall(this, new Point3D(
                        x,
                        y,
                        MapLevel.ground + 1
                    )));
                    wallsCount++;
                }
            }
        }
        int coinsCount = (xSize * ySize - wallsCount) / 20;
        for (int i = 0; i < coinsCount; i++) {
            add(new GoldCoin(this, getTrueFreePoint(MapLevel.ground + 1)));
        }
        coinAnimTimer = new Timer();
        coinAnimTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                MapIcons.collection().get("coin").stepAnimation();
            }
        }, 100, 250);
    }

    public Point3D getTrueFreePoint(int zLevel) {
        Point3D pt;
        do {
            pt = Random.getFreePoint(this).withZ(zLevel);
        }
        while (this.get(pt) != null && !this.get(pt).isWalkable());
        return pt;
    }

    @Override
    public GraphicalMapIcon getEmptyIcon() {
        return MapIcons.collection().get("emptiness");
    }

    @Override
    public ITemplate getName() {
        return I18n.of("map.global.name");
    }

    @Override
    public ITemplate getDescription() {
        return I18n.empty;
    }

    @Override
    protected void finalize() {
        coinAnimTimer.cancel();
        coinAnimTimer.purge();
        coinAnimTimer = null;
    }
}
