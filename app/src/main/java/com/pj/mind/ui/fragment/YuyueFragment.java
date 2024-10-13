package com.pj.mind.ui.fragment;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.hjq.base.BaseAdapter;
import com.hjq.base.action.ClickAction;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallbackProxy;
import com.hjq.toast.Toaster;
import com.lxj.xpopup.XPopup;
import com.pj.mind.R;
import com.pj.mind.app.AppActivity;
import com.pj.mind.app.AppApplication;
import com.pj.mind.app.AppFragment;
import com.pj.mind.bean.ImgBean;
import com.pj.mind.http.Model.HttpData;
import com.pj.mind.http.api.main.MainApi;
import com.pj.mind.http.api.main.MainApi.MainBean;
import com.pj.mind.http.api.mind.CounselorApi;
import com.pj.mind.http.api.mind.CounselorRecordApi;
import com.pj.mind.ui.activity.CepingActivity;
import com.pj.mind.ui.activity.LoginActivity;
import com.pj.mind.ui.activity.QingsuAcitivity;
import com.pj.mind.ui.activity.ZixunShiActivity;
import com.pj.mind.ui.adapter.AdapterTabFragmentPage;
import com.pj.mind.ui.adapter.YueyueHAdapter;
import com.pj.mind.ui.dialog.popupView.YuyuePopup;
import com.pj.mind.utils.GlideUtils;
import com.pj.mind.utils.NullUtil;
import com.pj.mind.utils.PreferencesUtils;
import com.pj.mind.utils.ToolUtils;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;

public class YuyueFragment extends AppFragment<AppActivity> implements ClickAction {

    private TabLayout tab_layout;
    private ViewPager2 viewPager;
    private RecyclerView recyclerView;
    private YueyueHAdapter adapter;
    private List<String> tabList = Arrays.asList("心理咨询小故事", "了解心理咨询", "注意事项");
    private List<Fragment> fragments = new ArrayList<>();
    private AdapterTabFragmentPage adapterTabFragmentPage;
    private TabLayoutMediator mediator;

    public static YuyueFragment newInstance() {
        YuyueFragment mainFragment = new YuyueFragment();
        Bundle bundle = new Bundle();
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_yuyue;
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.ll_ceping, R.id.ll_zixunshi, R.id.ll_suqiu);

        tab_layout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewPager);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        adapter = new YueyueHAdapter(getContext());
        adapter.setOnItemClickListener((recyclerView, itemView, position) ->
                getYuyueDialog(adapter.getData().get(position).getCounselorId()));
        recyclerView.setAdapter(adapter);
    }

    private void initFragment() {
        if (NullUtil.isListEmpty(fragments)) {
            return;
        }
        adapterTabFragmentPage = new AdapterTabFragmentPage(getChildFragmentManager(), getLifecycle(), fragments);
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setAdapter(adapterTabFragmentPage);
        mediator = new TabLayoutMediator(tab_layout, viewPager, (TabLayout.Tab tab, int position) -> {
            tab.setText(tabList.get(position));

        });
        //要执行这一句才是真正将两者绑定起来
        mediator.attach();
    }


    @Override
    protected void initData() {
        for (int i = 0; i < tabList.size(); i++) {
            if (tabList.get(i).equals("心理咨询小故事")) {
                fragments.add(YuyueItemFragment.newInstance(0));
            } else if (tabList.get(i).equals("了解心理咨询")) {
                fragments.add(YuyueItemFragment.newInstance(1));
            } else if (tabList.get(i).equals("注意事项")) {
                fragments.add(YuyueItemFragment.newInstance(2));
            }
        }
        initFragment();

        getData();
    }

    @Override
    public void onNoDoubleClick(View view) {
        switch (view.getId()) {
            case R.id.ll_ceping:
                CepingActivity.start(getContext());
                break;
            case R.id.ll_zixunshi:
                ZixunShiActivity.start(getContext());
                break;
            case R.id.ll_suqiu:
                QingsuAcitivity.start(getContext(), 0);
                break;
        }
    }

    private void getData() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        EasyHttp.get(this).api(new CounselorApi()
                        .setPageNum(1)
                        .setPageSize(5)
                        .setAvailableWeek(dayOfWeek + ""))
                .request(new HttpCallbackProxy<HttpData<List<CounselorApi.CounselorBean>>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<List<CounselorApi.CounselorBean>> bean) {
                        if (bean.getData() == null) {
                            return;
                        }
                        List<CounselorApi.CounselorBean> data = bean.getData();
                        adapter.setData(data);
                    }
                });
    }

    private void getYuyueDialog(int id) {
        new XPopup.Builder(getContext()).asCustom(new YuyuePopup(getContext(), data ->
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
