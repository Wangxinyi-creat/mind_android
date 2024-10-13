package com.pj.mind.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;

public class KeyBoardUtil {
    private Activity activity;
    private KeyboardVisibilityListener keyboardVisibilityListener;

    public KeyBoardUtil(Activity activity, KeyboardVisibilityListener keyboardVisibilityListener) {
        this.activity = activity;
        this.keyboardVisibilityListener = keyboardVisibilityListener;
        init();
    }

    private void init() {
        View contentView = activity.getWindow().getDecorView();
        //初始化时先判断当前键盘状态
        boolean isVisibility = getScreenHeight(activity) > getWindowContentHeight(activity);
        //这个监听的主要作用是在键盘弹出布局发生改变时 动态的通知用户键盘是否弹出
        contentView.getViewTreeObserver().addOnGlobalLayoutListener(() ->
                keyboardVisibilityListener.onShow(getScreenHeight(activity) > getWindowContentHeight(activity)));
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    public static int getWindowContentHeight(Activity activity) {
        if (activity == null) return 0;
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.height();
    }

    public static int getKeyBordHeight(Activity activity) {
        return getScreenHeight(activity) - getWindowContentHeight(activity);
    }

    public interface KeyboardVisibilityListener {
        void onShow(boolean isShow);
    }
}
