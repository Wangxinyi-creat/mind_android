package com.pj.mind.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hjq.base.action.ClickAction;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallbackProxy;
import com.hjq.toast.Toaster;
import com.lxj.xpopup.XPopup;
import com.pj.mind.AppContract;
import com.pj.mind.R;
import com.pj.mind.app.AppActivity;
import com.pj.mind.http.Model.HttpData;
import com.pj.mind.http.api.mind.ArticleApi;
import com.pj.mind.http.api.mind.ArticleInfoApi;
import com.pj.mind.http.api.mind.ArticleListApi;
import com.pj.mind.http.api.mind.CategoryApi;
import com.pj.mind.http.api.mind.CommentApi;
import com.pj.mind.http.api.mind.CommentListApi;
import com.pj.mind.http.api.mind.CounselorApi;
import com.pj.mind.ui.adapter.AdapterPinglun;
import com.pj.mind.ui.dialog.popupView.PinglunPopup;
import com.pj.mind.utils.GlideLoadUtils;
import com.pj.mind.utils.PreferencesUtils;

import java.util.List;

import cn.jzvd.JzvdStd;
import okhttp3.Call;

public class WenzhangDetailActivity extends AppActivity implements ClickAction {
    private static final String INTENT_KEY_IN_ID = "idd";

    private JzvdStd jzvdStd;
    private TextView title, content, tv_time, tv_name, tv_pinglun;
    private ImageView iv_touxiang;
    private LinearLayout ll_user;
    private RecyclerView recyclerView;
    private AdapterPinglun adapter;

    public static void start(Context context, int id) {
        Intent intent = new Intent(context, WenzhangDetailActivity.class);
        intent.putExtra(INTENT_KEY_IN_ID, id);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wenzhang_detail;
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.ll_pinglun);

        jzvdStd = findViewById(R.id.jz_video);
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        tv_time = findViewById(R.id.tv_time);
        iv_touxiang = findViewById(R.id.iv_touxiang);
        tv_name = findViewById(R.id.tv_name);
        ll_user = findViewById(R.id.ll_user);
        tv_pinglun = findViewById(R.id.tv_pinglun);
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new AdapterPinglun(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        getArticle();
        getComment();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onNoDoubleClick(View view) {
        switch (view.getId()) {
            case R.id.ll_pinglun:
                new XPopup.Builder(this).asCustom(new PinglunPopup(this, this::comment)).show();
                break;
        }
    }

    public void getArticle() {
        EasyHttp.get(this).api(new ArticleInfoApi()
                        .setActivityId(getInt(INTENT_KEY_IN_ID)))
                .request(new HttpCallbackProxy<HttpData<ArticleListApi.ArticleBean>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<ArticleListApi.ArticleBean> data) {
                        if (data.getData() == null) {
                            return;
                        }
                        ArticleListApi.ArticleBean bean = data.getData();
                        tv_time.setText(bean.getCreateTime());

                        content.setText(Html.fromHtml(bean.getContent()), TextView.BufferType.SPANNABLE);
                        title.setText(bean.getTitle());
                        jzvdStd.setVisibility(bean.getIsVideo() == 1 ? View.VISIBLE : View.GONE);
                        content.setVisibility(bean.getIsVideo() == 1 ? View.GONE : View.VISIBLE);
                        tv_pinglun.setText(bean.getComments()+"");
                        if (bean.getIsVideo() == 1) {
                            jzvdStd.setUp(AppContract.BASE_URL + bean.getVideoUrl(), bean.getTitle());
                        }
                        ll_user.setVisibility(bean.getUserId() > 0 ? View.VISIBLE : View.GONE);
                        if (bean.getUserId() > 0) {
                            tv_name.setText(bean.getAnonymous()==1?"匿名":bean.getUser().getNickName());
                            GlideLoadUtils.getInstance().glideLoadCircle(WenzhangDetailActivity.this, AppContract.BASE_URL + bean.getUser().getAvatar(), iv_touxiang, R.mipmap.bg_login);
                        }
                    }

                    @Override
                    public void onHttpEnd(Call call) {
                    }
                });
    }

    public void getComment() {

        EasyHttp.get(this).api(new CommentListApi()
                        .setArticleId(getInt(INTENT_KEY_IN_ID)))
                .request(new HttpCallbackProxy<HttpData<List<CommentListApi.CommentBean>>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<List<CommentListApi.CommentBean>> bean) {
                        if (bean.getData() == null) {
                            return;
                        }
                        List<CommentListApi.CommentBean> data = bean.getData();
                        adapter.setData(data);
                    }
                });

    }

    public void comment(String content) {
        EasyHttp.post(this).api(new CommentApi()
                        .setArticleId(getInt(INTENT_KEY_IN_ID))
                        .setContent(content)
                        .setUserId(PreferencesUtils.getInstance().get("userId", 0)))
                .request(new HttpCallbackProxy<HttpData<Void>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<Void> bean) {
                        Toaster.show("评论成功");
                        initData();
                    }

                    @Override
                    public void onHttpEnd(Call call) {
                    }
                });
    }
}

