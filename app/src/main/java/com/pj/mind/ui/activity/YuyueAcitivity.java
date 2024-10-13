package com.pj.mind.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.base.action.ClickAction;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallbackProxy;
import com.pj.mind.R;
import com.pj.mind.app.AppActivity;
import com.pj.mind.http.Model.HttpData;
import com.pj.mind.http.api.mind.ActivityApi;
import com.pj.mind.http.api.mind.ActivityYuyueListApi;
import com.pj.mind.ui.adapter.AdapterFaxian;
import com.pj.mind.ui.adapter.ZixunshiAdapter;
import com.pj.mind.utils.NullUtil;
import com.pj.mind.utils.PreferencesUtils;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import okhttp3.Call;

public class YuyueAcitivity extends AppActivity implements ClickAction {

    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private AdapterFaxian adapter;
    private int pageNum = 1;
    private int total = -1;

    public static void start(Context context) {
        Intent intent = new Intent(context, YuyueAcitivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_yuyue;
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.iv_back);

        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        smartRefreshLayout.setEnableRefresh(true);
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            pageNum = 1;
            initData();
        });

        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> initData());

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new AdapterFaxian(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        getActivityYuyueData();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onNoDoubleClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }

    private void getActivityYuyueData() {
        EasyHttp.get(this).api(new ActivityYuyueListApi()
                        .setPageNum(pageNum)
                        .setUserId(PreferencesUtils.getInstance().get("userId", 0)))
                .request(new HttpCallbackProxy<HttpData<List<ActivityYuyueListApi.ActivityYuyueBean>>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<List<ActivityYuyueListApi.ActivityYuyueBean>> bean) {
                        if (bean.getData() == null) {
                            return;
                        }
                        List<ActivityYuyueListApi.ActivityYuyueBean> list = bean.getData();
                        List<ActivityApi.ActivityBean> data = list.stream().map(activityYuyueBean -> activityYuyueBean.getMindActivity()).collect(Collectors.toList());
                        total = bean.getTotal();
                        if (!NullUtil.isListEmpty(data)) {
                            if (pageNum == 1) {
                                adapter.setData(data);//更新数据
                            } else {
                                adapter.addData(data);
                            }
                            pageNum++;
                        } else {
                            if (pageNum == 1) {
                                adapter.clearData();
                            }
                            //没有数据
                        }
                    }

                    @Override
                    public void onHttpEnd(Call call) {
                        smartRefreshLayout.finishRefresh();
                        if (total >= (pageNum - 1) * 10) {
                            smartRefreshLayout.finishLoadMore();
                        } else {
                            smartRefreshLayout.finishLoadMoreWithNoMoreData();
                            smartRefreshLayout.setNoMoreData(true);//复原状态
                        }
                    }
                });
    }
}