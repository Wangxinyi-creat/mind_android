package com.pj.mind.app;

import com.hjq.base.BaseFragment;
import com.hjq.http.listener.OnHttpListener;
import com.pj.mind.app.action.ToastAction;
import com.pj.mind.http.Model.HttpData;

import okhttp3.Call;

public abstract class AppFragment<A extends AppActivity> extends BaseFragment<A>
        implements ToastAction, OnHttpListener<Object> {

    /**
     * 当前加载对话框是否在显示中
     */
    public boolean isShowDialog() {
        A activity = getAttachActivity();
        if (activity == null) {
            return false;
        }
        return activity.isShowDialog();
    }


    public void onRefreshLayout(){}

    /**
     * 显示加载对话框
     */
    public void showDialog() {
        A activity = getAttachActivity();
        if (activity == null) {
            return;
        }
        activity.showDialog();
    }

    /**
     * 隐藏加载对话框
     */
    public void hideDialog() {
        A activity = getAttachActivity();
        if (activity == null) {
            return;
        }
        activity.hideDialog();
    }

    /**
     * {@link OnHttpListener}
     */

    @Override
    public void onHttpStart(Call call) {
//        showDialog();
    }

    @Override
    public void onHttpSuccess(Object result) {
        if (!(result instanceof HttpData)) {
            return;
        }
//        toast(((HttpData<?>) result).getMessage());
    }

    @Override
    public void onHttpFail(Throwable e) {
        toast(e.getMessage());
    }

    @Override
    public void onHttpEnd(Call call) {
//        hideDialog();
    }
}
