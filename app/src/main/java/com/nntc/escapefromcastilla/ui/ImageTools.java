package com.nntc.escapefromcastilla.ui;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import java.util.HashMap;
import java.util.Objects;

public class ImageTools {
    public static Bitmap flip(Bitmap source, boolean flipX, boolean flipY) {
        Matrix matrix = new Matrix();
        matrix.postScale(flipX ? -1 : 1, flipY ? -1 : 1, source.getWidth() / 2f, source.getHeight() / 2f);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    private static class ResizeArgs {
        Bitmap image;
        int w;
        int h;

        public ResizeArgs(Bitmap image, int w, int h) {
            this.image = image;
            this.w = w;
            this.h = h;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ResizeArgs that = (ResizeArgs) o;
            return w == that.w && h == that.h && image.equals(that.image);
        }

        @Override
        public int hashCode() {
            return Objects.hash(image, w, h);
        }
    }

    private static final HashMap<ResizeArgs, Bitmap> resizeTileCache = new HashMap<>();

    public static Bitmap resizeTile(Bitmap tile, int w, int h) {
        ResizeArgs args = new ResizeArgs(tile, w, h);
        Bitmap cached = resizeTileCache.getOrDefault(args, null);
        if (cached != null) {
            return cached;
        }
        Bitmap generated = resize(tile, w, h);
        resizeTileCache.put(args, generated);
        return generated;
    }

    private static Bitmap resize(Bitmap img, int width, int height) {
        return Bitmap.createScaledBitmap(
            img,
            width,
            height,
            false
        );
    }
}
