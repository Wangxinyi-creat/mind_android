package com.pj.mind.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.base.action.ClickAction;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallbackProxy;
import com.hjq.toast.Toaster;
import com.lxj.xpopup.XPopup;
import com.pj.mind.R;
import com.pj.mind.app.AppActivity;
import com.pj.mind.http.Model.HttpData;
import com.pj.mind.http.api.mind.CounselorApi;
import com.pj.mind.http.api.mind.CounselorRecordApi;
import com.pj.mind.ui.adapter.ZixunshiAdapter;
import com.pj.mind.ui.dialog.popupView.YuyuePopup;
import com.pj.mind.utils.NullUtil;
import com.pj.mind.utils.PreferencesUtils;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;

import java.util.List;

import okhttp3.Call;

public class ZixunShiActivity extends AppActivity implements ClickAction {

    private RecyclerView recyclerView;
    private ZixunshiAdapter adapter;
    private EditText editText;
    private SmartRefreshLayout smartRefreshLayout;
    private int pageNum = 1;
    private int total = -1;

    public static void start(Context context) {
        Intent intent = new Intent(context, ZixunShiActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zixunshi;
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.tv_search);
        editText = findViewById(R.id.editText);
        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        smartRefreshLayout.setEnableRefresh(true);
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            pageNum = 1;
            initData();
        });
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> initData());

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ZixunshiAdapter(getContext());
        adapter.setOnItemClickListener((recyclerView, itemView, position) ->
                getYuyueDialog(adapter.getData().get(position).getCounselorId()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        getData("");
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onNoDoubleClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                pageNum = 1;
                getData(editText.getText().toString().trim());
                break;
        }
    }

    private void getData(String key) {
        EasyHttp.get(this).api(new CounselorApi()
                        .setPageNum(pageNum)
                        .setCounselorName(key))
                .request(new HttpCallbackProxy<HttpData<List<CounselorApi.CounselorBean>>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<List<CounselorApi.CounselorBean>> bean) {
                        if (bean.getData() == null) {
                            return;
                        }
                        List<CounselorApi.CounselorBean> data = bean.getData();
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

    private void getYuyueDialog(int id) {
        new XPopup.Builder(this).asCustom(new YuyuePopup(this, data ->
                yuyue(id, data))
        ).show();
    }

    private void yuyue(int id, YuyuePopup.FormData formData) {
        EasyHttp.post(this).api(new CounselorRecordApi()
                        .setCounselorId(id)
                        .setName(formData.getName())
                        .setCollegeClass(formData.getCollegeClass())
                        .setPhone(formData.getPhone())
                        .setCollege(formData.getCollege())
                        .setUserId(PreferencesUtils.getInstance().get("userId", 0)))
                .request(new HttpCallbackProxy<HttpData<Void>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<Void> bean) {
                        Toaster.show("预约成功");
                    }
                });
    }
}

