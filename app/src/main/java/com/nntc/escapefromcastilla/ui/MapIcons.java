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
        instance.add(new GraphicalMapIcon("player_u", context, R.drawable.player_u, 4));
        instance.add(new GraphicalMapIcon("player_d", context, R.drawable.player_d, 4));
        instance.add(new GraphicalMapIcon("player_l", context, R.drawable.player_l, 4));
        instance.add(new GraphicalMapIcon("coin", context, R.drawable.coin, 8));
        instance.add(new GraphicalMapIcon("floor", context, R.drawable.floor));
        instance.add(new GraphicalMapIcon("wall", context, R.drawable.wall));
    }

    public static GraphicalMapIcon add(int resourceId, Context context) {
        GraphicalMapIcon icon = new GraphicalMapIcon(String.valueOf(resourceId), context, resourceId);
        instance.add(icon);
        return icon;
    }

    public static GraphicalMapIcon add(int resourceId, int frames, Context context) {
        GraphicalMapIcon icon = new GraphicalMapIcon(
            String.valueOf(resourceId),
            context,
            resourceId,
            frames
        );
        instance.add(icon);
        return icon;
    }

    public static GraphicalMapIcon add(
        int leftResourceId,
        int upResourceId,
        int downResourceId,
        int frames,
        Context context
    ) {
        GraphicalMapIcon icon = new GraphicalMapIcon(
            String.valueOf(leftResourceId),
            context,
            leftResourceId,
            upResourceId,
            downResourceId,
            frames
        );
        instance.add(icon);
        return icon;
    }
}
