package com.pj.mind.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class GlideUtils {

    public static void loadRoundImage(Context context, ImageView img, String url, float r) {

        RoundedCorners roundedCorners = new RoundedCorners((int) ToolUtils.dp2px(context, r));

        //关键点
        MultiTransformation<Bitmap> multiTransformation = new MultiTransformation<>(new CenterCrop(), roundedCorners);
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .bitmapTransform(multiTransformation);
        //图片加载自己实现
        Glide.with(img)
                .load(url)
                .apply(options)
                .into(img);

    }
}
