package com.nntc.escapefromcastilla.maps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.crown.i18n.I18n;
import com.crown.i18n.ITemplate;
import com.crown.maps.Direction;
import com.crown.maps.MapIcon;
import com.nntc.escapefromcastilla.ui.ImageTools;

public class GraphicalMapIcon extends MapIcon<Bitmap> {
    private final Bitmap img;

    public GraphicalMapIcon(String keyName, Context context, int iconId) {
        super(keyName);
        this.img = BitmapFactory.decodeResource(context.getResources(), iconId);
    }

    @Override
    public ITemplate getName() {
        return I18n.empty;
    }

    @Override
    public ITemplate getDescription() {
        return I18n.empty;
    }

    public Bitmap get() {
        Direction dir = getDirection();
        if (dir == Direction.e
            || dir == Direction.ne
            || dir == Direction.se) {
            return ImageTools.flip(img, true, false);
        }
        return img;
    }

    @Override
    public void stepAnimation() {

    }
}