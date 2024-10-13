package com.pj.mind.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hjq.base.BaseAdapter;
import com.hjq.base.action.ClickAction;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallbackProxy;
import com.pj.mind.AppContract;
import com.pj.mind.R;
import com.pj.mind.app.AppActivity;
import com.pj.mind.http.Model.HttpData;
import com.pj.mind.http.api.login.UserApi;
import com.pj.mind.http.api.mind.CounselorRecordListApi;
import com.pj.mind.http.api.mind.MessageApi;
import com.pj.mind.http.api.mind.MessageListApi;
import com.pj.mind.http.api.mind.MessageNextApi;
import com.pj.mind.ui.adapter.AdapterMessage;
import com.pj.mind.ui.adapter.AdapterQingsu;
import com.pj.mind.utils.GlideLoadUtils;
import com.pj.mind.utils.GlideUtils;
import com.pj.mind.utils.NullUtil;
import com.pj.mind.utils.PreferencesUtils;

import java.util.List;
import java.util.Timer;
import java.util.function.Consumer;

import okhttp3.Call;

public class MessageActivity extends AppActivity implements ClickAction {

    private static final String INTENT_KEY_IN_RECORD_ID = "recordId";
    private static final String INTENT_KEY_IN_COUNSELOR_ID = "counselorId";
    private static final String INTENT_KEY_IN_COUNSELOR_NAME = "name";
    private static final String INTENT_KEY_IN_COUNSELOR_URL = "url";

    private RecyclerView recyclerView;
    private AdapterMessage adapter;
    private ImageView touxiang;
    private TextView title;
    private EditText editText;

    private int lastMessageId;

    private Timer timer = new Timer();
    private UserApi.UserBean user;


    public static void start(Context context, int recordId, int counselorId, String name, String url) {
        Intent intent = new Intent(context, MessageActivity.class);
        intent.putExtra(INTENT_KEY_IN_RECORD_ID, recordId);
        intent.putExtra(INTENT_KEY_IN_COUNSELOR_NAME, name);
        intent.putExtra(INTENT_KEY_IN_COUNSELOR_URL, url);
        intent.putExtra(INTENT_KEY_IN_COUNSELOR_ID, counselorId);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.iv_back, R.id.ll_send);
        editText = findViewById(R.id.editText);
        title = findViewById(R.id.title);
        touxiang = findViewById(R.id.touxiang);
        title.setText(getString(INTENT_KEY_IN_COUNSELOR_NAME));
        GlideLoadUtils.getInstance().glideLoadNormal(this, AppContract.BASE_URL + getString(INTENT_KEY_IN_COUNSELOR_URL), touxiang, R.mipmap.bg_login);
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new AdapterMessage(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        EasyHttp.get(this).api(new UserApi())
                .request(new HttpCallbackProxy<HttpData<UserApi.UserBean>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<UserApi.UserBean> bean) {
                        if (bean.getData() == null) {
                            return;
                        }
                        user = bean.getData();
                        getMessage();
                    }
                });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onNoDoubleClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.ll_send:
                sendMessage(editText.getText().toString().trim());
                editText.setText("");
                break;
        }
    }

    private void getMessage() {
        EasyHttp.get(this).api(new MessageListApi()
                        .setRecordId(getInt(INTENT_KEY_IN_RECORD_ID)))
                .request(new HttpCallbackProxy<HttpData<List<MessageListApi.MessageBean>>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<List<MessageListApi.MessageBean>> bean) {
                        if (bean.getData() == null) {
                            return;
                        }
                        List<MessageListApi.MessageBean> data = bean.getData();
                        data.forEach(messageBean -> {
                            String url = messageBean.getSenderId() == PreferencesUtils.getInstance().get("userId", 0) ?
                                    user.getAvatar() : getString(INTENT_KEY_IN_COUNSELOR_URL);
                            messageBean.setUrl(url);
                        });

                        adapter.setData(data);
                        if (data.size() > 0) {
                            lastMessageId = data.get(data.size() - 1).getMessageId();
                        }
                        recyclerView.scrollToPosition(adapter.getCount() - 1);

                    }
                });
    }

    private void getLastMessage() {
        EasyHttp.get(this).api(new MessageNextApi()
                        .setMessageId(lastMessageId)
                        .setRecordId(getInt(INTENT_KEY_IN_RECORD_ID)))
                .request(new HttpCallbackProxy<HttpData<List<MessageListApi.MessageBean>>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<List<MessageListApi.MessageBean>> bean) {
                        if (bean.getData() == null) {
                            return;
                        }
                        List<MessageListApi.MessageBean> data = bean.getData();
                        adapter.addData(data);
                        if (data.size() > 0) {
                            lastMessageId = data.get(data.size() - 1).getMessageId();
                        }
                        recyclerView.scrollToPosition(adapter.getCount() - 1);
                    }
                });
    }

    private void sendMessage(String text) {
        if (NullUtil.isStringEmpty(text)) {
            return;
        }
        EasyHttp.post(this).api(new MessageApi()
                        .setMessageContent(text)
                        .setRecordId(getInt(INTENT_KEY_IN_RECORD_ID))
                        .setRecipientId(getInt(INTENT_KEY_IN_COUNSELOR_ID))
                        .setSenderId(PreferencesUtils.getInstance().get("userId", 0)))
                .request(new HttpCallbackProxy<HttpData<Void>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<Void> bean) {
                        if (lastMessageId > 0) {
                            getLastMessage();
                        } else {
                            initData();
                        }

                    }
                });
    }
}

