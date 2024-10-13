package com.pj.mind.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.hjq.base.BaseAdapter;
import com.hjq.base.action.ClickAction;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallbackProxy;
import com.hjq.toast.Toaster;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.pj.mind.R;
import com.pj.mind.app.AppActivity;
import com.pj.mind.app.AppFragment;
import com.pj.mind.http.Model.HttpData;
import com.pj.mind.http.api.mind.ArticleApi;
import com.pj.mind.http.api.mind.ArticleDeleteApi;
import com.pj.mind.http.api.mind.ArticleListApi;
import com.pj.mind.http.api.mind.ArticleListByIdsApi;
import com.pj.mind.http.api.mind.AssessmentListApi;
import com.pj.mind.http.api.mind.CategoryApi;
import com.pj.mind.http.api.mind.InboxApi;
import com.pj.mind.ui.adapter.AdapterTabFragmentPage2;
import com.pj.mind.ui.adapter.AdapterTiezi;
import com.pj.mind.ui.adapter.CepingAdapter;
import com.pj.mind.ui.dialog.popupView.TieziPopup;
import com.pj.mind.ui.fragment.WenzhangFragment;
import com.pj.mind.utils.NullUtil;
import com.pj.mind.utils.PreferencesUtils;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.Jzvd;
import okhttp3.Call;

public class ShoucangAcitivity extends AppActivity implements ClickAction {


    private RecyclerView recyclerView;
    private AdapterTiezi adapter;
    private SmartRefreshLayout smartRefreshLayout;

    private List<Integer> ids = new ArrayList<>();


    public static void start(Context context) {
        Intent intent = new Intent(context, ShoucangAcitivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shoucang;
    }

    @Override
    protected void initView() {

        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        smartRefreshLayout.setEnableRefresh(true);
        smartRefreshLayout.setEnableLoadMore(true);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new AdapterTiezi(getContext(),false);
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            initData();
        });
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> initData());
        adapter.setOnItemClickListener((recyclerView, itemView, position) -> {
            WenzhangDetailActivity.start(getContext(), adapter.getData().get(position).getId());
        });

        adapter.setOnChildClickListener(R.id.tv_shanchu, new BaseAdapter.OnChildClickListener() {
            @Override
            public void onChildClick(RecyclerView recyclerView, View childView, int position) {
                new XPopup.Builder(getContext()).asConfirm("", "是否确认删除？", () -> {
                    toDelete(adapter.getData().get(position).getId());
                });
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void toDelete(int id) {
        EasyHttp.post(this).api(new ArticleDeleteApi()
                        .setId(id))
                .request(new HttpCallbackProxy<HttpData<Void>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<Void> bean) {
                        Toaster.show("删除成功");
                        getData();
                    }
                });
    }

    @Override
    protected void initData() {
        getData();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onNoDoubleClick(View view) {
    }


    private void getData() {
        ids = PreferencesUtils.getInstance().getValueWithIds(getContext(), "shoucang");
        if (ids.size() == 0) {
            adapter.clearData();
            return;
        }
        EasyHttp.get(this).api(new ArticleListByIdsApi()
                        .setIds(ids))
                .request(new HttpCallbackProxy<HttpData<List<ArticleListApi.ArticleBean>>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<List<ArticleListApi.ArticleBean>> bean) {
                        if (bean.getData() == null) {
                            return;
                        }
                        List<ArticleListApi.ArticleBean> data = bean.getData();
                        adapter.setData(data);
                    }

                    @Override
                    public void onHttpEnd(Call call) {
                        smartRefreshLayout.finishRefresh();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

}