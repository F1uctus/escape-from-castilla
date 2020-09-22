package com.nntc.escapefromcastilla.ui;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class ImageTools {
    public static Bitmap flip(Bitmap source, boolean flipX, boolean flipY) {
        Matrix matrix = new Matrix();
        matrix.postScale(flipX ? -1 : 1, flipY ? -1 : 1, source.getWidth() / 2f, source.getHeight() / 2f);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }
}
