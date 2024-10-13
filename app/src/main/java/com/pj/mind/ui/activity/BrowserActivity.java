package com.pj.mind.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.hjq.bar.TitleBar;
import com.pj.mind.R;
import com.pj.mind.app.AppActivity;
import com.pj.mind.app.action.StatusAction;
import com.pj.mind.utils.NullUtil;
import com.pj.mind.view.BrowserView;
import com.pj.mind.view.StatusLayout;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import timber.log.Timber;


/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 浏览器界面
 */
public final class BrowserActivity extends AppActivity
        implements StatusAction, OnRefreshListener {

    private static final String INTENT_KEY_IN_URL = "url";
    private static final String INTENT_KEY_IN_SUB_URL = "sub_url";


    public static void start(Context context, String url, @NonNull String isSystemOpen, String subUrl) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        if ("1".equals(isSystemOpen) || !checkUrl(url)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            }
        } else {
            Intent intent = new Intent(context, BrowserActivity.class);
            intent.putExtra(INTENT_KEY_IN_URL, url);
            intent.putExtra(INTENT_KEY_IN_SUB_URL, subUrl);
            if (!(context instanceof Activity)) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            context.startActivity(intent);
        }
    }

    public static void start(Context context, String url, @NonNull String isSystemOpen) {
        start(context, url, isSystemOpen, "");
    }

    public static void start(Context context, String url) {
        start(context, url, "0", "");
    }


    private StatusLayout mStatusLayout;
    private ProgressBar mProgressBar;
    private SmartRefreshLayout mRefreshLayout;
    private BrowserView mBrowserView;

    @Override
    protected int getLayoutId() {
        return R.layout.browser_activity;
    }

    @Override
    protected void initView() {
        mStatusLayout = findViewById(R.id.hl_browser_hint);
        mProgressBar = findViewById(R.id.pb_browser_progress);
        mRefreshLayout = findViewById(R.id.sl_browser_refresh);
        mBrowserView = findViewById(R.id.wv_browser_view);

        // 设置 WebView 生命管控
        mBrowserView.setLifecycleOwner(this);
        // 设置网页刷新监听
        mRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
        showLoading();

        mBrowserView.setBrowserViewClient(new AppBrowserViewClient());
        mBrowserView.setBrowserChromeClient(new AppBrowserChromeClient(mBrowserView));
        if (!NullUtil.isStringEmpty(getString(INTENT_KEY_IN_SUB_URL))) {
            mBrowserView.postUrl(getString(INTENT_KEY_IN_URL), getString(INTENT_KEY_IN_SUB_URL).getBytes());
        } else {
            mBrowserView.loadUrl(getString(INTENT_KEY_IN_URL));
        }
    }

    @Override
    public StatusLayout getStatusLayout() {
        return mStatusLayout;
    }

    @Override
    public void onLeftClick(TitleBar titleBar) {
        onBackPressed();
    }

    @Override
    public void onRightClick(TitleBar titleBar) {
        finish();
    }

    @Override
    public void onBackPressed() {
        if (mBrowserView.canGoBack()) {
            mBrowserView.goBack();
        } else {
            finish();
        }
    }

    /**
     * 重新加载当前页
     */
    private void reload() {
        showLoading();
        setTitle("");
        mBrowserView.reload();
    }

    private static boolean checkUrl(String url) {
        String scheme = Uri.parse(url).getScheme();
        if (scheme == null) {
            return false;
        }
        switch (scheme) {
            // 如果这是跳链接操作
            case "http":
            case "https":
            default:
                return false;
        }
    }

    /**
     * {@link OnRefreshListener}
     */

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        reload();
    }

    private class AppBrowserViewClient extends BrowserView.BrowserViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Timber.i("WebView shouldOverrideUrlLoading：%s", url);
            if (checkUrl(url)) {
                view.loadUrl(url);
            } else {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
            return true;
        }

        /**
         * 同名 API 兼容
         */
        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return shouldOverrideUrlLoading(view, request.getUrl().toString());
        }


        /**
         * 网页加载错误时回调，这个方法会在 onPageFinished 之前调用
         */
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            // 这里为什么要用延迟呢？因为加载出错之后会先调用 onReceivedError 再调用 onPageFinished
            post(() -> showError(listener -> reload()));
        }

        /**
         * 开始加载网页
         */
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            mProgressBar.setVisibility(View.VISIBLE);
        }

        /**
         * 完成加载网页
         */
        @Override
        public void onPageFinished(WebView view, String url) {
            mProgressBar.setVisibility(View.GONE);
            mRefreshLayout.finishRefresh();
            showComplete();
        }
    }

    private class AppBrowserChromeClient extends BrowserView.BrowserChromeClient {

        private AppBrowserChromeClient(BrowserView view) {
            super(view);
        }

        /**
         * 收到网页标题
         */
        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (title == null) {
                return;
            }
            setTitle(title);
        }

        /**
         * 收到加载进度变化
         */
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mProgressBar.setProgress(newProgress);
        }
    }
}