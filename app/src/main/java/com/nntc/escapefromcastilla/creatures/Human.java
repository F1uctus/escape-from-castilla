package com.nntc.escapefromcastilla.creatures;

import com.crown.common.interfaces.IDroppable;
import com.crown.creatures.Organism;
import com.crown.i18n.I18n;
import com.crown.i18n.ITemplate;
import com.crown.items.InventoryItem;
import com.crown.maps.Map;
import com.crown.maps.MapIcon;
import com.crown.maps.MapObject;
import com.crown.maps.Point3D;
import com.crown.time.Action;
import com.crown.time.Timeline;
import com.nntc.escapefromcastilla.objects.GoldCoin;
import com.nntc.escapefromcastilla.ui.MapIcons;

public class Human extends Organism {
    public String lang = "en";

    protected final int maxFov = 20;
    protected int fov;

    public Human(
        String name,
        Map map,
        MapIcon<?> mapIcon,
        Point3D position
    ) {
        super(
            name,
            map,
            mapIcon,
            position,
            100,
            100,
            1,
            1
        );
        xp = 100;
        fov = 5;
        setWalkable(false);
    }

    public ITemplate getStats() {
        return I18n.of(getKeyName() + "\n"
            + I18n.fmtOf("stats.hp") + ": " + getHp() + "/" + getMaxHp() + "\n"
            + I18n.fmtOf("stats.energy") + ": " + getEnergy() + "/" + getMaxEnergy() + "\n"
            + I18n.fmtOf("stats.speed") + ": " + getSpeed() + "/" + getMaxSpeed() + "\n"
            + I18n.fmtOf("stats.fov") + ": " + getFov() + "\n"
            + I18n.fmtOf("stats.level") + ": " + getLevel() + "\n"
            + I18n.fmtOf("stats.xp") + ": " + getXp() + "\n"
            + I18n.fmtOf("stats.xp.toNextLevel") + ": " + getXpForLevel(level + 1) + "\n"
            + I18n.fmtOf("stats.skillPoints") + ": " + getSkillPoints() + "\n"
            + getPt0()
        );
    }

    // region FOV

    /**
     * Maximal allowed field of vision for creature.
     */
    public int getMaxFov() {
        return maxFov;
    }

    /**
     * Returns creature's field of vision.
     */
    public int getFov() {
        return fov;
    }

    /**
     * Changes creature's field of vision by {@code delta}.
     * Timeline support included.
     */
    public ITemplate changeFovBy(int delta) {
        return getTimeline().perform(Action.change(this, "changeFov", delta));
    }

    /**
     * Internal logic, may be overridden if needed.
     */
    public ITemplate changeFov(int delta) {
        if (invalidDelta(fov, delta, maxFov)) {
            return I18n.invalidDeltaMessage;
        }
        fov += delta;
        return I18n.okMessage;
    }

    // endregion

    @Override
    public ITemplate moveBy(final int deltaX, final int deltaY, final int deltaZ) {
        return Timeline.main.perform(new Action<Organism>(this) {
            @Override
            public ITemplate perform() {
                getMapIcon().stepAnimation();
                Point3D targetPoint = getPt0().plus(getMapIcon().getDirection().point);
                MapObject targetObj = getMap().get(targetPoint);
                if (targetObj instanceof IDroppable) {
                    getMap().remove(targetObj);
                    InventoryItem[] items = ((IDroppable) targetObj).drop();
                    for (InventoryItem item : items) {
                        getInventory().add(item);
                    }
                }
                return getTarget().move(deltaX, deltaY, deltaZ);
            }

            @Override
            public ITemplate rollback() {
                return getTarget().move(-deltaX, -deltaY, -deltaZ);
            }
        });
    }

    @Override
    public MapIcon<?> getMapIcon() {
        return MapIcons.collection().get(getMapIconId());
    }
}
