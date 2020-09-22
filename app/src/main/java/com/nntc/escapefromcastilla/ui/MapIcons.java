package com.nntc.escapefromcastilla.ui;

import android.content.Context;

import com.crown.common.ObjectsMap;
import com.nntc.escapefromcastilla.R;
import com.nntc.escapefromcastilla.maps.GraphicalMapIcon;

public class MapIcons extends ObjectsMap<GraphicalMapIcon> {
    private static MapIcons instance;

    public static MapIcons collection() {
        return instance;
    }

    static void init(Context context) {
        if (instance != null) return;
        instance = new MapIcons();
        instance.add(new GraphicalMapIcon("coin_gold", context, R.drawable.coin_gold));
        instance.add(new GraphicalMapIcon("coin_green", context, R.drawable.coin_green));
        instance.add(new GraphicalMapIcon("floor", context, R.drawable.floor));
        instance.add(new GraphicalMapIcon("wall", context, R.drawable.wall));
    }

    public static GraphicalMapIcon add(int resourceId, Context context) {
        GraphicalMapIcon icon = new GraphicalMapIcon(String.valueOf(resourceId), context, resourceId);
        instance.add(icon);
        return icon;
    }
}
