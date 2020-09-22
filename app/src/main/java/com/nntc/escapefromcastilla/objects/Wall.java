package com.nntc.escapefromcastilla.objects;

import com.crown.maps.Map;
import com.crown.maps.MapIcon;
import com.crown.maps.MapObject;
import com.crown.maps.MapWeight;
import com.nntc.escapefromcastilla.ui.MapIcons;

public class Wall extends MapObject {
    public Wall(Map map) {
        super("Wall", map, MapIcons.collection().get("wall"), MapWeight.OBSTACLE);
    }

    @Override
    public MapIcon<?> getMapIcon() {
        return MapIcons.collection().get(getMapIconId());
    }
}
