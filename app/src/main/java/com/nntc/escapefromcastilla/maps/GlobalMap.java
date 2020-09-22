package com.nntc.escapefromcastilla.maps;

import com.crown.i18n.I18n;
import com.crown.i18n.ITemplate;
import com.crown.maps.Map;
import com.crown.maps.Point3D;
import com.nntc.escapefromcastilla.ui.MapIcons;
import com.nntc.escapefromcastilla.objects.Floor;
import com.nntc.escapefromcastilla.objects.Wall;

public class GlobalMap extends Map {
    public GlobalMap(String name, int xSize, int ySize, int zSize) {
        super(name, xSize, ySize, zSize);
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < ySize; x++) {
                add(new Floor(this, new Point3D(x, y, MapLevel.ground)));
            }
        }
        for (int i = 0; i < xSize / 2; i++) {
            add(new Wall(this));
        }
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
}
