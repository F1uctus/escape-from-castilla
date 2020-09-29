package com.nntc.escapefromcastilla.creatures;

import com.crown.maps.Point3D;
import com.nntc.escapefromcastilla.GameState;
import com.nntc.escapefromcastilla.R;
import com.nntc.escapefromcastilla.ui.MapIcons;

public class Wanderer extends Human {
        public Wanderer(String name, GameState gs, Point3D pt) {
        super(
            name,
            gs.getGlobalMap(),
            MapIcons.add(
                R.drawable.player_l,
                R.drawable.player_u,
                R.drawable.player_d,
                4,
                gs.context
            ), pt
        );
    }
}
