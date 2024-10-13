package com.pj.mind.ui.fragment;


import android.view.View;
import android.widget.LinearLayout;

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
import com.pj.mind.app.AppAdapter;
import com.pj.mind.app.AppFragment;
import com.pj.mind.http.Model.HttpData;
import com.pj.mind.http.api.main.MainApi;
import com.pj.mind.http.api.mind.ActivityApi;
import com.pj.mind.http.api.mind.ActivityYuyueListApi;
import com.pj.mind.http.api.mind.ArticleApi;
import com.pj.mind.http.api.mind.ArticleDeleteApi;
import com.pj.mind.http.api.mind.ArticleListApi;
import com.pj.mind.ui.activity.WenzhangDetailActivity;
import com.pj.mind.ui.adapter.AdapterFaxian;
import com.pj.mind.ui.adapter.AdapterTiezi;
import com.pj.mind.ui.adapter.YueyueItemHAdapter;
import com.pj.mind.utils.NullUtil;
import com.pj.mind.utils.PreferencesUtils;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cn.jzvd.Jzvd;
import okhttp3.Call;


/**
 * @author Created by   on 2023/8/2
 */
public class WenzhangFragment extends AppFragment<AppActivity> implements ClickAction {
    private RecyclerView recyclerView;
    private SmartRefreshLayout smartRefreshLayout;
    private AdapterTiezi adapter;
    private int pageNum = 1;
    private int total = -1;
    private int categoryId;

    public static WenzhangFragment newInstance(int categoryId) {
        return new WenzhangFragment(categoryId);
    }

    public WenzhangFragment(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wenzhang;
    }

    @Override
    protected void initView() {

        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        smartRefreshLayout.setEnableRefresh(true);
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            pageNum = 1;
            initData();
        });

        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> initData());

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new AdapterTiezi(getContext(), true);
        adapter.setOnChildClickListener(R.id.tv_shoucang, (recyclerView, childView, position) -> {
            ArticleListApi.ArticleBean bean = adapter.getData().get(position);
            boolean isIn = PreferencesUtils.getInstance().isInValues(getContext(), "shoucang", bean.getId());
            if (isIn) {
                return;
            }
            Toaster.show("收藏成功");
            PreferencesUtils.getInstance().saveValueByIds(getContext(), "shoucang", bean.getId());
        });
        adapter.setOnChildClickListener(R.id.tv_shanchu, (recyclerView, childView, position) ->
                new XPopup.Builder(getContext()).asConfirm("", "是否确认删除？", () -> {
                    toDelete(adapter.getData().get(position).getId());
                }).show());
        adapter.setOnItemClickListener((recyclerView, itemView, position) ->
                WenzhangDetailActivity.start(getContext(), adapter.getData().get(position).getId()));
        recyclerView.setAdapter(adapter);

    }

    private void toDelete(int id) {
        EasyHttp.delete(this).api(new ArticleDeleteApi()
                        .setId(id))
                .request(new HttpCallbackProxy<HttpData<Void>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<Void> bean) {
                        Toaster.show("删除成功");
                        pageNum = 1;
                        getArticleData();
                    }
                });
    }

    @Override
    protected void initData() {
        getArticleData();
    }

    @Override
    public void onRefreshLayout() {
        pageNum = 1;
        initData();
    }

    private void getArticleData() {
        EasyHttp.get(this).api(new ArticleListApi()
                        .setPageNum(pageNum)
                        .setCategoryId(categoryId))
                .request(new HttpCallbackProxy<HttpData<List<ArticleListApi.ArticleBean>>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<List<ArticleListApi.ArticleBean>> bean) {
                        if (bean.getData() == null) {
                            return;
                        }
                        List<ArticleListApi.ArticleBean> data = bean.getData();
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
