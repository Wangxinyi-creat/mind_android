package com.pj.mind.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.hjq.base.action.ClickAction;
import com.pj.mind.R;
import com.pj.mind.app.AppActivity;
import com.pj.mind.http.api.main.MainApi;
import com.pj.mind.ui.adapter.ZixunshiAdapter;

import java.util.ArrayList;
import java.util.List;

public class XinQingActivity extends AppActivity implements ClickAction {
    private ImageView iv_mail;

    public static void start(Context context) {
        Intent intent = new Intent(context, XinQingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_xin_qing;
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.tv_his, R.id.iv_back, R.id.iv_mail, R.id.tv_his_back);
        iv_mail = findViewById(R.id.iv_mail);

    }

    @Override
    protected void initData() {

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onNoDoubleClick(View view) {
        switch (view.getId()) {
            case R.id.tv_his:
                HisXinActivity.start(this, 0);
                break;
            case R.id.tv_his_back:
                HisXinActivity.start(this, 1);
                break;
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.iv_mail:
                iv_mail.setSelected(!iv_mail.isSelected());
                iv_mail.postDelayed(() -> EditXinActivity.start(XinQingActivity.this), 500);
                break;
        }
    }
}

