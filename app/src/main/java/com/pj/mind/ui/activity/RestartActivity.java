package com.pj.mind.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.pj.mind.app.AppActivity;
import com.pj.mind.app.AppApplication;


public final class RestartActivity extends AppActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, RestartActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {}

    @Override
    protected void initData() {
        restart(this);
        finish();
//        toast(R.string.common_crash_hint);
    }

    public static void restart(Context context) {
        Intent intent;
        if (!AppApplication.getInstance().isLogin()) {
            // 如果是未登录的情况下跳转到闪屏页
            intent = new Intent(context, SplashActivity.class);
        } else {
            // 如果是已登录的情况下跳转到首页
            intent = new Intent(context, MainActivity.class);
        }

        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }
}
