package com.nntc.escapefromcastilla.objects;

import com.crown.common.interfaces.IDroppable;
import com.crown.items.InventoryItem;
import com.crown.maps.Map;
import com.crown.maps.MapIcon;
import com.crown.maps.MapObject;
import com.crown.maps.MapWeight;
import com.crown.maps.Point3D;
import com.nntc.escapefromcastilla.objects.items.Score;
import com.nntc.escapefromcastilla.ui.MapIcons;

public class GoldCoin extends MapObject implements IDroppable {
    public GoldCoin(Map map, Point3D pt) {
        super("Gold coin", map, MapIcons.collection().get("coin_gold"), MapWeight.OBSTACLE, pt);
    }

    @Override
    public MapIcon<?> getMapIcon() {
        return MapIcons.collection().get(getMapIconId());
    }

    @Override
    public InventoryItem[] drop() {
        return new InventoryItem[]{new Score(10)};
    }
}
