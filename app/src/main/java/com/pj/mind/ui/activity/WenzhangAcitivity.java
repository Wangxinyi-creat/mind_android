package com.pj.mind.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.hjq.base.action.ClickAction;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallbackProxy;
import com.hjq.toast.Toaster;
import com.lxj.xpopup.XPopup;
import com.pj.mind.R;
import com.pj.mind.app.AppActivity;
import com.pj.mind.app.AppFragment;
import com.pj.mind.http.Model.HttpData;
import com.pj.mind.http.api.mind.ArticleApi;
import com.pj.mind.http.api.mind.CategoryApi;
import com.pj.mind.ui.adapter.AdapterTabFragmentPage2;
import com.pj.mind.ui.dialog.popupView.TieziPopup;
import com.pj.mind.ui.fragment.WenzhangFragment;
import com.pj.mind.utils.NullUtil;
import com.pj.mind.utils.PreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.Jzvd;
import okhttp3.Call;

public class WenzhangAcitivity extends AppActivity implements ClickAction {
    private static final String INTENT_KEY_IN_TITLE = "title";
    private static final String INTENT_KEY_IN_CATEGORY_ID = "categoryId";

    private List<WenzhangFragment> fragments = new ArrayList<>();
    private AdapterTabFragmentPage2 adapterTabFragmentPage;
    private TabLayoutMediator mediator;
    private TextView title;
    private TabLayout tab_layout;
    private ViewPager2 viewPager;
    private TextView fatie;

    public static void start(Context context, String title, int categoryId) {
        Intent intent = new Intent(context, WenzhangAcitivity.class);
        intent.putExtra(INTENT_KEY_IN_TITLE, title);
        intent.putExtra(INTENT_KEY_IN_CATEGORY_ID, categoryId);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wenzhang;
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.iv_back, R.id.fatie);
        title = findViewById(R.id.title);
        fatie = findViewById(R.id.fatie);
        fatie.setVisibility(getInt(INTENT_KEY_IN_CATEGORY_ID) == 2 ? View.VISIBLE : View.GONE);
        title.setText(getString(INTENT_KEY_IN_TITLE));

        tab_layout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewPager2);

    }

    @Override
    protected void initData() {
        if (!NullUtil.isListEmpty(fragments)) {
            return;
        }
        getCategory(getInt(INTENT_KEY_IN_CATEGORY_ID));
    }

    private void initFragment(List<CategoryApi.CategoryBean> tabList) {
        tabList.forEach(categoryBean -> fragments.add(WenzhangFragment.newInstance(categoryBean.getId())));
        adapterTabFragmentPage = new AdapterTabFragmentPage2(getSupportFragmentManager(), getLifecycle(), fragments);
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setAdapter(adapterTabFragmentPage);
        mediator = new TabLayoutMediator(tab_layout, viewPager, (TabLayout.Tab tab, int position) -> {
            tab.setText(tabList.get(position).getTitle());

        });
        //要执行这一句才是真正将两者绑定起来
        mediator.attach();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onNoDoubleClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.fatie:
                new XPopup.Builder(this).asCustom(new TieziPopup(this, this::postArticle)).show();
        }
    }

    private void getCategory(int pId) {
        EasyHttp.get(this).api(new CategoryApi()
                        .setPid(pId))
                .request(new HttpCallbackProxy<HttpData<List<CategoryApi.CategoryBean>>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<List<CategoryApi.CategoryBean>> data) {
                        if (data.getData() == null) {
                            return;
                        }
                        initFragment(data.getData());
                    }

                    @Override
                    public void onHttpEnd(Call call) {
                    }
                });
    }

    public void postArticle(String title, String content, int anonymous) {
        EasyHttp.post(this).api(new ArticleApi()
                        .setTitle(title)
                        .setCategoryId(7)
                        .setContent(content)
                        .setAnonymous(anonymous)
                        .setUserId(PreferencesUtils.getInstance().get("userId", 0)))
                .request(new HttpCallbackProxy<HttpData<Void>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<Void> bean) {
                        Toaster.show("发帖成功");
                        fragments.forEach(AppFragment::onRefreshLayout);
                    }

                    @Override
                    public void onHttpEnd(Call call) {

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