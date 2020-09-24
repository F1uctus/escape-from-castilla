package com.nntc.escapefromcastilla.objects;

import com.crown.maps.Map;
import com.crown.maps.MapIcon;
import com.crown.maps.MapObject;
import com.crown.maps.MapWeight;
import com.crown.maps.Point3D;
import com.nntc.escapefromcastilla.ui.MapIcons;

public class Floor extends MapObject {
    public Floor(Map map, Point3D pt) {
        super("Floor", map, MapIcons.collection().get("floor"), MapWeight.OBSTACLE, pt);
        setWalkable(false);
    }

    @Override
    public MapIcon<?> getMapIcon() {
        return MapIcons.collection().get(getMapIconId());
    }
}
