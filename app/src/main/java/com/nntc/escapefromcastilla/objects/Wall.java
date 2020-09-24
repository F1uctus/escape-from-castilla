package com.nntc.escapefromcastilla.objects;

import com.crown.common.utils.Random;
import com.crown.maps.Map;
import com.crown.maps.MapIcon;
import com.crown.maps.MapObject;
import com.crown.maps.Point3D;
import com.nntc.escapefromcastilla.maps.MapLevel;
import com.nntc.escapefromcastilla.ui.MapIcons;

public class Wall extends MapObject {
    public Wall(Map map, Point3D pt) {
        super("Wall", map, MapIcons.collection().get("wall"), pt);
        setWalkable(false);
    }

    @Override
    public MapIcon<?> getMapIcon() {
        return MapIcons.collection().get(getMapIconId());
    }
}
