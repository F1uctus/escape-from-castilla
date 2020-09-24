package com.nntc.escapefromcastilla.creatures;

import com.crown.maps.*;
import com.nntc.escapefromcastilla.GameState;
import com.nntc.escapefromcastilla.R;
import com.nntc.escapefromcastilla.ui.MapIcons;

public class Wanderer extends Human {
    public Wanderer(String name, GameState gs, Point3D pt) {
        super(name, gs.getGlobalMap(), MapIcons.add(R.drawable.coin_gold, gs.context), pt);
    }
}
