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
    private final Bitmap[] imgsLeft;
    private final Bitmap[] imgsUp;
    private final Bitmap[] imgsDown;
    private int frame = 0;

    public GraphicalMapIcon(String keyName, Context context, int iconId) {
        super(keyName);
        this.imgsLeft = new Bitmap[1];
        this.imgsLeft[0] = BitmapFactory.decodeResource(context.getResources(), iconId);
        imgsUp = null;
        imgsDown = null;
    }

    public GraphicalMapIcon(String keyName, Context context, int iconId, int frames) {
        super(keyName);
        imgsLeft = ImageTools.splitHorizontal(
            BitmapFactory.decodeResource(context.getResources(), iconId),
            frames
        );
        imgsUp = null;
        imgsDown = null;
    }

    public GraphicalMapIcon(
        String keyName,
        Context context,
        int leftIconId,
        int upIconId,
        int downIconId,
        int frames
    ) {
        super(keyName);
        imgsLeft = ImageTools.splitHorizontal(
            BitmapFactory.decodeResource(context.getResources(), leftIconId),
            frames
        );
        imgsUp = ImageTools.splitHorizontal(
            BitmapFactory.decodeResource(context.getResources(), upIconId),
            frames
        );
        imgsDown = ImageTools.splitHorizontal(
            BitmapFactory.decodeResource(context.getResources(), downIconId),
            frames
        );
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
        Bitmap newFrame;
        Direction dir = getDirection();
        if (dir == Direction.e
            || dir == Direction.ne
            || dir == Direction.se) {
            newFrame = ImageTools.flip(imgsLeft[frame], true, false);
        } else if (dir == Direction.w
            || dir == Direction.nw
            || dir == Direction.sw) {
            newFrame = imgsLeft[frame];
        } else if (dir == Direction.n) {
            newFrame = imgsUp[frame];
        } else if (dir == Direction.s) {
            newFrame = imgsDown[frame];
        } else {
            // NOTE: should not happen
            newFrame = imgsLeft[0];
        }

        return newFrame;
    }

    @Override
    public void stepAnimation() {
        frame++;
        if (frame == imgsLeft.length) frame = 0;
    }
}