package com.pj.mind.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.base.BaseAdapter;
import com.hjq.base.action.ClickAction;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallbackProxy;
import com.pj.mind.R;
import com.pj.mind.app.AppActivity;
import com.pj.mind.http.Model.HttpData;
import com.pj.mind.http.api.main.MainApi;
import com.pj.mind.http.api.mind.AssessmentListApi;
import com.pj.mind.http.api.mind.CounselorApi;
import com.pj.mind.ui.adapter.CepingAdapter;
import com.pj.mind.ui.adapter.YueyueHAdapter;
import com.pj.mind.ui.adapter.ZixunshiAdapter;
import com.pj.mind.utils.NullUtil;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class CepingActivity extends AppActivity implements ClickAction {

    private RecyclerView recyclerView;
    private CepingAdapter adapter;
    private EditText editText;
    private SmartRefreshLayout smartRefreshLayout;
    private int pageNum = 1;
    private int total = -1;

    public static void start(Context context) {
        Intent intent = new Intent(context, CepingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ceping;
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.tv_search);
        editText = findViewById(R.id.editText);

        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        smartRefreshLayout.setEnableRefresh(true);
        smartRefreshLayout.setEnableLoadMore(true);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new CepingAdapter(getContext());
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            pageNum = 1;
            initData();
        });
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> initData());
        adapter.setOnItemClickListener((recyclerView, itemView, position) -> {
            AssessmentListApi.AssessmentBean bean = adapter.getData().get(position);
            CepingDetailActivity.start(CepingActivity.this, bean.getAssessmentId(), bean.getAssessmentName());
        });
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
        EasyHttp.get(this).api(new AssessmentListApi()
                        .setPageNum(pageNum)
                        .setAssessmentName(key))
                .request(new HttpCallbackProxy<HttpData<List<AssessmentListApi.AssessmentBean>>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<List<AssessmentListApi.AssessmentBean>> bean) {
                        if (bean.getData() == null) {
                            return;
                        }
                        List<AssessmentListApi.AssessmentBean> data = bean.getData();
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

