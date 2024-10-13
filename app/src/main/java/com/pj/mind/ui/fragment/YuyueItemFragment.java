package com.pj.mind.ui.fragment;


import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.base.action.ClickAction;
import com.pj.mind.R;
import com.pj.mind.app.AppActivity;
import com.pj.mind.app.AppApplication;
import com.pj.mind.app.AppFragment;
import com.pj.mind.http.api.main.MainApi;
import com.pj.mind.manage.ActivityManager;
import com.pj.mind.ui.activity.LoginActivity;
import com.pj.mind.ui.adapter.YueyueHAdapter;
import com.pj.mind.ui.adapter.YueyueItemHAdapter;
import com.pj.mind.utils.PreferencesUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Created by   on 2023/8/2
 */
public class YuyueItemFragment extends AppFragment<AppActivity> implements ClickAction {
    private int type = 0;
    private RecyclerView recyclerView;
    private LinearLayout ll_con;
    private YueyueItemHAdapter adapter;

    public static YuyueItemFragment newInstance(int type) {
        return new YuyueItemFragment(type);
    }

    public YuyueItemFragment(int type) {
        this.type = type;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_yuyue_item;
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        ll_con = findViewById(R.id.ll_con);
        recyclerView.setVisibility(type == 0 ? View.VISIBLE : View.GONE);
        ll_con.setVisibility(type == 1 ? View.VISIBLE : View.GONE);
        if (type > 0) {
            return;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        adapter = new YueyueItemHAdapter(getContext());
        recyclerView.setAdapter(adapter);

        List<MainApi.MainBean> list1 = new ArrayList<>();
        list1.add(new MainApi.MainBean());
        list1.add(new MainApi.MainBean());
        list1.add(new MainApi.MainBean());
        list1.add(new MainApi.MainBean());
        list1.add(new MainApi.MainBean());
        list1.add(new MainApi.MainBean());
        list1.add(new MainApi.MainBean());
        adapter.setData(list1);
    }

    @Override
    protected void initData() {
    }



    @Override
    public void onNoDoubleClick(View view) {

    }

}
