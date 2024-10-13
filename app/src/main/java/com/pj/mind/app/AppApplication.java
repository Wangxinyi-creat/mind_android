package com.pj.mind.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.hjq.bar.TitleBar;
import com.hjq.gson.factory.GsonFactory;
import com.hjq.http.EasyConfig;
import com.hjq.http.config.IRequestInterceptor;
import com.hjq.http.model.HttpHeaders;
import com.hjq.http.model.HttpParams;
import com.hjq.http.request.HttpRequest;
import com.hjq.toast.ToastLogInterceptor;
import com.hjq.toast.Toaster;

import com.pj.mind.AppConfig;
import com.pj.mind.R;
import com.pj.mind.app.action.MyActivityLifecycleCallbacks;
import com.pj.mind.http.Model.RequestHandler;
import com.pj.mind.http.Model.RequestServer;
import com.pj.mind.manage.ActivityManager;
import com.pj.mind.other.CrashHandler;
import com.pj.mind.other.DebugLoggerTree;
import com.pj.mind.other.TitleBarStyle;
import com.pj.mind.ui.activity.MainActivity;
import com.pj.mind.utils.NullUtil;
import com.pj.mind.utils.PreferencesUtils;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import okhttp3.OkHttpClient;
import timber.log.Timber;

public class AppApplication extends Application {
    private static AppApplication instance;
    private static boolean isHidden = false;

    public static AppApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initSdk(this);
        registerActivityLifecycleCallbacks(new MyActivityLifecycleCallbacks() {
            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                isHidden = false;
            }

        });

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        // 清理所有图片内存缓存
//        GlideApp.get(this).onLowMemory();
//    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        // 根据手机内存剩余情况清理图片内存缓存
    }

    /**
     * 初始化一些第三方框架
     */
    public static void initSdk(Application application) {
        // 设置标题栏初始化器
        TitleBar.setDefaultStyle(new TitleBarStyle());

        // 设置全局的 Header 构建器
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((cx, layout) ->
                new MaterialHeader(application).setColorSchemeColors(ContextCompat.getColor(application, R.color.common_accent_color)));
        // 设置全局的 Footer 构建器
//        SmartRefreshLayout.setDefaultRefreshFooterCreator((cx, layout) -> new SmartBallPulseFooter(application));
        // 设置全局初始化器
        SmartRefreshLayout.setDefaultRefreshInitializer((cx, layout) -> {
            // 刷新头部是否跟随内容偏移
            layout.setEnableHeaderTranslationContent(true)
                    .setEnableRefresh(true)//是否启用刷新
                    .setEnableLoadMore(true)
                    // 仿苹果越界效果开关
                    .setEnableOverScrollDrag(false);
        });
        // 初始化吐司
        Toaster.init(application);
        // 设置调试模式
        Toaster.setDebugMode(AppConfig.isDebug());
        // 设置 Toast 拦截器
        Toaster.setInterceptor(new ToastLogInterceptor());

        // 本地异常捕捉
        CrashHandler.register(application);

        // Activity 栈管理初始化
        ActivityManager.getInstance().init(application);


        // 网络请求框架初始化
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        EasyConfig.with(okHttpClient)
                // 是否打印日志
                .setLogEnabled(AppConfig.isLogEnable())
                // 设置服务器配置
                .setServer(new RequestServer())
                .setHandler(new RequestHandler(AppApplication.getInstance()))
                .setInterceptor(new IRequestInterceptor() {
                    @Override
                    public void interceptArguments(@NonNull HttpRequest<?> httpRequest, @NonNull HttpParams params, @NonNull HttpHeaders headers) {
                        headers.put("Authorization", PreferencesUtils.getInstance().getToken(AppApplication.getInstance()));
                    }
                })
                .setRetryCount(1)
                .into();

        // 设置 Json 解析容错监听
        GsonFactory.setJsonCallback((typeToken, fieldName, jsonToken) -> {

        });

        // 初始化日志打印
        if (AppConfig.isLogEnable()) {
            Timber.plant(new DebugLoggerTree());
        }

        // 注册网络状态变化监听
        ConnectivityManager connectivityManager = ContextCompat.getSystemService(application, ConnectivityManager.class);
        if (connectivityManager != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() {
                @Override
                public void onLost(@NonNull Network network) {
                    Activity topActivity = ActivityManager.getInstance().getTopActivity();
                    if (!(topActivity instanceof LifecycleOwner)) {
                        return;
                    }

                    LifecycleOwner lifecycleOwner = ((LifecycleOwner) topActivity);
                    if (lifecycleOwner.getLifecycle().getCurrentState() != Lifecycle.State.RESUMED) {
                        return;
                    }
                    if (topActivity instanceof MainActivity) {

                    } else {
                        Toaster.show("暂无网络");
                    }
                }
            });
        }
    }

    public boolean isLogin() {
        return !NullUtil.isStringEmpty(PreferencesUtils.getInstance().getToken(this));
    }

    public boolean isTest() {
        String testCust = PreferencesUtils.getInstance().getTestCust(this);
        return !NullUtil.isStringEmpty(testCust) && "1".equals(testCust);
    }

    public static boolean isHidden() {
        return isHidden;
    }
}
