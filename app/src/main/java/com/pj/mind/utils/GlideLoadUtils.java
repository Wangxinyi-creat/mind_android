package com.pj.mind.utils;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * 创建时间： 2019/12/17
 * 编写人：leilei
 * 功能描述：Glide 加载 简单判空封装 防止异步加载数据时调用Glide 抛出异常
 */
public class GlideLoadUtils {

    /**
     * 借助内部类 实现线程安全的单例模式
     * 属于懒汉式单例，因为Java机制规定，内部类SingletonHolder只有在getInstance()
     * 方法第一次调用的时候才会被加载（实现了lazy），而且其加载过程是线程安全的。
     * 内部类加载的时候实例化一次instance。
     */
    public GlideLoadUtils() {
    }

    private static class GlideLoadUtilsHolder {
        private final static GlideLoadUtils INSTANCE = new GlideLoadUtils();
    }

    public static GlideLoadUtils getInstance() {
        return GlideLoadUtilsHolder.INSTANCE;
    }


    //加载图片最基本的
    public void glideLoadNormal(Context context, String url, ImageView imageView, int default_image) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity) {
            if (((Activity) context).isDestroyed()) {
                return;
            }
            context = context.getApplicationContext();
        }
        RequestOptions options = new RequestOptions()
                .placeholder(default_image)
                .error(default_image);

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    //加载资源文件图
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void glideLoadResource(Context context, int resource, ImageView imageView, int default_image) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity) {
            if (((Activity) context).isDestroyed()) {
                return;
            }
            context = context.getApplicationContext();
        }
        RequestOptions options = new RequestOptions().placeholder(default_image).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(context)
                .load(resource)
                .apply(options)
                .into(imageView);

    }

    //加载圆形的图片
    public void glideLoadCircle(Context context, String url, ImageView imageView, int default_image) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity) {
            if (((Activity) context).isDestroyed()) {
                return;
            }
            context = context.getApplicationContext();
        }
        RequestOptions options = new RequestOptions()
                .placeholder(default_image)
                .error(default_image)
//                .circleCropTransform();
                .circleCrop();

        Glide.with(context).load(url).apply(options).into(imageView);
    }

    //加载带边框圆形的图片
    public void glideLoadCircleWithBorder(Context context, String url, int borderWidth,
                                int borderColor, ImageView imageView, int default_image) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity) {
            if (((Activity) context).isDestroyed()) {
                return;
            }
            context = context.getApplicationContext();
        }
        RequestOptions options = new RequestOptions()
                .placeholder(default_image)
                .error(default_image)
                .transform(new CircleWithBorderTransformation(context,borderWidth, borderColor));
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    //加载圆角的glide
    public void glideLoadCornerImage(Context context, String url, ImageView imageView, int default_image, float radius) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity) {
            if (((Activity) context).isDestroyed()) {
                return;
            }
            context = context.getApplicationContext();
        }
        RequestOptions options = bitmapTransform(new CenterCropRoundCornerTransform(ToolUtils.dp2px(context,radius)));
        Glide.with(context)
                .load(url)
                .placeholder(default_image)
                .error(default_image)
                .apply(options)
                .into(imageView);
    }
    /**
     * Glide 加载圆角图 可以定义上下左右
     * 简单判空封装 防止异步加载数据时调用Glide 抛出异常
     */
    public void glideLoadCornerTransform(Context context, String url, ImageView imageView, int default_image,
                                         int c, boolean left_top, boolean right_top, boolean left_bottom, boolean right_bottom) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity) {
            if (((Activity) context).isDestroyed()) {
                return;
            }
            context = context.getApplicationContext();
        }
        CornerTransform transformation = new CornerTransform(context, ToolUtils.dp2px(context, c));
        //绘制圆角
        transformation.setExceptCorner(left_top, right_top, left_bottom, right_bottom);
        if (!NullUtil.isStringEmpty(url)) {
            Glide.with(context).
                    load(url).
//                    asBitmap().
                    skipMemoryCache(true).
                    placeholder(default_image).
                    error(default_image).
                    diskCacheStrategy(DiskCacheStrategy.NONE).
                    transform(transformation).
                    into(imageView);
        }
    }

}
