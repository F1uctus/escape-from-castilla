package com.nntc.escapefromcastilla;

import android.content.Context;

import com.crown.BaseGameState;
import com.crown.i18n.I18n;
import com.crown.i18n.ITemplate;
import com.crown.maps.Point3D;
import com.nntc.escapefromcastilla.creatures.Wanderer;
import com.nntc.escapefromcastilla.maps.GlobalMap;
import com.nntc.escapefromcastilla.maps.MapLevel;

/**
 * Contains game state for current running session.
 */
public class GameState extends BaseGameState {
    public final Context context;

    public GameState(Context context, GlobalMap globalMap) {
        super(globalMap);
        this.context = context;
    }

    public ITemplate addPlayer(String name) {
        if (players.get(name) != null) {
            return I18n.of("player.nameReserved");
        }

        Point3D pt = ((GlobalMap) getGlobalMap()).getTrueFreePoint(MapLevel.ground + 1);
        players.add(new Wanderer(
            name,
            this,
            pt
        ));
        return I18n.okMessage;
    }


}
