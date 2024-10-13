package com.pj.mind.other;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.hjq.bar.style.LightBarStyle;
import com.pj.mind.R;


/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2021/02/27
 *    desc   : 标题栏初始器
 */
public final class TitleBarStyle extends LightBarStyle {

    @Override
    public TextView newTitleView(Context context) {
        return new TextView(context);
    }

    @Override
    public TextView newLeftView(Context context) {
        return new TextView(context);
    }

    @Override
    public TextView newRightView(Context context) {
        return new TextView(context);
    }

    @Override
    public Drawable getTitleBarBackground(Context context) {
        return new ColorDrawable(ContextCompat.getColor(context, R.color.tran));
    }

    @Override
    public Drawable getBackButtonDrawable(Context context) {
        return ContextCompat.getDrawable(context, R.mipmap.arrows_left_ic);
    }

    @Override
    public Drawable getLeftTitleBackground(Context context) {
        return null;
    }

    @Override
    public Drawable getRightTitleBackground(Context context) {
        return null;
    }

    @Override
    public int getChildVerticalPadding(Context context) {
        return (int) context.getResources().getDimension(R.dimen.dp_8);
    }
    @Override
    public Typeface getTitleTypeface(Context context, int style) {
        return super.getTitleTypeface(context, Typeface.BOLD);
    }

    @Override
    public Typeface getLeftTitleTypeface(Context context, int style) {
        return super.getLeftTitleTypeface(context, Typeface.BOLD);
    }

    @Override
    public Typeface getRightTitleTypeface(Context context, int style) {
        return super.getRightTitleTypeface(context, Typeface.BOLD);
    }

    @Override
    public float getTitleSize(Context context) {
        return context.getResources().getDimension(R.dimen.sp_18);
    }

    @Override
    public float getLeftTitleSize(Context context) {
        return context.getResources().getDimension(R.dimen.sp_18);
    }

    @Override
    public float getRightTitleSize(Context context) {
        return context.getResources().getDimension(R.dimen.sp_13);
    }

    @Override
    public int getTitleIconPadding(Context context) {
        return 0;
    }

    @Override
    public int getLeftIconPadding(Context context) {
        return (int) context.getResources().getDimension(R.dimen.dp_2);
    }

    @Override
    public int getRightIconPadding(Context context) {
        return (int) context.getResources().getDimension(R.dimen.dp_2);
    }
}