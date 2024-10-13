package com.pj.mind.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.hjq.base.action.ClickAction;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallbackProxy;
import com.hjq.toast.Toaster;
import com.pj.mind.R;
import com.pj.mind.app.AppActivity;
import com.pj.mind.http.Model.HttpData;
import com.pj.mind.http.api.mind.InboxApi;
import com.pj.mind.http.api.mind.MessageApi;
import com.pj.mind.utils.NullUtil;
import com.pj.mind.utils.PreferencesUtils;

public class EditXinActivity extends AppActivity implements ClickAction {

    private EditText editText;
    private CheckBox checkbox;

    public static void start(Context context) {
        Intent intent = new Intent(context, EditXinActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_xin;
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.ll_send);
        editText = findViewById(R.id.editText);
        checkbox = findViewById(R.id.checkbox);
    }

    @Override
    protected void initData() {

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onNoDoubleClick(View view) {
        switch (view.getId()) {
            case R.id.ll_send:
                sendMessage(editText.getText().toString().trim());
                editText.setText("");
                break;
        }
    }

    private void sendMessage(String text) {
        if (NullUtil.isStringEmpty(text)) {
            return;
        }
        EasyHttp.post(this).api(new InboxApi()
                        .setMessageContent(text)
                        .setAnonymous(checkbox.isChecked() ? 1 : 0)
                        .setRecipientId(1)
                        .setSenderId(PreferencesUtils.getInstance().get("userId", 0)))
                .request(new HttpCallbackProxy<HttpData<Void>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<Void> bean) {
                        Toaster.show("发送成功");
                        finish();
                    }
                });
    }
}

