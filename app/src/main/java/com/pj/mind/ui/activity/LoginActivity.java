package com.pj.mind.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.hjq.base.action.ClickAction;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallbackProxy;
import com.hjq.toast.Toaster;
import com.pj.mind.R;
import com.pj.mind.app.AppActivity;
import com.pj.mind.http.Model.HttpData;
import com.pj.mind.http.api.login.LoginApi;
import com.pj.mind.utils.NullUtil;
import com.pj.mind.utils.PreferencesUtils;

import okhttp3.Call;

public class LoginActivity extends AppActivity implements ClickAction {

    private EditText et_name, et_pass;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        et_pass = findViewById(R.id.et_pass);
        et_name = findViewById(R.id.et_name);
        setOnClickListener(R.id.commit,R.id.zhuce);
    }

    @Override
    protected void initData() {
        setShowDialog(true);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onNoDoubleClick(View view) {
        switch (view.getId()) {
            case R.id.zhuce:
                ZhuceActivity.start(getContext());
                break;
            case R.id.commit:
                String phone = et_name.getText().toString().trim();
                String pass = et_pass.getText().toString().trim();
                if (NullUtil.isStringEmpty(phone)) {
                    Toaster.show("请输入账户");
                    return;
                }
                if (NullUtil.isStringEmpty(pass)) {
                    Toaster.show("请输入密码");
                    return;
                }
                EasyHttp.post(this).api(new LoginApi()
                                .setUsername(phone)
                                .setPassword(pass))
                        .request(new HttpCallbackProxy<HttpData<String>>(this) {

                            @Override
                            public void onHttpSuccess(HttpData data) {
                                if (data.getToken() != null) {
                                    PreferencesUtils.getInstance().saveToken(getContext(), data.getToken());
                                    PreferencesUtils.getInstance().put("userId", data.getUserid());
                                    MainActivity.start(getContext(), R.id.radio_home);
                                    finish();
                                }
                            }

                            @Override
                            public void onHttpEnd(Call call) {
                            }
                        });
                break;
        }
    }

}

