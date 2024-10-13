package com.pj.mind.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.L;
import com.hjq.base.BaseAdapter;
import com.hjq.base.action.ClickAction;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallbackProxy;
import com.hjq.toast.Toaster;
import com.pj.mind.R;
import com.pj.mind.app.AppActivity;
import com.pj.mind.http.Model.HttpData;
import com.pj.mind.http.api.main.MainApi;
import com.pj.mind.http.api.mind.InboxListApi;
import com.pj.mind.http.api.mind.MessageListApi;
import com.pj.mind.ui.adapter.ZixunshiAdapter;
import com.pj.mind.utils.NullUtil;
import com.pj.mind.utils.PreferencesUtils;

import java.util.ArrayList;
import java.util.List;

public class HisXinActivity extends AppActivity implements ClickAction {

    private static final String INTENT_KEY_IN_TYPE = "type";

    private RelativeLayout ll_empty;
    private LinearLayout ll_c;
    private List<InboxListApi.InboxBean> list = new ArrayList<>();
    private int currentNum = 0;
    private TextView tv_inbox;

    public static void start(Context context, int type) {
        Intent intent = new Intent(context, HisXinActivity.class);
        intent.putExtra(INTENT_KEY_IN_TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_his_xin;
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.tv_pre, R.id.tv_next);
        ll_empty = findViewById(R.id.ll_empty);
        ll_c = findViewById(R.id.ll_c);
        tv_inbox = findViewById(R.id.tv_inbox);
    }

    @Override
    protected void initData() {
        getInboxList();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onNoDoubleClick(View view) {
        switch (view.getId()) {
            case R.id.tv_pre:
                if (currentNum == 0) {
                    Toaster.show("没有上一封了");
                    return;
                }
                currentNum--;
                initInbox();
                break;
            case R.id.tv_next:
                if (currentNum == list.size() - 1) {
                    Toaster.show("没有下一封了");
                    return;
                }
                currentNum++;
                initInbox();
                break;
        }
    }

    private void getInboxList() {
        InboxListApi api = new InboxListApi();

        if (getInt(INTENT_KEY_IN_TYPE) == 0) {
            api.setSenderId(PreferencesUtils.getInstance().get("userId", 0));
        } else {
            api.setRecipientId(PreferencesUtils.getInstance().get("userId", 0));
        }

        EasyHttp.get(this).api(api)
                .request(new HttpCallbackProxy<HttpData<List<InboxListApi.InboxBean>>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<List<InboxListApi.InboxBean>> bean) {
                        if (bean.getData() == null) {
                            return;
                        }
                        list = bean.getData();
                        ll_empty.setVisibility(list.size() > 0 ? View.GONE : View.VISIBLE);
                        ll_c.setVisibility(list.size() > 0 ? View.VISIBLE : View.GONE);
                        initInbox();
                    }
                });
    }

    private void initInbox() {
        if (list.size() > 0) {
            tv_inbox.setText(list.get(currentNum).getMessageContent() + "");
        }
    }
}

