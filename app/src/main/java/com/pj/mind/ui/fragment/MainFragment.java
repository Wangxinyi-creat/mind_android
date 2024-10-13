package com.pj.mind.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hjq.base.action.ClickAction;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallbackProxy;
import com.pj.mind.AppContract;
import com.pj.mind.R;
import com.pj.mind.app.AppActivity;
import com.pj.mind.app.AppApplication;
import com.pj.mind.app.AppFragment;
import com.pj.mind.bean.ImgBean;
import com.pj.mind.http.Model.HttpData;
import com.pj.mind.http.api.main.BannerApi;
import com.pj.mind.http.api.main.MainApi;
import com.pj.mind.http.api.main.MainApi.MainBean;
import com.pj.mind.ui.activity.CepingActivity;
import com.pj.mind.ui.activity.LoginActivity;
import com.pj.mind.ui.activity.WenzhangAcitivity;
import com.pj.mind.ui.activity.XinQingActivity;
import com.pj.mind.utils.GlideUtils;
import com.pj.mind.utils.ToolUtils;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class MainFragment extends AppFragment<AppActivity> implements ClickAction {

    private Banner banner;
    private ImageView touxiang;

    public static MainFragment newInstance() {
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.ll_ceping,R.id.ll_xinqing,R.id.ll_zaixian,R.id.ll_chahua);
        touxiang = findViewById(R.id.iv_touxiang);
        banner = findViewById(R.id.banner);
        banner.setBannerRound(ToolUtils.dp2px(getActivity(),6));
    }


    @Override
    protected void initData() {
        getBanner();
    }

    @Override
    public void onNoDoubleClick(View view) {
        switch (view.getId()) {
            case R.id.ll_ceping:
                CepingActivity.start(getContext());
                break;
            case R.id.ll_xinqing:
                XinQingActivity.start(getContext());
                break;
            case R.id.ll_zaixian:
                WenzhangAcitivity.start(getContext(),"在线课程",1);
                break;
            case R.id.ll_chahua:
                WenzhangAcitivity.start(getContext(),"茶话室",2);
                break;
        }
    }

    private void getBanner() {
        EasyHttp.get(this).api(new BannerApi())
                .request(new HttpCallbackProxy<HttpData<List<BannerApi.BannerBean>>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<List<BannerApi.BannerBean>> data) {
//                        smartRefreshLayout.finishRefresh();
                        if (data.getData() == null) {
                            return;
                        }
                        banner.setVisibility(data.getData().size() > 0 ? View.VISIBLE : View.GONE);
                        banner.setAdapter(new BannerImageAdapter<BannerApi.BannerBean>(data.getData()) {
                                    @Override
                                    public void onBindView(BannerImageHolder holder, BannerApi.BannerBean data, int position, int size) {
//                                        GlideLoadUtils.getInstance().glideLoadCornerImage(getContext(), AppContract.BASE_URL+data.getPictureUrl(), iv_tui1, R.mipmap.bg_default, 6);
//                                        GlideLoadUtils.getInstance().glideLoadCornerImage(getContext(), AppContract.BASE_URL+data.getPictureUrl(), iv_tui1, R.mipmap.bg_default, 6);
                                        GlideUtils.loadRoundImage(getActivity(), holder.imageView, AppContract.BASE_URL+data.getPictureUrl(), 6.0f);

                                    }
                                })
                                .addBannerLifecycleObserver(getActivity())//添加生命周期观察者
                                .setIndicator(new CircleIndicator(getContext()));
                    }

                    @Override
                    public void onHttpEnd(Call call) {
//                        smartRefreshLayout.finishRefresh();
                    }
                });
    }
}
