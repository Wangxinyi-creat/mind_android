package com.pj.mind.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

public class CircleWithBorderTransformation extends BitmapTransformation {

    private int borderWidth;
    private int borderColor;
    private Context mContext;

    public CircleWithBorderTransformation(Context mContext, int borderWidth, int borderColor) {
        super();
        this.mContext = mContext;
        this.borderWidth = borderWidth;
        this.borderColor = borderColor;
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap inBitmap, int outWidth, int outHeight) {
        if (inBitmap == null) return null;

        int size = Math.min(inBitmap.getWidth(), inBitmap.getHeight());
        int x = (inBitmap.getWidth() - size) / 2;
        int y = (inBitmap.getHeight() - size) / 2;

        Bitmap roundedBitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(roundedBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        canvas.drawCircle(size / 2, size / 2, size / 2, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(inBitmap, x, y, paint);

        // Add border
        paint.setXfermode(null);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(borderColor);
        paint.setStrokeWidth(ToolUtils.dp2px(mContext,borderWidth));
        float radius = size / 2;
        canvas.drawCircle(size / 2, size / 2, radius - ToolUtils.dp2px(mContext,borderWidth) / 2, paint);
        return roundedBitmap;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        // Intentionally empty to avoid the disk cache key being invalidated
    }
}

