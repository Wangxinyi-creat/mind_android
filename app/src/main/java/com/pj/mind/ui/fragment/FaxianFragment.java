package com.pj.mind.ui.fragment;


import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.base.BaseAdapter;
import com.hjq.base.action.ClickAction;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallbackProxy;
import com.hjq.toast.Toaster;
import com.lxj.xpopup.XPopup;
import com.pj.mind.R;
import com.pj.mind.app.AppActivity;
import com.pj.mind.app.AppFragment;
import com.pj.mind.http.Model.HttpData;
import com.pj.mind.http.api.mind.ActivityApi;
import com.pj.mind.http.api.mind.ActivityYuyueApi;
import com.pj.mind.http.api.mind.ActivityYuyueListApi;
import com.pj.mind.ui.adapter.AdapterFaxian;
import com.pj.mind.ui.adapter.AdapterFaxianTop;
import com.pj.mind.ui.adapter.AdapterFaxianYuyue;
import com.pj.mind.utils.PreferencesUtils;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.List;

import okhttp3.Call;


/**
 * @author Created by   on 2023/8/2
 */
public class FaxianFragment extends AppFragment<AppActivity> implements ClickAction {
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    private AdapterFaxianTop adapterFaxianTop;
    private AdapterFaxianYuyue adapterFaxianYuyue;
    private AdapterFaxian adapterFaxian;
    private SmartRefreshLayout smartRefreshLayout;

    public static FaxianFragment newInstance() {
        return new FaxianFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_faxian;
    }

    @Override
    protected void initView() {
        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setEnableRefresh(true);
        smartRefreshLayout.setEnableOverScrollDrag(false);
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> initData());

        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        adapterFaxianTop = new AdapterFaxianTop(getContext());

        adapterFaxianTop.setOnItemClickListener((recyclerView, itemView, position) -> {
                    ActivityApi.ActivityBean activityBean = adapterFaxianTop.getData().get(position);
                    new XPopup.Builder(getContext()).asConfirm("预约", "是否预约"+activityBean.getActivityName(), () ->
                            yuyue(activityBean.getActivityId())
                    ).show();
                }

        );
        recyclerView1.setAdapter(adapterFaxianTop);

        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        adapterFaxianYuyue = new AdapterFaxianYuyue(getContext());
        recyclerView2.setAdapter(adapterFaxianYuyue);

        recyclerView3 = findViewById(R.id.recyclerView3);
        recyclerView3.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterFaxian = new AdapterFaxian(getContext());
        adapterFaxian.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView recyclerView, View itemView, int position) {

            }
        });
        recyclerView3.setAdapter(adapterFaxian);
    }

    @Override
    protected void initData() {
        getActivityData(0, adapterFaxianTop, null);
        getActivityData(2, null, adapterFaxian);
        getActivityYuyueData();
    }

    private void getActivityYuyueData() {
        EasyHttp.get(this).api(new ActivityYuyueListApi()
                        .setAppointmentStatus(0)
                        .setUserId(PreferencesUtils.getInstance().get("userId", 0)))
                .request(new HttpCallbackProxy<HttpData<List<ActivityYuyueListApi.ActivityYuyueBean>>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<List<ActivityYuyueListApi.ActivityYuyueBean>> bean) {
                        if (bean.getData() == null) {
                            return;
                        }
                        List<ActivityYuyueListApi.ActivityYuyueBean> data = bean.getData();
                        adapterFaxianYuyue.setData(data);
                    }
                });

    }

    private void getActivityData(int status, AdapterFaxianTop top, AdapterFaxian faxian) {
        EasyHttp.get(this).api(new ActivityApi()
                        .setPageNum(1)
                        .setPageSize(100)
                        .setAppointmentStatus(status))
                .request(new HttpCallbackProxy<HttpData<List<ActivityApi.ActivityBean>>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<List<ActivityApi.ActivityBean>> bean) {
                        if (bean.getData() == null) {
                            return;
                        }
                        List<ActivityApi.ActivityBean> data = bean.getData();
                        if (top != null) {
                            top.setData(data);
                        } else if (faxian != null) {
                            faxian.setData(data);
                        }
                    }
                });
    }

    private void yuyue(int id) {
        EasyHttp.post(this).api(new ActivityYuyueApi()
                        .setActivityId(id)
                        .setUserId(PreferencesUtils.getInstance().get("userId", 0)))
                .request(new HttpCallbackProxy<HttpData<Void>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<Void> bean) {
                        Toaster.show("预约成功");
                        initData();
                    }
                });
    }

    @Override
    public void onHttpEnd(Call call) {
        super.onHttpEnd(call);
        smartRefreshLayout.finishRefresh();
    }
}
