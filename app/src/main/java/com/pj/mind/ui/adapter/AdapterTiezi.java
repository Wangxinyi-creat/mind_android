package com.pj.mind.ui.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hjq.base.BaseAdapter;
import com.pj.mind.AppContract;
import com.pj.mind.R;
import com.pj.mind.app.AppAdapter;
import com.pj.mind.http.api.mind.ActivityApi;
import com.pj.mind.http.api.mind.ArticleListApi;
import com.pj.mind.utils.GlideLoadUtils;
import com.pj.mind.utils.NullUtil;
import com.pj.mind.utils.PreferencesUtils;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;


public class AdapterTiezi extends AppAdapter<ArticleListApi.ArticleBean> {
    private boolean isShowShoucang = true;

    public AdapterTiezi(@NonNull Context context, boolean isShowShoucang) {
        super(context);
        this.isShowShoucang = isShowShoucang;
    }

    @NonNull
    @Override
    public BaseAdapter<?>.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder();
    }


    private final class ViewHolder extends AppAdapter<?>.ViewHolder {
        private JzvdStd jzvdStd;
        private TextView title, content, tv_time, tv_name, tv_pinglun ;
        private ImageView iv_touxiang,tv_shoucang,tv_shanchu;
        private LinearLayout ll_user;


        private ViewHolder() {
            super(R.layout.item_tiezi);
            jzvdStd = findViewById(R.id.jz_video);
            title = findViewById(R.id.title);
            content = findViewById(R.id.content);
            tv_time = findViewById(R.id.tv_time);
            iv_touxiang = findViewById(R.id.iv_touxiang);
            tv_name = findViewById(R.id.tv_name);
            ll_user = findViewById(R.id.ll_user);
            tv_pinglun = findViewById(R.id.tv_pinglun);
            tv_shoucang = findViewById(R.id.tv_shoucang);
            tv_shanchu = findViewById(R.id.tv_shanchu);
        }

        @Override
        public void onBindView(int position) {
            ArticleListApi.ArticleBean bean = getItem(position);
            tv_time.setText(bean.getCreateTime());

            content.setText(Html.fromHtml(bean.getContent()), TextView.BufferType.SPANNABLE);

            title.setText(bean.getTitle());
            boolean isShowShanchu = bean.getUserId() == PreferencesUtils.getInstance().get("userId", 0);
            tv_shanchu.setVisibility(isShowShanchu ? View.VISIBLE : View.GONE);
            jzvdStd.setVisibility(bean.getIsVideo() == 1 ? View.VISIBLE : View.GONE);
            content.setVisibility(bean.getIsVideo() == 1 ? View.GONE : View.VISIBLE);
            tv_pinglun.setText(bean.getComments() + "");
            tv_shoucang.setVisibility(isShowShoucang ? View.VISIBLE : View.GONE);
//            boolean isIn = PreferencesUtils.getInstance().isInValues(getContext(), "shoucang", bean.getId());
//            tv_shoucang.setText(isIn ? "已收藏" : "收藏");
            if (bean.getIsVideo() == 1) {
                jzvdStd.setUp(AppContract.BASE_URL + bean.getVideoUrl(), bean.getTitle());
            }
            ll_user.setVisibility(bean.getUserId() > 0 ? View.VISIBLE : View.GONE);
            if (bean.getUserId() > 0) {
                tv_name.setText(bean.getAnonymous() == 1 ? "匿名" : bean.getUser().getNickName());
                if (!NullUtil.isStringEmpty(bean.getUser().getAvatar())) {
                    GlideLoadUtils.getInstance().glideLoadCircle(getContext(), AppContract.BASE_URL + bean.getUser().getAvatar(), iv_touxiang, R.mipmap.bg_login);
                }
            }
        }
    }


    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }

    }

    protected void onPause() {
        Jzvd.releaseAllVideos();
    }
}
