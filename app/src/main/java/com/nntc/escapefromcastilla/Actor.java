package com.nntc.escapefromcastilla;

import com.crown.creatures.Organism;
import com.crown.i18n.I18n;
import com.crown.i18n.ITemplate;
import com.crown.time.Timeline;
import com.nntc.escapefromcastilla.creatures.Human;

public class Actor {
    private static Human player;

    public static Human get() {
        return player;
    }

    public static ITemplate pick(String name) {
        Organism target = Timeline.main.getGameState().players.get(name.toLowerCase());
        if (target == null) {
            return I18n.of("player.notExists");
        }
        Actor.player = (Human) target;
        return I18n.okMessage;
    }

    public static ITemplate free() {
        player = null;
        return I18n.okMessage;
    }

    public static ITemplate kill() {
        getGameState().players.remove(player);
        free();
        return I18n.okMessage;
    }

    public static GameState getGameState() {
        if (player == null) {
            return (GameState) Timeline.main.getGameState();
        }
        return (GameState) player.getTimeline().getGameState();
    }
}
