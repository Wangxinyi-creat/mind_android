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
import com.pj.mind.http.api.login.ZhuceApi;
import com.pj.mind.utils.NullUtil;
import com.pj.mind.utils.PreferencesUtils;

import okhttp3.Call;

public class ZhuceActivity extends AppActivity implements ClickAction {

    private EditText et_name, et_pass,et_pass2;

    public static void start(Context context) {
        Intent intent = new Intent(context, ZhuceActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhuce;
    }

    @Override
    protected void initView() {
        et_pass = findViewById(R.id.et_pass);
        et_pass2 = findViewById(R.id.et_pass2);
        et_name = findViewById(R.id.et_name);
        setOnClickListener(R.id.commit);
    }

    @Override
    protected void initData() {
        setShowDialog(true);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onNoDoubleClick(View view) {
        switch (view.getId()) {
            case R.id.commit:
                String phone = et_name.getText().toString().trim();
                String pass = et_pass.getText().toString().trim();
                String pass2 = et_pass2.getText().toString().trim();
                if (NullUtil.isStringEmpty(phone)) {
                    Toaster.show("请输入账户");
                    return;
                }
                if (NullUtil.isStringEmpty(pass)) {
                    Toaster.show("请输入密码");
                    return;
                }
                if (NullUtil.isStringEmpty(pass2)) {
                    Toaster.show("请再次输入密码");
                    return;
                }
                if (!pass.equals(pass2)) {
                    Toaster.show("两次输入的密码不一致");
                    return;
                }
                EasyHttp.post(this).api(new ZhuceApi()
                                .setUsername(phone)
                                .setPassword(pass))
                        .request(new HttpCallbackProxy<HttpData<String>>(this) {

                            @Override
                            public void onHttpSuccess(HttpData data) {
//                                if (data.getToken() != null) {
//                                    PreferencesUtils.getInstance().saveToken(getContext(), data.getToken());
//                                    PreferencesUtils.getInstance().put("userId", data.getUserid());
//                                    MainActivity.start(getContext(), R.id.radio_home);
//                                    finish();
//                                }
                                Toaster.show("注册成功");
                            }

                            @Override
                            public void onHttpEnd(Call call) {
                            }
                        });
                break;
        }
    }

}

